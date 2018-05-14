<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<security:authentication property="principal" var="user" />


<aside class="left-sidebar">

	<div class="scroll-sidebar">

		<nav class="sidebar-nav">
			<ul id="sidebarnav">

				<security:authorize access="hasRole('ROLE_ENTREGADOR')">

					<li><a class="waves-effect waves-dark"
						href="/entregador/dashboard" aria-expanded="false"><i
							class="fa fa-tachometer"></i><span class="hide-menu">Dashboard</span></a></li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/entregador/perfil'/>" aria-expanded="false"><i
							class="fa fa-user-circle-o"></i><span class="hide-menu">Perfil</span></a>
					</li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/entregador/pedido'/>" aria-expanded="false"><i
							class="fa fa-table"></i><span class="hide-menu">Pedidos</span></a></li>

				</security:authorize>

				<security:authorize access="hasRole('ROLE_CLIENTE')">

					<li><a class="waves-effect waves-dark"
						href="/cliente/dashboard" aria-expanded="false"><i
							class="fa fa-tachometer"></i><span class="hide-menu">Dashboard</span></a></li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/cliente/perfil'/>" aria-expanded="false"><i
							class="fa fa-user-circle-o"></i><span class="hide-menu">Perfil</span></a>
					</li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/cliente/pedido'/>" aria-expanded="false"><i
							class="fa fa-table"></i><span class="hide-menu">Pedidos</span></a></li>


				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMINISTRADOR')">

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/admin/dashboard'/>" aria-expanded="false"><i
							class="fa fa-tachometer"></i><span class="hide-menu">Dashboard</span></a></li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/admin/entregador'/>" aria-expanded="false"><i
							class="fa fa-user-circle-o"></i><span class="hide-menu">Entregador</span></a>
					</li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/admin/cliente'/>" aria-expanded="false"><i
							class="fa fa-user-circle-o"></i><span class="hide-menu">Cliente</span></a>
					</li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/admin/roupa'/>" aria-expanded="false"><i
							class="fa fa-universal-access"></i><span class="hide-menu">Roupa</span></a></li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/admin/bairro'/>" aria-expanded="false"><i
							class="fa fa-map-marker"></i><span class="hide-menu">Bairro</span></a></li>

					<li><a class="waves-effect waves-dark"
						href="<c:url value='/admin/gasto'/>" aria-expanded="false"><i
							class="fa fa-money"></i><span class="hide-menu">Gastos</span></a></li>

					<li><a class="has-arrow waves-effect waves-dark" href="#"
						aria-expanded="true"><i class="fa fa-cart-plus"
							aria-hidden="true"></i><span class="hide-menu">Pedidos</span></a>
						<ul aria-expanded="true" class="collapse out" style="">
							<li><a href="<c:url value='/admin/pedido'/>">Todos</a></li>
							<li><a class="has-arrow waves-effect waves-dark" href="#"
								aria-expanded="true"><i class="fa fa-ellipsis-v"
									aria-hidden="true"></i> Ações</a>
								<ul aria-expanded="true" class="collapse out" style="">

									<li><a
										href="<c:url value='/admin/pedido/enviaentregador'/>">Coletar
											na loja</a></li>
									<li><a href="<c:url value='/admin/pedido/lavar'/>">A
											lavar</a></li>
									<li><a href="<c:url value='/admin/pedido/secar'/>">A
											secar</a></li>
									<li><a href="<c:url value='/admin/pedido/entregar'/>">A
											entregar</a></li>
									<li><a href="<c:url value='/admin/pedido/entregando'/>">Retirado
											na loja</a></li>
								</ul></li>


						</ul></li>


				</security:authorize>
				<li><a class="waves-effect waves-dark"
					href="<c:url value='/logout'/>" aria-expanded="false"><i
						class="fa fa-sign-out"></i><span class="hide-menu">Logout</span></a></li>

			</ul>

		</nav>
	</div>


</aside>