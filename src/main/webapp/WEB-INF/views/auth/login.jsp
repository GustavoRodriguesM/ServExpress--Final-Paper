<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
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
<title>Login - ServExpress</title>
<link href="/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/assets/css/pages/login.css" rel="stylesheet">
<link href="/assets/css/style.css" rel="stylesheet">
<link href="/assets/css/colors/default.css" id="theme" rel="stylesheet">

<script src="<c:url value='/assets/vendor/jquery/jquery.min.js'/>"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
$( document ).ready(function() {
		${sweetAlert}
})
</script>
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
				<div class="card-body">
					<c:if test="${param.error != null}">
						<p class="text-center alert-danger">
							<fmt:message key="invalid.usernamePassword" />
						</p>
					</c:if>
					<c:if test="${param.logout != null}">
						<p class="text-center alert-success">
							<fmt:message key="logout" />
						</p>
					</c:if>

					${message}

					<form:form class="form-horizontal form-material"
						servletRelativeAction="/login" method="post">

						<h3 class="box-title m-b-20">Por favor, logue-se</h3>
						<div class="form-group ">
							<div class="col-xs-12">
								<input class="form-control" name="username" type="text"
									required="" placeholder="Digite seu email">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<input class="form-control" name="password" type="password"
									required="" placeholder="Digite sua senha">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-12">
								<a href="<c:url value='/reset'/>" id="to-recover"
									class="text-dark pull-right"><i class="fa fa-lock m-r-5"></i>
									Esqueceu sua senha?</a>
							</div>
						</div>
						<div class="form-group text-center">
							<div class="col-xs-12 p-b-20">
								<button class="btn btn-block btn-lg btn-info btn-rounded"
									type="submit">Entrar</button>
							</div>
						</div>

						<div class="form-group m-b-0">
							<div class="col-sm-12 text-center">
								Ainda não tem uma conta? <a
									href="/registrar"
									class="text-info m-l-5"><b>Registre-se!</b></a>
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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		$(document).ready(function() {
			$
			{
				sweetAlert
			}
		});
	</script>
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