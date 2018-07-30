<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="pt-br" style="background: #f6f9fa !important;">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/assets/images/favicon.png">
<title>Registro - ServExpress</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="/assets/css/pages/login.css" rel="stylesheet">
<link href="/assets/css/style.css" rel="stylesheet">
<link href="/assets/css/colors/default.css" id="theme" rel="stylesheet">

</head>

<body class="card-no-border"
	style="background: #f6f9fa !important; position: relative !important">
	<div class="preloader">
		<div class="loader">
			<div class="loader__figure"></div>
			<p class="loader__label">ServExpress</p>
		</div>
	</div>

	<section id="wrapper">
		<div class="container">

			<div class="row">
				<div class="col-md-8 offset-md-2 card" style="margin-top: 5em;">

					<div class="card-header text-center">
						<b>Cadastre-se para fazer pedidos!</b>
					</div>
					<div class="card-body">
						<form:form method="POST" class="form-material"
							servletRelativeAction="/registrar" modelAttribute="cliente">

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
											<form:input class="form-control" data-mask="999.999.999-99"
												path="cpf" type="text" placeholder="Digite seu CPF" />
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
									
									<label>Observaçoes</label>
									<div class="alert-danger text-center">
										<form:errors path="observacao" />
									</div>
									<form:textarea class="form-control"
										path="observacao" placeholder="Digite um observação em caso de ALERGIAS OU LIMITAÇÕES" cols="10"/>
								</div>

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
											<form:input class="form-control" type="password"
												path="usuario.confirmarSenha" placeholder="Senha" />
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
						<div class="text-center">
							<a class="d-block small mt-3" href="login">Já é registrado?
								Entre!</a> <a class="d-block small" href="/reset">Esqueceu sua
								senha?</a>
						</div>
					</div>


				</div>
			</div>

		</div>
	</section>

	<script src="/assets/vendor/jquery/jquery.min.js"></script>
	<script src="/assets/vendor/bootstrap/js/popper.min.js"></script>
	<script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

	<script src="<c:url value='/assets/js/jquery.mask.js'/>"></script>
	<script type="text/javascript">
		$(function() {
			$(".preloader").fadeOut();
		});
		$(function() {
			$('[data-toggle="tooltip"]').tooltip()
		});
		$(document).ready(function() {
			$('#cpf').mask('000.000.000-00');
			$('#rg').mask('00.000.000-0');
			$('#cep').mask('00000-000');
			
		});
	</script>

</body>

</html>
