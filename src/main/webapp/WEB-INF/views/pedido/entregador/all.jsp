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
			<div class="row page-titles">
				<div class="col-md-12 content align-self-center">
					<h3 class="text-themecolor pull-left">Pedidos</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a href="<c:url value='/entregador/pedido'/>">Pedido</a></li>
						<li class="breadcrumb-item active">Todos</li>
					</ol>
				</div>
			</div>
			<div class="row content">
				<div class="col-md-8 offset-md-2">
				${message}

					<div class="table-responsive">

						<table class="table color-table info-table">
							<thead>
								<tr>
									<th>#</th>
									<th>Data do pedido</th>
									<th>Valor Total</th>
									<th>Pagamento</th>
									<th>Status</th>
									<th>Local de entrega</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="pedido" items="${pedidos}">
									<tr>
										<td><b><a href="<c:url value='/entregador/pedido/${pedido.id}'/>">${pedido.id}</a></b></td>

										<td><fmt:formatDate value="${pedido.dataEmissao.time}"
												pattern="dd/MM/yyyy HH:mm" /></td>
										<td>R$ ${pedido.total}</td>
										<td>${pedido.pagamento.descricao}</td>
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
										<td>${pedido.endereco.rua},${pedido.endereco.numero}-
											${pedido.endereco.bairroEntity.nome}</td>

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
