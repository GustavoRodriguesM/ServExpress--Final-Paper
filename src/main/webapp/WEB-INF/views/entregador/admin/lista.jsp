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
					<h3 class="text-themecolor pull-left">Entregadores</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/dashboard'/>">Home</a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/entregador'/>">Entregador</a></li>
						<li class="breadcrumb-item active">Todos</li>
					</ol>
				</div>
			</div>
			<div class="content">

				<div class="row">
					<div class="col-md-12">${message}</div>

					<div class="col-md-8 offset-md-2 table-responsive ">

						<div class="table-responsive">

							<table class="table table-hover color-table info-table">
								<thead>
									<tr>
										<th>Nome</th>
										<th>RG</th>
										<th>CNH</th>
										<th>Status do entregador</th>
										<th>Remover</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="entregador" items="${entregadores}">
										<tr>
											<td><a href="/admin/entregador/${entregador.id}">${entregador.usuario.nome}</a></td>
											<td>${entregador.rg}</td>
											<td>${entregador.cnh}</td>
											<td><b>${entregador.status.descricao}</b></td>
											<td>
												<c:if test="${entregador.status == 'EMPREGADO'}">
													<form:form
														servletRelativeAction="/admin/entregador/desativar">
														<input type="hidden" name="id" value="${entregador.id}" />
														<input type="submit" class="btn btn-danger"
															value="Desativar" />
													</form:form>
												</c:if>
												<c:if test="${entregador.status == 'DEMITIDO'}">
													<form:form
														servletRelativeAction="/admin/entregador/ativar">
														<input type="hidden" name="id" value="${entregador.id}" />
														<input type="submit" class="btn btn-success"
															value="Reativar" />
													</form:form>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>

						<div class="col-md-12 text-center">
							<a class="btn btn-primary"
								href="<c:url value='/admin/entregador/add'/>">Adicionar
								entregador</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer class="footer"> © 2018 ServExpress por BlackCode </footer>
	</div>

</myTags:template>