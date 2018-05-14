package br.com.web.servexpress.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;

import br.com.web.servexpress.enums.Pagamento;
import br.com.web.servexpress.helper.ChartHelper;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.reports.ChartModel;
import br.com.web.servexpress.reports.ChartsTemplates;
import br.com.web.servexpress.reports.GraficoBairrosMaisUsados;
import br.com.web.servexpress.reports.GraficoNumeroPedidosMes;
import br.com.web.servexpress.reports.GraficoVendasGastosReport;
import br.com.web.servexpress.reports.TodosClientesReport;
import br.com.web.servexpress.service.ClienteService;
import br.com.web.servexpress.service.GastoService;
import br.com.web.servexpress.service.PedidoService;
import br.com.web.servexpress.service.impl.ChartService;

@Controller
public class ReportController extends AbstractController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private GastoService gastoService;
	
	@Autowired
	private ChartService chartService;

	/* GET /admin/relatorio/clientes */
	@GetMapping(value = MAPPING_ADMIN + "relatorio/clientes", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> clientesReport() throws IOException {

		List<Cliente> clientes = this.clienteService.lista();

		ByteArrayInputStream bis = TodosClientesReport.create(clientes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	/* GET /admin/relatorio/vendas-gastos */
	@GetMapping(value = MAPPING_ADMIN + "relatorio/vendas-gastos", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> vendasGastosReport() throws IOException, DocumentException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report.pdf");

		Double pedidosPagosDoMes = this.pedidoService.vendasPorMesDinheiro(Pagamento.PAGO);
		Double gastos = this.gastoService.valorTotal();
		
		List<ChartModel> values = new ArrayList<>();
		values.add(new ChartModel("Vendas - R$" + pedidosPagosDoMes, pedidosPagosDoMes));
		values.add(new ChartModel("Gastos - R$" + gastos, gastos));

		ByteArrayInputStream bis = GraficoVendasGastosReport.create(ChartsTemplates.generatePieChart("Vendas x Gastos", values), 500, 400);

		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	
	/* GET /admin/relatorio/pedidos-por-mes */
	@GetMapping(value = MAPPING_ADMIN + "relatorio/pedidos-por-mes", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> pedidosPorMes() throws IOException, DocumentException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report.pdf");

		List<ChartHelper> values = this.chartService.barFromPedidos();

		ByteArrayInputStream bis = GraficoNumeroPedidosMes.create(ChartsTemplates.generateBarChartHelper("Número de pedidos por mês", "Mês", "Número de pedidos", values), 500, 400);
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	/* GET /admin/relatorio/bairros-mais-usados */
	@GetMapping(value = MAPPING_ADMIN + "relatorio/bairros-mais-usados", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> bairrosMaisUsados() throws IOException, DocumentException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report.pdf");

		List<ChartModel> values = this.chartService.bairrosPorPedidos();

		ByteArrayInputStream bis = GraficoBairrosMaisUsados.create(ChartsTemplates.generateBarChartModel("Bairros mais usados em pedidos", "Bairro", "Número de bairros usados", values), 500, 400);
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	
	
}
