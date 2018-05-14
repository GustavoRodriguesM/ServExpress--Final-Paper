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
	<link rel="stylesheet"
		href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
	<link rel="stylesheet" href="/assets/css/fontawesome-stars.css">
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-bar-rating/1.2.2/jquery.barrating.min.js"></script>

	<script>
		$(document).ready(function() {
			${sweetAlert}
		})

		$(function() {
			$('#example').barrating({
				theme : 'fontawesome-stars'
			});
		});
	</script>
	<style>
.ultimopedido {
	padding: 5px;
	background-color: #489af2;
	margin-top: 0.1em;
	color: #fbfbfb;
}

.titulo {
	border-radius: 10px;
}
</style>

	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div class="page-wrapper">
		<div class="container-fluid">

			<c:choose>


				<c:when test="${pedidoRoupa == null}">
					<div class="col-md-12">${message}</div>
					<div class="col-md-12">
						<div class="content text-center col-md-4 offset-md-4">
							<h3>Você ainda não fez nenhum pedido :(</h3>
						</div>
					</div>
				</c:when>
				<c:otherwise>


					<div class="col-md-12">
						${message}
						<div class="card card-body printableArea">
							<h3>
								<b>Pedido mais recente</b> <span class="pull-right">#${pedidoRoupa.pedido.id}</span>
							</h3>
							<hr>
							<div class="row">
								<div class="col-md-12">
									<div class="pull-left">
										<address>
											<h3>
												&nbsp;<b class="text-danger">${pedidoRoupa.pedido.cliente.usuario.nome}</b>
											</h3>
											<p class="m-l-5">
												${pedidoRoupa.pedido.cliente.endereco.rua},
												${pedidoRoupa.pedido.cliente.endereco.numero}<br />
												${pedidoRoupa.pedido.cliente.endereco.bairroEntity.nome}, <br />
												${pedidoRoupa.pedido.cliente.endereco.cep}
											</p>
										</address>
									</div>
									<div class="pull-right text-right">

										<h3>Endereço de entrega</h3>
										<p class="m-l-30">
											${pedidoRoupa.pedido.endereco.rua},
											${pedidoRoupa.pedido.endereco.numero}<br />
											${pedidoRoupa.pedido.endereco.bairroEntity.nome}, <br />
											${pedidoRoupa.pedido.endereco.cep}
										</p>
										<p class="m-t-30">
											<b>Data de emissão:</b> <i class="fa fa-calendar"></i>
											<fmt:formatDate
												value="${pedidoRoupa.pedido.dataEmissao.time}"
												pattern="HH:mm dd/MM/yyyy" />
										</p>
										<p>
											<b>Data de entrega :</b> <i class="fa fa-calendar"></i>
											<fmt:formatDate
												value="${pedidoRoupa.pedido.dataEntrega.time}"
												pattern="HH:mm dd/MM/yyyy" />
										</p>

									</div>
								</div>

								<div class="col-md-12">
									<span class="text-danger">Status:</span> <b>${pedidoRoupa.pedido.statusPedido.descricao}</b><br />
									<span class="text-danger">Pagamento:</span> <b>${pedidoRoupa.pedido.pagamento.descricao}</b>
								</div>
								<div class="col-md-6 offset-md-3">
									<div class="table-responsive m-t-40" style="clear: both;">
										<table class="table table-hover color-table danger-table">
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
														<td>R$${pedidoRoupa.quantidade *
															pedidoRoupa.roupa.valorUnitario}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-12">
									<div class="pull-right m-t-30 text-right">
										<h3>
											<b>Total :</b> R$${pedidoRoupa.pedido.total}
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


				</c:otherwise>
			</c:choose>

			<c:if test="${!pedidoRoupa.pedido.cliente.feedback}">

				<c:if test="${pedidoRoupa.pedido.statusPedido == 'ENTREGUE'}">
					<script>
						$(document).ready(function() {
							$('#exampleModalCenter').modal('show');

						});

						function enviaFeedback() {
							form = document.getElementById("feedback");
							form.submit();
						}
					</script>
					<div class="modal fade" id="exampleModalCenter" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalCenterTitle"
						aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">Avaliação
										de serviço ServExpress</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">

									<form:form action="/cliente/feedback" modelAttribute="feedback"
										method="post">
										<div class="form-group text-center">
											<label><select name="avaliacao" id="example">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
											</select> </label>
										</div>

										<div class="form-group">
											<label>Descrição</label>
											<textarea name="descricao" class="form-control"></textarea>
										</div>
										<input type="hidden" name="cliente"
											value="${pedidoRoupa.pedido.cliente.id}" />
									</form:form>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Sair</button>
									<button type="button" class="btn btn-primary"
										onclick="enviaFeedback()">Enviar feedback</button>
								</div>
							</div>
						</div>
					</div>

				</c:if>

			</c:if>
		</div>
	</div>

</myTags:template>