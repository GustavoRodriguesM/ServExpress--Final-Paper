<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/images/favicon.png">
<title>ServExpress</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/colors/default.css" id="theme" rel="stylesheet">

<style>
.progress {
	height: 0.25em !important;
}

.content {
	padding: 0 !important;
}
</style>
</head>

<body class="fix-header card-no-border fix-sidebar">
	<div class="preloader">
		<div class="loader">
			<div class="loader__figure"></div>
			<p class="loader__label">ServExpress</p>
		</div>
	</div>
	<div id="main-wrapper">

		<header class="topbar">
			<nav class="navbar top-navbar navbar-expand-md navbar-light">

				<div class="navbar-header navbar-index">
					<a class="navbar-brand" href="/"> <b> <img
							src="assets/images/logo-icon.png" alt="homepage"
							class="dark-logo" />


					</b> <span> <img src="assets/images/logo-text.png"
							alt="homepage" class="dark-logo" />

					</span>
					</a>
				</div>
				<div class="navbar-collapse">
					<ul class="navbar-nav mr-auto">

						<li class="nav-item"><a
							class="nav-link nav-toggler hidden-md-up waves-effect waves-dark"
							href="javascript:void(0)"><i class="fa fa-bars"></i></a></li>


					</ul>

					<ul class="navbar-nav ml-auto" style="margin-right: 4em;">
						<li class="nav-item"><a class="nav-link" href="#main-wrapper">Home</a>
						</li>

						<li class="nav-item"><a class="nav-link" href="#servicos">Serviços</a>
						</li>

						<li class="nav-item"><a class="nav-link" href="#contato">Contato</a></li>
						<li class="nav-item"><a class="nav-link" href="/login">Entrar</a></li>
					</ul>


				</div>
			</nav>


		</header>

		<div>
			<div class="container-fluid">
				<div class="row content">


					<div class="container-fluid" id="home">
						<div class="row">
							<div class="carousel-item active">
								<img class="d-block w-100 teste"
									src="http://www.perol.com.br/static/img/bkg-lavanderia.jpg"
									alt="First slide" style="height: 500px;">
								<div class="carousel-caption d-none d-md-block">
									<h3>
										<b style="color: #FFF">SERV EXPRESS</b>
									</h3>
									<p>Tradição em lavagem de roupas!</p>
								</div>
							</div>

						</div>
					</div>

					<div class="container">

						<section id="sobre">
							<div class="row">
								<div class="col-md-12">
									<h3 class="text-center">Sobre</h3>
								</div>
								<div class="col-md-12">
									<div class="row" style="margin-top: 10px;">
										<div class="col-md-4">
											<h4 class="text-center">Missão</h4>
											<p class="text-justify">A nossa missão é proporcionar
												facilidade e qualidade de lavagem para as pessoas em relação
												a suas roupas. Fazendo isso de forma sustentável, econômica
												e eficaz.</p>
										</div>
										<div class="col-md-4">
											<h4 class="text-center">Visão</h4>
											<p class="text-justify">Ser reconhecida como uma empresa
												líder pelos serviços de qualidade, pelo comprometimento com
												o bem-estar dos clientes e com a sustentabilidade, no
												período de cinco anos.</p>
										</div>
										<div class="col-md-4">
											<h4 class="text-center">Valores</h4>
											<div class="text-center">
												Honestidade<br /> Ética e transparência<br /> Confiança e
												superação<br /> Respeito à vida, pessoas e meio ambiente<br />
												Segurança<br /> Responsabilidade social e ambiental<br />

											</div>

										</div>

									</div>
								</div>
							</div>
						</section>

						<hr>

						<section>
							<div class="row">
								<div class="col-sm-12" id="servicos">
									<h3 class="text-center">Um pouco da nossa Empresa</h3>
								</div>
								<div class="col-sm-4">
									<h3 class="text-center">Meio Ambiente</h3>
									<p class="text-justify">A lavanderia Serv Express
										preocupada com o meio ambiente e com o desperdício de água,
										faz o reaproveitamento da mesma. Ela utiliza a água para reuso
										em outras tarefas na lavanderia, como lavar os vários setores
										da empresa, limpar o chão do estabelecimento e a calçada.
										Assim a água usada, não é desperdiçada. O impacto do
										reaproveitamento da água na lavanderia, ajuda com a diminuição
										dos custos de água da empresa, e consequentemente ela não
										precisa atribuir esse custo aos serviços, além de manter a
										consciência ambiental.</p>
								</div>
								<div class="col-sm-4">
									<h3 class="text-center">A Empresa</h3>
									<p class="text-justify">A lavanderia ServeExpress é uma
										microempresa especializada em lavagem e tinturaria, fundada em
										1991, que fica localizada em um edificio comercial na Rua José
										Maria Lisboa, Nº 612, Jardim Paulista, São Paulo - SP, CEP
										01423-000, Segundo Andar.</p>
								</div>
								<div class="col-sm-4">
									<h3 class="text-center">Serviços e Qualidade</h3>
									<p class="text-justify">A variedade de roupas, tecidos,
										capas e cobertores que a empresa consegue se encarregar de
										limpar é simplesmente enorme, sempre oferecendo o melhor
										serviço possível tanto no processo de lavagem, quanto de
										secagem e armazenamento, até o destino final: o cliente
										satisfeito com o serviço prestado.</p>
								</div>
							</div>
						</section>
						<section id="contato">
							<div class="row">
								<div class="col-md-12">
									<h3 class="text-center">Entre em Contato</h3>
								</div>
								<div class="col-md-12">
									<p class="text-justify font-18">Estamos sempre procurando
										nos aperfeiçoar, estamos abertos a sugestões para melhorar e
										fazemos questão de saber o que vocês tem a nos dizer.</p>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-8">
									<form:form id="contact_form" action="/contato" method="POST"
										class="form-horizontal">
										<div class="form-group">
											<label for="name" class="col-sm-2 control-label">Seu
												Nome:</label>
											<div class="col-sm-10">
												<input id="name" class="form-control" name="nome"
													type="text" placeholder="Nome" />
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-sm-2 control-label">Seu
												email:</label>
											<div class="col-sm-10">
												<input id="email" class="form-control" name="email"
													type="email" placeholder="email@email.com" />
											</div>
										</div>
										<div class="form-group">
											<label for="message" class="col-sm-2 control-label">Mensagem:</label>
											<textarea id="message" class="form-control" name="mensagem"
												rows="7" cols="30"></textarea>
										</div>
										<input id="submit_button" type="submit"
											class="btn btn-default" value="Enviar" />
									</form:form>
								</div>

							</div>
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="assets/vendor/jquery/jquery.min.js"></script>

	<script src="assets/vendor/bootstrap/js/popper.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/perfect-scrollbar.jquery.min.js"></script>
	<script src="assets/js/waves.js"></script>
	<script src="assets/js/custom.min.js"></script>

</body>

</html>