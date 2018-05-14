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
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="<c:url value='/assets/images/favicon.png'/>">
<title>ServExpress</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link href="<c:url value='/assets/css/style.css'/>" rel="stylesheet">
<link href="<c:url value='/assets/css/colors/default.css'/>" id="theme"
	rel="stylesheet">

<script src="<c:url value='/assets/vendor/jquery/jquery.min.js'/>"></script>

<script src="<c:url value='/assets/vendor/bootstrap/js/popper.min.js'/>"></script>
<script
	src="<c:url value='/assets/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
<script
	src="<c:url value='/assets/js/perfect-scrollbar.jquery.min.js'/>"></script>
<script src="<c:url value='/assets/js/waves.js'/>"></script>
<script src="<c:url value='/assets/js/sidebarmenu.js'/>"></script>
<script src="<c:url value='/assets/js/custom.min.js'/>"></script>
<script src="<c:url value='/assets/js/jquery.mask.js'/>"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.min.js"></script>
<script src="<c:url value='/assets/js/mask.js'/>"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script>
	$(document).ready(function() {
		$
		{
			sweetAlert
		}
	})
</script>

<style>
.progress {
	height: 0.25em !important;
}
</style>
</head>

<body class="card-no-border"
	style="background: #f6f9fa !important; position: relative !important; width: 100% !important">
	<div class="preloader">
		<div class="loader">
			<div class="loader__figure"></div>
			<p class="loader__label">ServExpress</p>
		</div>
	</div>


	<div class="container-fluid">

		<div class="row">

			<div class="col-md-4 offset-md-4">

				<div class="content" style="margin-top: 10em; margin-bottom: 10em">
					<form:form class="form-horizontal form-material"
						servletRelativeAction="/reset/password" modelAttribute="usuario">
						<h3 class="box-title m-b-20">Alteração de senha</h3>
						<div class="form-group ">
							<div class="col-xs-12">
								<div class="alert-danger text-center">
									<form:errors path="senha" />
								</div>
								<form:input class="form-control" type="password"
									placeholder="Digite a nova senha" path="senha" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<div class="alert-danger text-center">
									<form:errors path="confirmarSenha" />
								</div>
								<form:input class="form-control" type="password"
									placeholder="Confirme a senha" path="confirmarSenha" />
							</div>
						</div>
						<input type="hidden" name="token" value="${token}" />
						<div class="form-group text-center">
							<div class="col-xs-12">
								<button
									class="btn btn-primary btn-block text-uppercase waves-effect waves-light"
									type="submit">Alterar</button>
							</div>
						</div>
					</form:form>
				</div>

			</div>

		</div>

	</div>



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