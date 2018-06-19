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
					<h3 class="text-themecolor">Pedido #${pedido.id}</h3>
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="">Pedido</a></li>
						<li class="breadcrumb-item active">Detalhe</li>
					</ol>
				</div>
			</div>
			<div class="container content">
				<div class="row">

					
						<div class="col-md-12">
						<div class="card card-body printableArea">
							<h3>
								<b>Pedido </b> <span class="pull-right">#${pedido.id}</span>
							</h3>
							<hr>
							<div class="row">
								<div class="col-md-12">
									<div class="pull-left">
										<address>
											<h3>
												&nbsp;<b class="text-danger">${pedido.cliente.usuario.nome}</b>
											</h3>
											<p class=" m-l-5">
												${pedido.cliente.endereco.rua},
												${pedido.cliente.endereco.numero}<br />
												${pedido.cliente.endereco.bairroEntity.nome}, <br />
												${pedido.cliente.endereco.cep}
											</p>
										</address>
									</div>
									<div class="pull-right text-right">

										<h3>Endereço de entrega</h3>
										<p class="m-l-30">
											${pedido.endereco.rua},
											${pedido.endereco.numero}<br />
											${pedido.endereco.bairroEntity.nome}, <br />
											${pedido.endereco.cep}
										</p>
										<p class="m-t-30">
											<b>Data de emissão:</b> <i class="fa fa-calendar"></i>
											<fmt:formatDate
												value="${pedido.dataEmissao.time}"
												pattern="HH:mm dd/MM/yyyy" />
										</p>
										<p>
											<b>Data de entrega :</b> <i class="fa fa-calendar"></i>
											<fmt:formatDate value="${pedido.dataEntrega.time}"
											pattern="HH:mm dd/MM/yyyy" />
										</p>

									</div>
								</div>
								
								<div class="col-md-12">
									<span class="text-danger">Status:</span> <b>${pedido.statusPedido.descricao}</b><br/>
									<span class="text-danger">Pagamento:</span> <b>${pedido.pagamento.descricao}</b>
								</div>
								<div class="col-md-12">
									<div class="table-responsive m-t-40" style="clear: both;">
										<table class="table table-hover">
											<thead>
												<tr>
													<th>#</th>
													<th>Produto</th>
													<th>Quantidade</th>
													<th>Subtotal</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${roupas}" var="pedidoRoupa" varStatus="s">
													<tr>
														<td>${s.index + 1}</td>
														<td>${pedidoRoupa.roupa.descricao}</td>
														<td>${pedidoRoupa.quantidade}</td>
														<td>R$ <fmt:formatNumber value ="${pedidoRoupa.quantidade * pedidoRoupa.roupa.valorUnitario}" maxFractionDigits="2" minFractionDigits="2" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-12">
									<div class="pull-right m-t-30 text-right">
										<h3>
											<b>Total :</b> R$${pedido.total}
										</h3>
									</div>
									<div class="clearfix"></div>
									<hr>
									<div class="text-right">
										<button id="print" class="btn btn-danger" type="submit">
											<i class="fa fa-print"></i> Baixar como comprovante
										</button>
									</div>
								</div>
								<div class="row">
									<!-- accepted payments column -->
									<div class="col-md-10 offset-md-1">
										<p class="lead">Metodos de pagamento:</p>
										<img
											src="https://seuintermediario.files.wordpress.com/2015/04/interactive-01.png"
											width="30" alt="Moderninha pagseguro"> Moderninha
										(PagSeguro)<br /> <img
											src="https://icon-icons.com/icons2/474/PNG/32/cash_46875.png"
											width="30" alt="A Vista"> A vista
										<p class="text-muted well well-md no-shadow"
											style="color: #000 !important" style="margin-top: 10px;">
											Vale ressaltar que os preços que contém na tabela são dados <b>ATUAIS</b>,
											pode ser que os preços sejam diferentes se comparados ao
											momento da compra mas nada vai alterar o preço total.
										</p>
									</div>
									<!-- /.col -->

								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
</myTags:template>
