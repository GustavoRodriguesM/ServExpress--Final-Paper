package br.com.web.servexpress.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.helper.ChartHelper;
import br.com.web.servexpress.model.Bairro;
import br.com.web.servexpress.model.Pedido;
import br.com.web.servexpress.reports.ChartModel;
import br.com.web.servexpress.repository.PedidoRepository;
import br.com.web.servexpress.service.BairroService;

@Service
public class ChartService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private BairroService bairroService;

	public List<ChartHelper> barFromPedidos() {
		List<ChartHelper> pedidos = new ArrayList<>();

		Calendar calendar = new GregorianCalendar();
		calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		Calendar calendar1 = new GregorianCalendar();
		calendar1.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

		Long a = this.pedidoRepository.countByDataEmissaoBetween(calendar1, calendar);
		ChartHelper chartHelper = new ChartHelper();

		chartHelper.setMonth(calendar.get(Calendar.MONTH));
		chartHelper.setAmount(a);
		pedidos.add(chartHelper);

		for (int i = 0; i < 5; i++) {
			calendar = new GregorianCalendar();
			calendar = Calendar.getInstance();
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - i, 1);
			calendar1 = new GregorianCalendar();
			calendar1.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);

			a = this.pedidoRepository.countByDataEmissaoBetween(calendar1, calendar);
			chartHelper = new ChartHelper();

			chartHelper.setMonth(calendar1.get(Calendar.MONTH));
			chartHelper.setAmount(a);
			pedidos.add(chartHelper);
		}

		return pedidos;
	}

	public List<ChartModel> bairrosPorPedidos() {
		List<Bairro> bairros = this.bairroService.listaComDeletados();
		List<Pedido> pedidos = (List<Pedido>) this.pedidoRepository.findAll();
		List<ChartModel> data = new ArrayList<>();
		
		for(int i = 0; i < bairros.size(); i++) {
			Long count = 0l;
			for (Pedido pedido : pedidos) {
				if (bairros.get(i) == pedido.getEndereco().getBairroEntity()) {
					count++;
					
				}
			}

			data.add(new ChartModel(bairros.get(i).getNome(), count.doubleValue()));
		}
		
		return data;
	}
}
