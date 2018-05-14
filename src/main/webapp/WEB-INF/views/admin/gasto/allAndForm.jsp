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
					<h3 class="text-themecolor pull-left">Gastos</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/dashboard'/>">Home</a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/gasto'/>">Gasto</a></li>
						<li class="breadcrumb-item active">Todos / Formulário</li>
					</ol>
				</div>
			</div>
			<div class="content">

				<div class="row">

					<div class="col-md-12">${message}</div>

					<div class="col-md-6 offset-md-3">
						<form:form class="form-material" method="POST"
							modelAttribute="gasto" servletRelativeAction="/admin/gasto">

							<div class="form-group has-error">

								<form:input path="id" type="hidden" />
								<label>Recurso</label>
								<div class="alert-danger text-center">
									<span><form:errors path="recurso" /></span>
								</div>
								<form:input class="form-control" path="recurso"
									placeholder="Digite o recurso" autofocus="true" />
							</div>
							<div class="form-group">
								<label>Valor</label>
								<div class="alert-danger text-center">
									<span class="text-center"><form:errors path="valor" /></span>
								</div>
								<form:input class="form-control" type="numeric" path="valor"
									placeholder="Digite o valor do recurso" />
							</div>

							<div class="form-group">
								<label>Classificação</label>
								<div class="alert-danger text-center">
									<span class="text-center"><form:errors
											path="classificacao" /></span>
								</div>
								<form:radiobuttons items="${classificacoes}"
									path="classificacao" class="form-control"
									placeholder="Digite o valor do recurso" />
							</div>
							<input type="submit" class="btn btn-primary btn-block"
								value="Registrar" />
						</form:form>

					</div>

					<div class="col-md-8 offset-md-2">
						<h4 class="text-center" style="margin-top: 2em">Gastos
							cadastradas</h4>

						<div class="table-responsive">


							<table class="table color-table info-table">
								<thead>
									<tr>
										<th>Recurso</th>
										<th>Valor</th>
										<th>Classificação</th>
										<th>Editar</th>
										<th>Excluir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="gasto" items="${gastos}">
										<tr>
											<td>${gasto.recurso}</td>
											<td>R$ ${gasto.valor}</td>
											<td>${gasto.classificacao}</td>
											<td><a href="/admin/gasto/${gasto.id}/editar"
												class="btn btn-primary">Editar</a></td>
											<c:if test="${gasto.deletadoEm == null}">
												<td><a class="btn btn-danger" style="color: #fff"
													data-toggle="modal" data-target="#myModal${gasto.id}">Remover</a></td>




												<div class="modal fade" id="myModal${gasto.id}"
													tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>

															</div>
															<div class="modal-body">Deseja realmente remover
																este recurso (${gasto.recurso}) ?</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancelar</button>
																<form:form
																	servletRelativeAction="/admin/gasto/${gasto.id}/delete"
																	method="post">
																	<input type="submit" class="btn btn-primary"
																		value="Confirmar" />
																</form:form>
															</div>
														</div>
													</div>
												</div>
											</c:if>

											<c:if test="${gasto.deletadoEm != null}">
												<td><a class="btn btn-success" style="color: #fff"
													data-toggle="modal" data-target="#myModal${gasto.id}">Reativar</a></td>




												<div class="modal fade" id="myModal${gasto.id}"
													tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>

															</div>
															<div class="modal-body">Deseja ativar este recurso
																(${gasto.recurso}) ?</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancelar</button>
																<form:form
																	servletRelativeAction="/admin/gasto/${gasto.id}/reactivate"
																	method="post">
																	<input type="submit" class="btn btn-primary"
																		value="Confirmar" />
																</form:form>
															</div>
														</div>
													</div>
												</div>
											</c:if>

										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>

					</div>
				</div>

			</div>
		</div>
		<footer class="footer"> © 2018 ServExpress por BlackCode </footer>
	</div>

</myTags:template>
