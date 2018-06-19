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

	<script>
		function visualizaRoupas(check) {
			var endereco = document.getElementById("roupas");
			if (endereco.classList.contains('displayNo')) {
				endereco.classList.remove('displayNo');
			} else {
				endereco.classList.add('displayNo');
			}
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div class="page-wrapper">
		<div class="container-fluid">
			<div class="row page-titles">
				<div class="col-md-12 content align-self-center">
					<h3 class="text-themecolor pull-left">Pedido #${pedido.id}</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a href="/admin/pedido">Pedido</a></li>
						<li class="breadcrumb-item active">Detalhe</li>
					</ol>
				</div>
			</div>
			<div class="container content">
				<div class="row">


					<div class="col-md-12">
						<div class="card card-body printableArea">
							<h3>
								<b>Pedido </b> <span class="pull-right">#${pedido.id}</span>
							</h3>
							<hr>
							<div class="row">
								<div class="col-md-12">
									<div class="pull-left">
										<address>
											<h3>
												&nbsp;<b class="text-danger">${pedido.cliente.usuario.nome}</b>
											</h3>
											<p class=" m-l-5">
												${pedido.cliente.endereco.rua},
												${pedido.cliente.endereco.numero}<br />
												${pedido.cliente.endereco.bairroEntity.nome}, <br />
												${pedido.cliente.endereco.cep}
											</p>
										</address>
									</div>
									<div class="pull-right text-right">

										<h3>Endereço de entrega</h3>
										<p class="m-l-30">
											${pedido.endereco.rua}, ${pedido.endereco.numero}<br />
											${pedido.endereco.bairroEntity.nome}, <br />
											${pedido.endereco.cep}
										</p>
										<p class="m-t-30">
											<b>Data de emissão:</b> <i class="fa fa-calendar"></i>
											<fmt:formatDate value="${pedido.dataEmissao.time}"
												pattern="HH:mm dd/MM/yyyy" />
										</p>
										<p>
											<b>Data de entrega :</b> <i class="fa fa-calendar"></i>
											<fmt:formatDate value="${pedido.dataEntrega.time}"
												pattern="HH:mm dd/MM/yyyy" />
										</p>

									</div>
								</div>

								<div class="col-md-12">
									<span class="text-danger">Status:</span> <b>${pedido.statusPedido.descricao}</b><br />
									<span class="text-danger">Pagamento:</span> <b>${pedido.pagamento.descricao}</b>
								</div>

								<div class="col-md-4 offset-md-4 text-center">
									<span><input id="visualizarRoupas"
										name="visualizarRoupas" type="checkbox"
										onchange="visualizaRoupas()" value="visualizarRoupas" /><label
										for="visualizarRoupas">Visualizar roupas</label></span>
								</div>
								<div class="col-md-12 displayNo" id="roupas">
									<div class="table-responsive m-t-40" style="clear: both;">
										<table class="table table-hover color-table danger-table">
											<thead>
												<tr>
													<th>#</th>
													<th>Produto</th>
													<th>Quantidade</th>
													<th>Subtotal</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${roupas}" var="pedidoRoupa" varStatus="a">
													<tr>
														<td>${a.index + 1}</td>
														<td>${pedidoRoupa.roupa.descricao}</td>
														<td>${pedidoRoupa.quantidade}</td>
														<td>R$ <fmt:formatNumber value ="${pedidoRoupa.quantidade * pedidoRoupa.roupa.valorUnitario}" maxFractionDigits="2" minFractionDigits="2" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-12">
									<div class="pull-right m-t-30 text-right">
										<h3>
											<b>Total :</b> R$${pedido.total}
										</h3>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row text-center">
					<hr />
					<div class="col-md-12" style="margin: 0 auto;">
						<form:form servletRelativeAction="/admin/pedido/pagar"
							method="post" modelAttribute="pedido">
							<form:input class="form-control" path="id" type="hidden" />

							<div class="form-group">
								<c:if test="${pedido.pagamento == 'PENDENTE'}">
									<input type="submit" value="TORNAR PEDIDO PAGO"
										class="btn btn-primary" />
								</c:if>
								<c:if test="${pedido.pagamento == 'PAGO'}">
									<input type="submit" value="TORNAR PEDIDO PENDENTE"
										class="btn btn-primary" />
								</c:if>


							</div>
						</form:form>

					</div>

				</div>
			</div>
		</div>
	</div>
</myTags:template>