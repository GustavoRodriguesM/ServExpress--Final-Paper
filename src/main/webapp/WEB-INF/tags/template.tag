<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@attribute name="bodyName" required="true"%>


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
<script src="<c:url value='/assets/js/stom-websocket.min.js'/>"></script>
<script src="<c:url value='/assets/js/sidebarmenu.js'/>"></script>
<script src="<c:url value='/assets/js/custom.min.js'/>"></script>
<script src="<c:url value='/assets/js/jquery.mask.js'/>"></script>
<script src='<c:url value='/assets/js/viacep.js'/>'></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.min.js"></script>
<script src="<c:url value='/assets/js/mask.js'/>"></script>
<script src="/assets/js/jquery.PrintArea.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style>
.progress {
	height: 0.25em !important;
}
</style>


	<script>
$( document ).ready(function() {
		${sweetAlert}
})
</script>
</head>

<body class="fix-header card-no-border fix-sidebar">
	<div class="preloader">
		<div class="loader">
			<div class="loader__figure"></div>
			<p class="loader__label">ServExpress</p>
		</div>
	</div>
	<security:authentication property="principal" var="user" />
	<div id="main-wrapper">

		<header class="topbar">
			<nav class="navbar top-navbar navbar-expand-md navbar-light">

				<div class="navbar-header">
					<a class="navbar-brand" href="<c:url value='/'/>"> <b> <img
							src="<c:url value='/assets/images/logo-icon.png'/>"
							alt="homepage" class="dark-logo" />


					</b> <span> <img src="/assets/images/logo-text.png"
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

					<ul class="navbar-nav my-lg-0">

						<li class="nav-item dropdown u-pro"><a
							class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
							href="" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"><img src="/assets/images/logo-icon.png"
								alt="user" class="" /> <span class="hidden-md-down">${user.nome}
									&nbsp;</span> </a></li>
					</ul>
				</div>
			</nav>
		</header>


		<jsp:doBody />



	</div>

	<script>
		$(document).ready(function() {
			$("#print").click(function() {
				var mode = 'iframe'; //popup
				var close = mode == "popup";
				var options = {
					mode : mode,
					popClose : close
				};
				$("div.printableArea").printArea(options);
			});
		});
	</script>
	<script type="text/javascript">
		$('#myModal').on('shown.bs.modal', function() {
			$('#myInput').focus()
		})

		$(document).ready(function() {
			$('#cpf').mask('000.000.000-00');
			$('#rg').mask('00.000.000-0');
			$('#cep').mask('00000-000');
		});
	</script>
</body>

</html>
