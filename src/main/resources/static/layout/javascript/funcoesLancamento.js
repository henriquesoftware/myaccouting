/* DECIDE SE NECESSÁRIO EXIBIR IS_PAGO */
$(document).ready(function() {
	
	var idCategoria = $("#cat").val();
	
	if (idCategoria != null && idCategoria != ''){
		desabilitaChecked(idCategoria);
	}
	
});

$("#cat").on("change",function(){
	
	var idCategoria = $(this).val();
	
	if (idCategoria != null && idCategoria != ''){
		desabilitaChecked(idCategoria);
	}

});

function desabilitaChecked(idCategoria) {
	
	var endereco = "/categoria/json/";
	
	$.ajax({
		url : endereco + idCategoria,
		type : "GET",

		beforeSend : function(xhr) {
			/*xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json"); TIREI DEVIDO ESTAR RETORNANDO APENAS UMA STRING*/
		},

		success : function(data) {

			checked(data);
			
		},

		error : function(e) {

			$('#bodyteste').append(erroDiv); // VARIÁVEL LOCALIZADA EM OUTRO ARQUIVO -> erroDivMessage.js

		}
		
	});
}

function checked(resposta){
	
	if (resposta == 'DESPESA') {
		$('#valorPago').show('slow');
	} else {
		$('#valorPago').hide('slow');
	}
}

