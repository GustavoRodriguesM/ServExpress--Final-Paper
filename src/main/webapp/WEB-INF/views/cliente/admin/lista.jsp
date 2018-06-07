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
				<div class="col-md-12 align-self-center content">
					<h3 class="text-themecolor pull-left">Clientes</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a
							href="<c:url value='/cliente/pedido'/>">Cliente</a></li>
						<li class="breadcrumb-item active">Todos</li>
					</ol>
				</div>
			</div>
			<div class="row content">
				<div class="col-md-8 offset-md-2">
					<div class="table-responsive">

						<table class="table table-hover color-table info-table">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Email</th>
									<th>CPF</th>
									<th>Endereço</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cliente" items="${clientes}">
									<tr>
										<td><a href="/admin/cliente/${cliente.id}">${cliente.usuario.nome}</a></td>
										<td>${cliente.usuario.email}</td>
										<td>${cliente.cpf}</td>
										<td>${cliente.endereco.rua},${cliente.endereco.numero} -
											${cliente.endereco.bairroEntity.nome}</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="text-center">
						<a href="<c:url value='/admin/relatorio/clientes'/>" class="btn btn-themecolor btn-rounded">Relatório geral de clientes</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</myTags:template>