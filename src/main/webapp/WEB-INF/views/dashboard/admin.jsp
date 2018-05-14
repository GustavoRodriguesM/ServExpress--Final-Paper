<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<myTags:template bodyName="Dashboard">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
$( document ).ready(function() {
		${sweetAlert}
})
</script>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div class="page-wrapper">
		<div class="container-fluid">
			<div class="row">


				<!-- Column -->
				<div class="col-lg-3 col-md-6">
					<div class="card">
						<div class="card-body">
							<div class="d-flex p-10 no-block">
								<span class="align-slef-center">
									<h2 class="m-b-0">${countPedidosPendentes}</h2>
									<h6 class="text-muted m-b-0">Pedidos pendentes</h6>
								</span>
								<div class="align-self-center display-6 ml-auto">
									<i class="text-success icon-Target-Market"></i>
								</div>
							</div>
						</div>
						<div class="progress">
							<div class="progress-bar bg-success" role="progressbar"
								aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"
								style="width: 50%; height: 3px;"></div>
						</div>
					</div>
				</div>
				<!-- Column -->
				<!-- Column -->
				<div class="col-lg-3 col-md-6">
					<div class="card">
						<div class="card-body">
							<div class="d-flex p-10 no-block">
								<span class="align-slef-center">
									<h2 class="m-b-0">${countPedidosEntregues}</h2>
									<h6 class="text-muted m-b-0">Pedidos entregues</h6>
								</span>
								<div class="align-self-center display-6 ml-auto">
									<i class="text-info icon-Dollar-Sign"></i>
								</div>
							</div>
						</div>
						<div class="progress">
							<div class="progress-bar bg-info" role="progressbar"
								aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"
								style="width: 50%; height: 3px;"></div>
						</div>
					</div>
				</div>
				<!-- Column -->
				<!-- Column -->
				<div class="col-lg-3 col-md-6">
					<div class="card">
						<div class="card-body">
							<div class="d-flex p-10 no-block">
								<span class="align-slef-center">
									<h2 class="m-b-0">${countPagamentosPendentes}</h2>
									<h6 class="text-muted m-b-0">Pagamentos pendentes</h6>
								</span>
								<div class="align-self-center display-6 ml-auto">
									<i class="text-primary icon-Inbox-Forward"></i>
								</div>
							</div>
						</div>
						<div class="progress">
							<div class="progress-bar bg-primary" role="progressbar"
								aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"
								style="width: 50%; height: 3px;"></div>
						</div>
					</div>
				</div>
				<!-- Column -->
				<!-- Column -->
				<div class="col-lg-3 col-md-6">
					<div class="card">
						<div class="card-body">
							<div class="d-flex p-10 no-block">
								<span class="align-slef-center">
									<h2 class="m-b-0">${numeroEntregadores}</h2>
									<h6 class="text-muted m-b-0">Entregadores Registrados</h6>
								</span>
								<div class="align-self-center display-6 ml-auto">
									<i class="text-danger icon-Contrast"></i>
								</div>
							</div>
						</div>
						<div class="progress">
							<div class="progress-bar bg-danger" role="progressbar"
								aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"
								style="width: 50%; height: 3px;"></div>
						</div>
					</div>
				</div>
				<!-- Column -->
				<!-- Column -->

			</div>

			<div class="row">
				<div class="col-md-6 content">
					<canvas id="ganhosMes"></canvas>
				</div>
				<div class="col-md-6 content">
					<canvas id="ganhosMeses"></canvas>
				</div>
			</div>
			<hr />

			<div class="row">
				<div class="col-md-6">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">5 clientes mais participativos</h5>
							<table class="table table-hover color-table info-table">
								<thead>
									<tr>
										<th>Cliente</th>
										<th>Total de pedidos</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${top5Clientes}" var="topCliente">
										<tr>
											<td>${topCliente.cliente.usuario.nome}</td>
											<td>${topCliente.numeroPedidos}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-md-6 card">
					<h3 class="box-title">Vendas recentes</h3>
					<div class="row sales-report">
						<div class="col-md-6 col-sm-6 col-xs-6">
							<h2>${chartHelper.month}</h2>

						</div>
						<div class="col-md-6 col-sm-6 col-xs-6 ">
							<h1 class="text-right text-info m-t-20">${ganhosString}</h1>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-hover color-table info-table">
							<thead>
								<tr>
									<th>#</th>
									<th>NOME</th>
									<th>STATUS</th>
									<th>DATA</th>
									<th>VALOR</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pedidos5}" var="pedido">
									<tr>
										<td><b><a
												href="<c:url value='/admin/pedido/${pedido.id}'/>">${pedido.id}</a></b></td>
										<td class="txt-oflo">${pedido.cliente.usuario.nome}</td>
										<td><c:choose>
												<c:when test="${pedido.statusPedido == 'PENDENTE'}">
													<span class="label label-custom">${pedido.statusPedido.descricao}</span>
												</c:when>
												<c:when
													test="${pedido.statusPedido == 'ENTREGADOR_ENVIADO_A_RESIDENCIA'}">
													<span class="label label-purple">${pedido.statusPedido.descricao}</span>
												</c:when>
												<c:when test="${pedido.statusPedido == 'LAVANDO'}">
													<span class="label label-danger">${pedido.statusPedido.descricao}</span>
												</c:when>
												<c:when test="${pedido.statusPedido == 'SECANDO'}">
													<span class="label label-info">${pedido.statusPedido.descricao}</span>
												</c:when>
												<c:when test="${pedido.statusPedido == 'ENTREGANDO'}">
													<span class="label label-warning">${pedido.statusPedido.descricao}</span>
												</c:when>
												<c:when test="${pedido.statusPedido == 'ENTREGUE'}">
													<span class="label label-success">${pedido.statusPedido.descricao}</span>
												</c:when>
											</c:choose></td>
										<td class="txt-oflo"><fmt:formatDate
												value="${pedido.dataEmissao.time}"
												pattern="HH:mm dd/MM/yyyy" /></td>
										<td><b>R$${pedido.total}</b></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
			</div>
			<div class="row content">

				<div class="col-md-6">


					<h3 class="box-title">Feedbacks</h3>
					<div class="comment-center p-t-10">
						<div class="comment-body">
							<c:forEach items="${feedbacks5}" var="feedback">
								<div class="mail-content">
									<h5>${feedback.cliente.usuario.nome}</h5>
									<span class="label label-rouded label-info">${feedback.avaliacao}</span>
									<br> <span class="mail-desc">${feedback.descricao}</span>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>


		</div>
		<footer class="footer"> © 2018 ServExpress por BlackCode </footer>
	</div>
	<script>


		window.onload = function() {
			var ctx1 = document.getElementById("ganhosMes").getContext("2d");
			window.myPie = new Chart(ctx1, 
					{
				type : 'pie',
				data : {
					datasets : [ {
						data : [ ${sumPago}, ${gastos} ],
						backgroundColor : [ 'rgb(32,174,227)', 'rgb(255,92,108)' ],
						label : 'Dataset 1'
					} ],
					labels : [ "Vendas do mês", "Gastos do mês" ]
				},
				options : {
					responsive : true,
					title : {
						display : true,
						text : 'Vendas X Gastos'
					}
				}
			});
			

			var ctx = document.getElementById("ganhosMeses").getContext("2d");
			window.myBar = new Chart(ctx, {
				type : 'bar',
				data : barChartData,
				options : {
					responsive : true,
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : 'Número de pedidos por mês'
					}
				}
			});

		};
		

		var MONTHS = [ <c:forEach items="${numeroPedidosMes}" var="numeroPedidosMes">"${numeroPedidosMes.month}",</c:forEach> ];
		var barChartData = {
			labels : [ <c:forEach items="${numeroPedidosMes}" var="numeroPedidosMes">"${numeroPedidosMes.month}",</c:forEach> ],
			datasets : [ {
				label : 'Pedidos por mês',
				backgroundColor : 'rgb(0, 109, 255)',
				borderColor : 'rgb(0, 109, 255)',
				borderWidth : 1,
				data : [ <c:forEach items="${numeroPedidosMes}" var="numeroPedidosMes">${numeroPedidosMes.amount},</c:forEach> ]
			} ]

		};
		

	</script>

</myTags:template>