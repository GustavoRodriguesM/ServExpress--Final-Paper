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
						<li class="breadcrumb-item active">${entregador.usuario.nome}</li>
					</ol>
				</div>
			</div>
			<div class="content">

				<div class="row">
					<div class="col-md-12">${message}</div>
					<div class="col-md-10 offset-md-1">
						<div class="row">
							<div class="col-md-12 text-center">
								<span><a class="btn btn-danger"
									href="<c:url value='/admin/entregador/${entregador.id}/editar'/>">Editar</a></span>
							</div>
						</div>
						<div class="row" style="margin-top: 2em">

							<div class="col-md-4 text-center">
								<span>Nome completo<br /> <b>${entregador.usuario.nome}</b></span>
							</div>
							<div class="col-md-4 text-center">
								<span>e-Mail<br /> <b>${entregador.usuario.email}</b></span>
							</div>

							<div class="col-md-4 text-center">
								<span>Registro Geral (RG)<br /> <b>${entregador.rg}</b></span>
							</div>

						</div>
						<br />
						<hr>
						<br />
						<div class="row">

							<div class="col-md-4 text-center">
								<span>Carteira Nacional de Habilitação (CNH)<br /> <b>${entregador.cnh}</b></span>
							</div>

							<div class="col-md-4 text-center">
								<span>Cadastro de Pessoa Fisíca (CPF)<br /> <b>${entregador.cpf}</b></span>
							</div>

							<div class="col-md-4 text-center">
								<span>Status de cadastro<br /> <b>${entregador.status.descricao}</b></span>
							</div>

						</div>

						<br />
						<hr>
						<br />

						<div class="row">
							<div class="col-md-12 text-center">
								<h4>Endereço</h4>
							</div>
							<div class="col-md-4 text-center">
								<span>Rua<br /> <b>${entregador.endereco.rua}</b></span>
							</div>
							<div class="col-md-4 text-center">
								<span>Número<br /> <b>${entregador.endereco.numero}</b></span>
							</div>
							<div class="col-md-4 text-center">
								<span>Bairro<br /> <b>${entregador.endereco.bairro}</b></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer class="footer"> © 2018 ServExpress por BlackCode </footer>
	</div>
</myTags:template>
