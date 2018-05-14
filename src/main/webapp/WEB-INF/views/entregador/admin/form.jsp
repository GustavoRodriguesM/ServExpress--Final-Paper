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
						<li class="breadcrumb-item active">Formulário</li>
					</ol>
				</div>
			</div>
			<div class="content">

				<div class="row">
					<div class="col-md-12">${message}</div>
					<div class="col-md-8 offset-md-2">
						<form:form method="POST" class="form-material"
							modelAttribute="entregador"
							servletRelativeAction="/admin/entregador">

							<div class="form-group">
								<div class="form-row">
									<form:hidden path="id" />
									<form:hidden path="usuario.id" />
									<form:hidden path="endereco.id" />
									<div class="col-md-6">
										<label>Nome Completo</label>

										<div class="alert-danger text-center">
											<form:errors path="usuario.nome" />
										</div>
										<form:input class="form-control" path="usuario.nome"
											placeholder="Digite seu nome" />
									</div>
									<div class="col-md-6">
										<label>Email</label>
										<div class="alert-danger text-center">
											<form:errors path="usuario.email" />
										</div>
										<form:input class="form-control" path="usuario.email"
											type="email" placeholder="Digite seu email" />
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


							<c:if test="${entregador.id != null}">

								<form:hidden path="usuario.senha" />
								<form:hidden path="usuario.dataCadastro" />
								<form:hidden path="usuario.confirmarSenha" />


							</c:if>

							<c:if test="${entregador.id == null}">


								<div class="form-group">
									<div class="form-row">
										<div class="col-md-6">
											<label>Senha</label>
											<div class="alert-danger text-center">
												<form:errors path="usuario.senha" />
											</div>
											<form:input class="form-control" path="usuario.senha"
												type="password" placeholder="Minimo de 7 caracteres" />
										</div>
										<div class="col-md-6">
											<label>Confirmação de senha</label>
											<div class="alert-danger text-center">
												<form:errors path="usuario.confirmarSenha" />
											</div>
											<input class="form-control" name="usuario.confirmarSenha"
												type="password" placeholder="Senha">
										</div>
									</div>
								</div>
							</c:if>

							<form:input type="hidden" path="status" value="EMPREGADO" />



							<hr />

							<div class="form-group">
								<p class="text-center">Informações pessoais</p>
								<div class="form-row">
									<div class="col-md-6">
										<label>RG</label>
										<div class="alert-danger text-center">
											<form:errors path="rg" />
										</div>
										<form:input class="form-control" path="rg"
											placeholder="Registro Geral" />
									</div>
									<div class="col-md-6">
										<label>CNH</label>
										<div class="alert-danger text-center">
											<form:errors path="cnh" />
										</div>
										<form:input class="form-control" path="cnh"
											placeholder="Carteira Nacional de Habilitação" />
									</div>
								</div>
							</div>

							<hr />
							<div class="form-group">
								<p class="text-center">Endereço</p>
								<div class="form-group">
									<label>CEP</label>
									<div class="alert-danger text-center">
										<form:errors path="endereco.cep" />
									</div>
									<form:input class="form-control" id="cep" path="endereco.cep"
										onblur="pesquisacep(this.value);" placeholder="Digite seu CEP" />
								</div>
								<div class="form-row">
									<div class="col-md-5">
										<label>Rua</label>
										<div class="alert-danger text-center">
											<form:errors path="endereco.rua" />
										</div>
										<form:input class="form-control" id="rua" path="endereco.rua"
											placeholder="Rua" />
									</div>
									<div class="col-md-2">
										<label>Numero/APT</label>
										<div class="alert-danger text-center">
											<form:errors path="endereco.numero" />
										</div>
										<form:input class="form-control" path="endereco.numero"
											placeholder="Numero" />
									</div>
									<div class="col-md-5">
										<label>Bairro</label>
										<div class="alert-danger text-center">
											<form:errors path="endereco.bairro" />
										</div>
										<form:input class="form-control" id="bairro"
											path="endereco.bairro" placeholder="Bairro" />
									</div>
								</div>


							</div>
							<input type="submit" class="btn btn-primary btn-block"
								value="Registrar" />
						</form:form>

					</div>

				</div>
			</div>
		</div>
		<footer class="footer"> © 2018 ServExpress por BlackCode </footer>
	</div>
</myTags:template>
