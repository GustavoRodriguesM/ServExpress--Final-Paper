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
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div class="page-wrapper">
		<div class="container-fluid">
			<div class="row content">
				<div class="col-md-8 offset-md-2">
					<h4 class="text-center">Pedidos para buscar</h4>
					<div class="table-responsive">

						<table class="table color-table info-table">
							<thead>
								<tr>
									<th>#</th>
									<th>Data do pedido</th>
									<th>Valor Total</th>
									<th>Pagamento</th>
									<th>Local de entrega</th>
									<th>Ação</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="pedido" items="${pedidosParaBuscar}">
									<tr>
										<td><b><a
											href="<c:url value='/entregador/pedido/${pedido.id}'/>">${pedido.id}</a></b></td>

										<td><fmt:formatDate value="${pedido.dataEmissao.time}"
												pattern="dd/MM/yyyy HH:mm" /></td>
										<td>R$ ${pedido.total}</td>
										<td>${pedido.pagamento.descricao}</td>
										<td>${pedido.endereco.rua},${pedido.endereco.numero}-
											${pedido.endereco.bairroEntity.nome}</td>
										<td>
											<form:form method="post" servletRelativeAction="/entregador/pedido/encaminhar">
												<input type="hidden" name="id" value="${pedido.id}"> <input type="submit"
													class="btn btn-primary" value="Buscar pedido" />
											</form:form>
										</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<hr/>
			<div class="row  content">
				<div class="col-md-8 offset-md-2">
					<h4 class="text-center">Pedidos para entregar</h4>
					<div class="table-responsive">

						<table class="table color-table info-table">
							<thead>
								<tr>
									<th>#</th>
									<th>Data do pedido</th>
									<th>Valor Total</th>
									<th>Pagamento</th>
									<th>Local de entrega</th>
									<th>Ação</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="pedido" items="${pedidosParaEntregar}">
									<tr>
										<td><b><a
											href="<c:url value='/entregador/pedido/${pedido.id}'/>">${pedido.id}</a></b></td>

										<td><fmt:formatDate value="${pedido.dataEmissao.time}"
												pattern="dd/MM/yyyy HH:mm" /></td>
										<td>R$ ${pedido.total}</td>
										<td>${pedido.pagamento.descricao}</td>
										<td>${pedido.endereco.rua},${pedido.endereco.numero}-
											${pedido.endereco.bairroEntity.nome}</td>
										<td>
											<form:form method="post" servletRelativeAction="/entregador/pedido/entregar">
												<input type="hidden" name="id" value="${pedido.id}"> <input type="submit"
													class="btn btn-primary" value="Pedido entregue" />
											</form:form>
										</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</myTags:template>
