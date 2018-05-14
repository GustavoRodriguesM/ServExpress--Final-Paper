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
					<h3 class="text-themecolor pull-left">Roupas</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/dashboard'/>">Home</a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/roupa'/>">Roupa</a></li>
						<li class="breadcrumb-item active">Todas / Formulário</li>
					</ol>
				</div>
			</div>
			<div class="content">

				<div class="row">

					<div class="col-md-12">${message}</div>

					<div class="col-md-6 offset-md-3">
						<form:form class="form-material" method="POST"
							modelAttribute="roupa" servletRelativeAction="/admin/roupa">

							<div class="form-group">

								<form:input path="id" type="hidden" />
								<label>Nome da Roupa</label>
								<div class="alert-danger text-center">
									<span><form:errors path="descricao" /></span>
								</div>
								<form:input class="form-control" path="descricao"
									placeholder="Digite o nome da roupa" autofocus="true" />
							</div>
							<div class="form-group">
								<label>Preço</label>
								<div class="alert-danger text-center">
									<span class="text-center"><form:errors
											path="valorUnitario" /></span>
								</div>
								<form:input class="form-control" type="numeric"
									path="valorUnitario"
									placeholder="Digite o preço unitário da peça" />
							</div>
							<input type="submit" class="btn btn-primary btn-block"
								value="Registrar" />
						</form:form>

					</div>

					<div class="col-md-8 offset-md-2">
						<h4 class="text-center" style="margin-top: 2em">Roupas
							cadastradas</h4>
						<div class="table-responsive">

							<table class="table color-table info-table">
								<thead>
									<tr>
										<th>Roupa</th>
										<th>Preço unitário</th>
										<th>Editar</th>
										<th>Excluir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="roupa" items="${roupas}">
										<tr>
											<td>${roupa.descricao}</td>
											<td>R$ ${roupa.valorUnitario}</td>
											<td><a href="/admin/roupa/${roupa.id}/editar"
												class="btn btn-primary">Editar</a></td>
											<c:if test="${roupa.deletadoEm == null}">
												<td><a class="btn btn-danger" style="color: #fff"
													data-toggle="modal" data-target="#myModal${roupa.id}">Remover</a></td>




												<div class="modal fade" id="myModal${roupa.id}"
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
																este modelo de roupa (${roupa.descricao}) ?</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancelar</button>
																<form:form
																	servletRelativeAction="/admin/roupa/${roupa.id}/delete"
																	method="post">
																	<input type="submit" class="btn btn-primary"
																		value="Confirmar" />
																</form:form>
															</div>
														</div>
													</div>
												</div>
											</c:if>

											<c:if test="${roupa.deletadoEm != null}">
												<td><a class="btn btn-success" style="color: #fff"
													data-toggle="modal" data-target="#myModal${roupa.id}">Reativar</a></td>




												<div class="modal fade" id="myModal${roupa.id}"
													tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>

															</div>
															<div class="modal-body">Deseja ativar este modelo
																de roupa (${roupa.descricao}) ?</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancelar</button>
																<form:form
																	servletRelativeAction="/admin/roupa/${roupa.id}/reactivate"
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
