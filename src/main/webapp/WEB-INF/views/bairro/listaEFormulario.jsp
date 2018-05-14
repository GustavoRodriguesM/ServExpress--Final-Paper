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
		$(document).ready(function() {
			$
			{
				sweetAlert
			}
		});
	</script>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div class="page-wrapper">
		<div class="container-fluid">
			<div class="row page-titles">
				<div class="col-md-12 content align-self-center">
					<h3 class="text-themecolor pull-left">Bairros</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/dashboard'/>">Home</a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value='/admin/bairro'/>">Bairro</a></li>
						<li class="breadcrumb-item active">Todos / Formulário</li>
					</ol>
				</div>
			</div>

			<div class="content">

				<div class="row">
					<div class="col-md-12">${message}</div>

					<div class="col-md-6 offset-md-3">
						<form:form class="form-material" method="POST"
							modelAttribute="bairro" servletRelativeAction="/admin/bairro">

							<div class="form-group">

								<form:input path="id" type="hidden" />
								<label>Nome do Bairro</label>
								<div class="alert-danger text-center">
									<form:errors path="nome" />
								</div>
								<form:input class="form-control" path="nome"
									placeholder="Digite o nome do bairro" />

							</div>
							<input type="submit" class="btn btn-primary btn-block"
								value="Registrar" />

						</form:form>

					</div>
				</div>

				<div class="row">
					<div class="col-md-8 offset-md-2">
						<h4 class="text-center" style="margin-top: 2em">Bairros
							cadastrados</h4>

						<div class="table-responsive">

							<table class="table color-table info-table">
								<thead>
									<tr>
										<th>Bairro</th>
										<th>Editar</th>
										<th>Excluir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="bairro" items="${bairros}">
										<tr>
											<td>${bairro.nome}</td>
											<td><a href="/admin/bairro/${bairro.id}/editar"
												class="btn btn-primary">Editar</a></td>
											<c:if test="${bairro.deletadoEm == null}">
												<td><a class="btn btn-danger" style="color: #fff"
													data-toggle="modal" data-target="#myModal${bairro.id}">Remover</a></td>




												<div class="modal fade" id="myModal${bairro.id}"
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
																este bairro (${bairro.nome}) ?</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancelar</button>
																<form:form
																	servletRelativeAction="/admin/bairro/${bairro.id}/delete"
																	method="post">
																	<input type="submit" class="btn btn-primary"
																		value="Confirmar" />
																</form:form>
															</div>
														</div>
													</div>
												</div>
											</c:if>

											<c:if test="${bairro.deletadoEm != null}">
												<td><a class="btn btn-success" style="color: #fff"
													data-toggle="modal" data-target="#myModal${bairro.id}">Reativar</a></td>




												<div class="modal fade" id="myModal${bairro.id}"
													tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>

															</div>
															<div class="modal-body">Deseja ativar este bairro
																(${bairro.nome}) ?</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancelar</button>
																<form:form
																	servletRelativeAction="/admin/bairro/${bairro.id}/reactivate"
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