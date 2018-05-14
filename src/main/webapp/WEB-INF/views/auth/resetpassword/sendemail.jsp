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
<title>ServExpress</title>
<link href="/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
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
		<div class="login-register">
			<div class="login-box card">

				<div class="card-header text-center">
					<b>Alteração de senha</b>
				</div>
				<div class="card-body">

					<p>Digite seu email para receber uma mensagem com as instruções
						de como redefinir sua senha.</p>

					<form:form servletRelativeAction="/reset" method="post" modelAttribute="user">
						<div class="col-md-12">

							<div class="form-group">
								<input type="email" placeholder="Digite seu email" name="email"
									class="form-control" />

							</div>
							<div class="form-group text-center">
								<input type="submit" class="btn btn-primary" value="Enviar" />
							</div>
						</div>
					</form:form>

				</div>
			</div>
		</div>
	</section>

	<script src="/assets/vendor/jquery/jquery.min.js"></script>
	<script src="/assets/vendor/bootstrap/js/popper.min.js"></script>
	<script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".preloader").fadeOut();
		});
		$(function() {
			$('[data-toggle="tooltip"]').tooltip()
		});
	</script>

</body>

</html>