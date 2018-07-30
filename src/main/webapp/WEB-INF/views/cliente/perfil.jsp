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
					<h3 class="text-themecolor pull-left">Perfil</h3>
					<ol class="breadcrumb pull-right">
						<li class="breadcrumb-item"><a href="/cliente/dashboard">Home</a></li>
						<li class="breadcrumb-item active">Perfil</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 offset-md-3 content">

					<div class="tab-pane" id="settings">
						<form:form method="POST" servletRelativeAction="/cliente/perfil"
							modelAttribute="cliente">

							<div class="col-md-12">

								<p class="alert-danger text-center">${message}</p>
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-6">
											<label for="exampleInputName">Nome Completo</label>
											<div class="alert-danger text-center">
												<form:errors path="usuario.nome" />
											</div>
											<form:input class="form-control" type="text" path="usuario.nome"
												placeholder="Digite seu nome" />
										</div>
										<div class="col-md-6">

											<label>Email</label>
											<div class="alert-danger text-center">
												<form:errors path="usuario.email" />
											</div>
											<form:input class="form-control" type="email"
												path="usuario.email" placeholder="Digite seu email" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-6">
											<label>CPF</label>
											<div class="alert-danger text-center">
												<form:errors path="cpf" />
											</div>
											<form:input class="form-control" path="cpf" type="text"
												placeholder="Digite seu CPF" />
										</div>
										<div class="col-md-6">
											<label>Contato</label>
											<div class="alert-danger text-center">
												<form:errors path="usuario.contato" />
											</div>
											<form:input class="form-control" path="usuario.contato"
												type="text" placeholder="Digite seu numero para contato"
												maxlength="12" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>Observações</label>
									<div class="alert-danger text-center">
										<form:errors path="observacao" />
									</div>
									<form:textarea class="form-control" path="observacao" />
										
								</div>

								<form:input type="hidden" path="id" />
								<form:input type="hidden" path="usuario.dataCadastro" />
								<form:input type="hidden" path="usuario.senha" />
								<form:input type="hidden" path="usuario.id" />
								<form:input type="hidden" path="usuario.confirmarSenha" value="${cliente.usuario.senha}"/>
								<form:input type="hidden" path="endereco.id" />

								<hr />
								<div class="form-group">
									<p class="text-center">Endereço</p>
									<div class="form-group">
										<label>CEP</label>
										<div class="alert-danger text-center">
											<form:errors path="endereco.cep" />
										</div>
										<form:input type="text" class="form-control"
											path="endereco.cep" id="cep" placeholder="Digite seu CEP" />
									</div>
									<div class="form-row">
										<div class="col-md-4">
											<label>Rua</label>
											<div class="alert-danger text-center">
												<form:errors path="endereco.rua" />
											</div>
											<form:input class="form-control" id="rua" path="endereco.rua"
												type="text" placeholder="Rua" />
										</div>
										<div class="col-md-2">
											<label>Numero/APT</label>
											<div class="alert-danger text-center">
												<form:errors path="endereco.numero" />
											</div>
											<form:input class="form-control" path="endereco.numero"
												type="text" placeholder="Numero" />
										</div>
										<div class="col-md-6">
											<label>Bairro</label> <select class="form-control"
												name="endereco.bairroEntity">
												<option value="${cliente.endereco.bairroEntity.id}">${cliente.endereco.bairroEntity.nome}</option>
												<c:forEach var="bairro" items="${bairros}">
													<option value="${bairro.id}">${bairro.nome}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<input type="submit" class="btn btn-primary btn-block"
									value="Registrar" />

							</div>
						</form:form>
					</div>

				</div>
			</div>

		</div>
		<footer class="footer"> &copy; 2018 ServExpress por BlackCode
		</footer>
	</div>

</myTags:template>