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

	<script type="text/javascript">
	total = 0;

	function checar(check, qnt){
		document.getElementById(check).checked = true;
		
	}

	function apagar(inp, check){
		if(inp.value == "" || inp.value == "0"){
			document.getElementById(check).checked = false;
			total=0;
		}
	}

	function totala(){
		a = 0;
		console.log(a);
		<c:forEach var="roupa" items="${roupas}">
			b = parseFloat(document.getElementById("lbl${roupa.id}").innerHTML.split(" ")[3]);
			if(!isNaN(b)){
			a += parseFloat(document.getElementById("lbl${roupa.id}").innerHTML.split(" ")[3]);
			console.log(a);
			}
		</c:forEach>
		document.getElementById('total').innerHTML ="<h3><span style='color: #26a69a'>Total:</span> R$ " + a + "</h3>";
	}

	function totalAParte(roupaID, qnt){
		
		<c:forEach var="roupa" items="${roupas}">
		if(document.getElementById(roupaID).checked && roupaID == "check${roupa.id}"){
			total = 0;
			total = (${roupa.valorUnitario} * qnt.value);
			document.getElementById('lbl${roupa.id}').innerHTML = "Valor parcial: R$ " + total;
		}
		
		</c:forEach>

		totala();

	}

	function novoEndereco(check){
		var endereco = document.getElementById("endereco");
		if(endereco.classList.contains('displayNo')){
			endereco.classList.remove('displayNo');
		}else{
			endereco.classList.add('displayNo');
		}
	}
	


</script>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div class="page-wrapper">
		<div class="container-fluid">
			<div class="row page-titles">
				<div class="col-md-5 align-self-center">
					<h3 class="text-themecolor">Pedidos</h3>
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<c:url value='/cliente/pedido/add'/>">Pedido</a></li>
						<li class="breadcrumb-item active">Solicitação</li>
					</ol>
				</div>
			</div>
			<div class="row content">
				<div class="col-md-6 offset-md-3">
					<form:form id="formPedido" servletRelativeAction="/cliente/pedido"
						class="form-material" method="post" modelAttribute="pedidoRoupaWrapper">
						<div class="displayNo" id="erro">
							<span>Adicione ao menos uma roupa no pedido.</span>
						</div>
						<div class="row">
							<table>
								<c:forEach var="roupa" items="${roupas}" varStatus="s">
									<tr>
										<td><span class="input-group-text"> <form:input
												type="hidden" path="pedidoRoupas[${s.index}].roupa.id" id="check${roupa.id}"
												value="${roupa.id}"/> &nbsp; <label
												for="check${roupa.id}">${roupa.descricao}</label>
										</span></td>
										<td><form:input type="text" class="form-control"
											path="pedidoRoupas[${s.index}].quantidade" placeholder="Quantidade"
											onchange="totalAParte('check' + ${roupa.id}, this)"
											onblur="apagar(this, 'check' + ${roupa.id})"
											onfocus="checar('check'+ ${roupa.id})" /></td>
										<td><label id='lbl${roupa.id}'>Valor parcial: R$
												0.0</label></td>
									</tr>


								</c:forEach>

							</table>

						</div>

						<div class="row">
							<div class="col-md-12">
								<div class="text-center" style="margin-top: 1em">
									<h6>Entrega</h6>
								</div>
							</div>
						</div>



						<div class="form-group">

							<label> <span><input id="status1" name="endereco"
									type="radio" onchange="novoEndereco(this)" checked="checked"
									value="1" /> <label for="status1"> Utilizar o mesmo
										endereço do cadastro</label></span><br /> <span><input id="status2"
									name="endereco" type="radio" onchange="novoEndereco(this)"
									value="2" /> <label for="status2">Utilizar outro
										endereço</label> </span>
							</label>
						</div>





						<div class="row">
							<div class="displayNo col-md-12" id="endereco">

								<label>CEP</label> <input type='text' class='form-control'
									name='cep' id='cep' />

								<div class="form-row">
									<div class="form-group col-7">
										<label>Rua</label> <input type='text' class='form-control'
											id='rua' name='rua' />
									</div>
									<div class="form-group col-5">
										<label>Numero</label> <input type='text' class='form-control'
											name='numero' />
									</div>
								</div>


								<label>Bairro</label> <select class="form-control" name="bairro">

									<c:forEach var="bairro" items="${bairros}">
										<option value="${bairro.id}">${bairro.nome}</option>
									</c:forEach>
								</select>


							</div>


						</div>
						<div class="row pull-right" id="total" style="margin-top: 2em"></div>

						<div class="row text-center" style="margin-top: 20px;">
							<div class="col-md-12">
								<button class="btn btn-primary">Realizar pedido</button>
							</div>
						</div>


					</form:form>

				</div>
			</div>
		</div>
	</div>

</myTags:template>