package br.com.web.servexpress.controller;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.web.servexpress.enums.Pagamento;
import br.com.web.servexpress.enums.StatusPedido;
import br.com.web.servexpress.helper.ChartHelper;
import br.com.web.servexpress.helper.TopClienteAdminDashboard;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Feedback;
import br.com.web.servexpress.model.Pedido;
import br.com.web.servexpress.model.PedidoRoupa;
import br.com.web.servexpress.service.ClienteService;
import br.com.web.servexpress.service.EntregadorService;
import br.com.web.servexpress.service.GastoService;
import br.com.web.servexpress.service.PedidoService;
import br.com.web.servexpress.service.impl.ChartService;
import br.com.web.servexpress.service.impl.FeedbackService;

@Controller
public class DashboardController extends AbstractController {

	private static final String PAGINA_CLIENTE_DASHBOARD = "/dashboard/cliente";
	private static final String PAGINA_ENTREGADOR_DASHBOARD = "/dashboard/entregador";
	private static final String PAGINA_ADMIN_DASHBOARD = "/dashboard/admin";

	@Autowired
	private EntregadorService entregadorService;

	@Autowired
	private GastoService gastoService;

	@Autowired
	private ChartService chartService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PedidoService pedidoService;

	@GetMapping(MAPPING_CLIENTE + "dashboard")
	public ModelAndView dashboardCliente() {
		Cliente cliente = this.clienteService.busca(getUser());
		PedidoRoupa pedidoRoupa = pedidoService.buscaUltimo(cliente);
		List<PedidoRoupa> roupas = null;
		if (pedidoRoupa != null)
			roupas = this.pedidoService.buscaRoupas(pedidoRoupa.getPedido());
		
		return new ModelAndView(PAGINA_CLIENTE_DASHBOARD)
				.addObject("pedidoRoupa", pedidoRoupa)
				.addObject("roupas", roupas);
	}

	@GetMapping(MAPPING_ENTREGADOR + "dashboard")
	public ModelAndView dashboardEntregador() {
		List<Pedido> pedidosParaBuscar = this.pedidoService.lista(StatusPedido.PENDENTE);
		List<Pedido> pedidosParaEntregar = this.pedidoService.lista(StatusPedido.ENTREGANDO);
		return this.modelAndView(PAGINA_ENTREGADOR_DASHBOARD)
				.addObject("pedidosParaBuscar", pedidosParaBuscar)
				.addObject("pedidosParaEntregar", pedidosParaEntregar);
	}

	@GetMapping(MAPPING_ADMIN + "dashboard")
	public ModelAndView dashboardAdministrador() {
		Long pedidosPendentes = this.pedidoService.conta(StatusPedido.PENDENTE);
		Long pedidosEntregues = this.pedidoService.conta(StatusPedido.ENTREGUE);
		Long pagamentosPendentes = this.pedidoService.conta(Pagamento.PENDENTE);
		List<Pedido> pedidos5 = this.pedidoService.buscaTop5();
		List<Feedback> feedbacks5 = feedbackService.findTop5();

		Double vendasPorMesDinheiro = this.pedidoService.vendasPorMesDinheiro(Pagamento.PAGO);
		Double gastos = this.gastoService.valorTotal();
		String ganhosString = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(vendasPorMesDinheiro);
		Long numeroEntregadores = this.entregadorService.conta();

		List<ChartHelper> numeroPedidosMes = this.chartService.barFromPedidos();

		ChartHelper chartHelper = new ChartHelper();
		chartHelper.setMonth(Calendar.getInstance().get(Calendar.MONTH));

		Collections.reverse(numeroPedidosMes);

		List<TopClienteAdminDashboard> top5Clientes = this.clienteService.top5PorNumeroPedidos();
		
		return this.modelAndView(PAGINA_ADMIN_DASHBOARD).addObject("top5Clientes", top5Clientes)
				.addObject("feedbacks5", feedbacks5)
				.addObject("pedidos5", pedidos5).addObject("chartHelper", chartHelper)
				.addObject("countPedidosPendentes", pedidosPendentes)
				.addObject("numeroEntregadores", numeroEntregadores)
				.addObject("countPedidosEntregues", pedidosEntregues).addObject("gastos", gastos)
				.addObject("numeroPedidosMes", numeroPedidosMes)
				.addObject("countPagamentosPendentes", pagamentosPendentes).addObject("sumPago", vendasPorMesDinheiro)
				.addObject("ganhosString", ganhosString);
	}

}
