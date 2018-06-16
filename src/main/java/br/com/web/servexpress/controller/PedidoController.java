package br.com.web.servexpress.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.web.servexpress.enums.StatusPedido;
import br.com.web.servexpress.exceptions.PagamentoNaoEfetuadoException;
import br.com.web.servexpress.exceptions.PedidoSemPecasException;
import br.com.web.servexpress.model.Bairro;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Endereco;
import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Pedido;
import br.com.web.servexpress.model.PedidoRoupa;
import br.com.web.servexpress.model.Roupa;
import br.com.web.servexpress.service.BairroService;
import br.com.web.servexpress.service.ClienteService;
import br.com.web.servexpress.service.EnderecoService;
import br.com.web.servexpress.service.EntregadorService;
import br.com.web.servexpress.service.PedidoService;
import br.com.web.servexpress.service.RoupaService;
import br.com.web.servexpress.util.EnviaEmail;
import br.com.web.servexpress.wrapper.PedidoRoupaWrapper;

@Controller
public class PedidoController extends AbstractController {

	private static final String PAGINA_ADMIN_PEDIDO_ACAO = "/pedido/admin/list";
	private static final String PAGINA_ADMIN_PEDIDO = "/pedido/admin/all";
	private static final String PAGINA_ADMIN_PEDIDO_DETAIL = "/pedido/admin/detail";

	private static final String PAGINA_ENTREGADOR_PEDIDO = "/pedido/entregador/all";
	private static final String PAGINA_ENTREGADOR_PEDIDO_DETAIL = "/pedido/entregador/detail";

	private static final String PAGINA_CLIENTE_PEDIDO = "/pedido/cliente/all";
	private static final String PAGINA_CLIENTE_PEDIDO_ADD = "/pedido/cliente/add";
	private static final String PAGINA_CLIENTE_PEDIDO_DETAIL = "/pedido/cliente/detail";

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private EntregadorService entregadorService;

	@Autowired
	private EnviaEmail sendEmail;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private RoupaService roupaService;

	@Autowired
	private BairroService bairroService;

	/*
	 * 
	 * CLIENTE
	 * 
	 */

	@GetMapping(MAPPING_CLIENTE + "pedido")
	public ModelAndView pedidoCliente() {
		Cliente cliente = clienteService.busca(this.getUser());
		List<Pedido> pedidos = pedidoService.listaDataEmissaoDesc(cliente);
		return new ModelAndView(PAGINA_CLIENTE_PEDIDO).addObject("pedidos", pedidos);
	}

	@PostMapping(MAPPING_CLIENTE + "pedido")
	public ModelAndView addPedido(PedidoRoupaWrapper pedidoRoupaWrapper, Integer endereco, String cep, String rua,
			String numero, Bairro bairro, RedirectAttributes attributes) {

		pedidoRoupaWrapper.getPedidoRoupas().removeIf(s -> s.getQuantidade() == null);

		String mensagem = null;

		try {
			Cliente cliente = clienteService.busca(this.getUser());
			Pedido pedido = new Pedido();

			if (endereco == 2) {
				Endereco e = new Endereco();
				e.setCep(cep);
				e.setRua(rua);
				e.setNumero(numero);
				e.setBairroEntity(bairro);
				enderecoService.persiste(e);
				pedido.setEndereco(e);
			} else
				pedido.setEndereco(cliente.getEndereco());

			pedido.setCliente(cliente);
			pedidoRoupaWrapper.getPedidoRoupas().forEach(s -> {
				s.setPedido(pedido);
			});
			pedidoService.persiste(pedidoRoupaWrapper);
			mensagem = this.successMessage("Pedido cadastrado com sucesso!");
		} catch (PedidoSemPecasException pspe) {
			pspe.printStackTrace();
			mensagem = this.errorMessage("Pedido sem peças para cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage("Erro ao cadastrar pedido");
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/cliente/dashboard");
	}

	@GetMapping(MAPPING_CLIENTE + "pedido/add")
	public ModelAndView formPedido(PedidoRoupaWrapper pedidoRoupaWrapper) {
		List<Roupa> roupas = roupaService.lista();
		return this.modelAndView(PAGINA_CLIENTE_PEDIDO_ADD).addObject("roupas", roupas).addObject("bairros",
				bairroService.lista());
	}

	@GetMapping(MAPPING_CLIENTE + "pedido/{id}")
	public ModelAndView pedidoDetailCliente(@PathVariable Long id) {
		Cliente cliente = this.clienteService.busca(getUser());
		Pedido pedido = this.pedidoService.busca(id, cliente);
		List<PedidoRoupa> roupas = null;

		if (pedido == null)
			return this.redirect("/cliente/pedido");

		roupas = this.pedidoService.buscaRoupas(pedido);
		return this.modelAndView(PAGINA_CLIENTE_PEDIDO_DETAIL).addObject("pedido", pedido).addObject("roupas", roupas);
	}

	/*
	 * 
	 * ENTREGADOR
	 * 
	 */

	@GetMapping(MAPPING_ENTREGADOR + "pedido")
	public ModelAndView pedidoEntregador() {
		List<Pedido> pedidos = this.pedidoService.lista();
		Collections.reverse(pedidos);
		return this.modelAndView(PAGINA_ENTREGADOR_PEDIDO).addObject("pedidos", pedidos);
	}

	@GetMapping(MAPPING_ENTREGADOR + "pedido/{id}")
	public ModelAndView pedidoDetailEntregador(@PathVariable Long id) {
		Pedido pedido = this.pedidoService.busca(id);
		List<PedidoRoupa> roupas = null;

		if (pedido == null)
			return this.redirect("/entregador/pedido");

		roupas = this.pedidoService.buscaRoupas(pedido);
		return this.modelAndView(PAGINA_ENTREGADOR_PEDIDO_DETAIL).addObject("pedido", pedido).addObject("roupas",
				roupas);
	}

	@PostMapping(MAPPING_ENTREGADOR + "pedido/encaminhar")
	public ModelAndView sendABiker(Long id, RedirectAttributes attributes) {
		Pedido pedido = this.pedidoService.busca(id);
		Entregador entregador = this.entregadorService.busca(getUser());

		String mensagem = null;

		try {
			this.pedidoService.sendABiker(pedido, entregador, StatusPedido.ENTREGADOR_ENVIADO_A_RESIDENCIA);
			sendEmail.statusAlterado(pedido);
			mensagem = this.successMessage();
		} catch (Exception e) {
			mensagem = this.errorMessage();
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/entregador/dashboard");
	}

	@PostMapping(MAPPING_ENTREGADOR + "pedido/entregar")
	public ModelAndView delivery(Long id, RedirectAttributes attributes) {
		Pedido pedido = this.pedidoService.busca(id);

		String mensagem = null;

		try {
			this.pedidoService.delivering(pedido);
			mensagem = this.successMessage();
		} catch (Exception e) {
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/entregador/dashboard");
	}

	@PostMapping(MAPPING_ENTREGADOR + "pedido/pagar")
	public ModelAndView paymentEntregador(Long id, RedirectAttributes attributes) {
		Pedido pedido = this.pedidoService.busca(id);

		String mensagem = null;

		try {
			this.pedidoService.pagamento(pedido);
			mensagem = this.successMessage();
		} catch (Exception e) {
			mensagem = this.errorMessage();
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/entregador/pedido");
	}

	/*
	 * 
	 * ADMINISTRADOR
	 * 
	 */

	@PostMapping(MAPPING_ADMIN + "pedido")
	public ModelAndView pedido(Long id, RedirectAttributes attributes) {
		Pedido pedido = this.pedidoService.busca(id);

		String mensagem = null;

		try {
			this.pedidoService.mudaStatus(pedido);
			if (pedido.getStatusPedido() == StatusPedido.ENTREGANDO)
				sendEmail.statusAlterado(pedido);
			mensagem = this.successMessage();
		} catch (PagamentoNaoEfetuadoException e) {
			mensagem = this.errorMessage("Pagamento não efetuado!");
		} catch (Exception e) {
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/pedido");
	}

	@PostMapping(MAPPING_ADMIN + "pedido/pagar")
	public ModelAndView payment(Long id, RedirectAttributes attributes) {
		Pedido pedido = this.pedidoService.busca(id);

		String mensagem = null;

		try {
			this.pedidoService.pagamento(pedido);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/pedido");
	}

	@GetMapping(MAPPING_ADMIN + "pedido")
	public ModelAndView pedido() {
		List<Pedido> pedidos = this.pedidoService.lista();
		Collections.reverse(pedidos);
		return this.modelAndView(PAGINA_ADMIN_PEDIDO).addObject("pedidos", pedidos);
	}

	@GetMapping(MAPPING_ADMIN + "pedido/enviaentregador")
	public ModelAndView pedidoLavarEnviaEntregador() {
		List<Pedido> pedidos = this.pedidoService.lista(StatusPedido.PENDENTE);
		return this.modelAndView(PAGINA_ADMIN_PEDIDO_ACAO).addObject("pedidos", pedidos);
	}

	@GetMapping(MAPPING_ADMIN + "pedido/lavar")
	public ModelAndView pedidoLavar() {
		List<Pedido> pedidos = this.pedidoService.lista(StatusPedido.ENTREGADOR_ENVIADO_A_RESIDENCIA);
		return this.modelAndView(PAGINA_ADMIN_PEDIDO_ACAO).addObject("pedidos", pedidos);
	}

	@GetMapping(MAPPING_ADMIN + "pedido/secar")
	public ModelAndView pedidoSecar() {
		List<Pedido> pedidos = this.pedidoService.lista(StatusPedido.LAVANDO);
		return this.modelAndView(PAGINA_ADMIN_PEDIDO_ACAO).addObject("pedidos", pedidos);
	}

	@GetMapping(MAPPING_ADMIN + "pedido/entregar")
	public ModelAndView pedidoEntregar() {
		List<Pedido> pedidos = this.pedidoService.lista(StatusPedido.SECANDO);
		return this.modelAndView(PAGINA_ADMIN_PEDIDO_ACAO).addObject("pedidos", pedidos);
	}

	@GetMapping(MAPPING_ADMIN + "pedido/entregando")
	public ModelAndView pedidoEntregue() {
		List<Pedido> pedidos = this.pedidoService.lista(StatusPedido.ENTREGANDO);
		return this.modelAndView(PAGINA_ADMIN_PEDIDO_ACAO).addObject("pedidos", pedidos);
	}

	@GetMapping(MAPPING_ADMIN + "pedido/{id}")
	public ModelAndView pedidoDetail(@PathVariable Long id) {

		Pedido pedido = this.pedidoService.busca(id);
		List<PedidoRoupa> roupas = null;

		if (pedido == null)
			return this.redirect("/cliente/pedido");

		roupas = this.pedidoService.buscaRoupas(pedido);
		return this.modelAndView(PAGINA_ADMIN_PEDIDO_DETAIL).addObject("pedido", pedido).addObject("roupas", roupas);
	}

}
