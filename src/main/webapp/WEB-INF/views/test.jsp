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
				<div class="col-md-5 align-self-center">
					<h3 class="text-themecolor">Blank Page</h3>
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">Blank Page</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
				
					<span class="label label-custom">TESTE</span>
					<span class="label label-success">TESTE</span>
					<span class="label label-info">TESTE</span>
					<span class="label label-warning">TESTE</span>
					<span class="label label-danger">TESTE</span>
					<span class="label label-purple">TESTE</span>

				</div>
			</div>

		</div>
		<footer class="footer"> &copy; 2018 ServExpress por BlackCode </footer>
	</div>

</myTags:template>