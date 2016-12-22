$(function() {
	setTimeout(function() {
		$('#hideComponent').fadeOut('slow');
	}, 7000);
});

$(function() {
	$('#tabela1').DataTable();
	$('#example2').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"searching" : false,
		"ordering" : true,
		"info" : true,
		"autoWidth" : false
	});
});

// EXCLUIR WITHOUT RELOAD PAGE
$(document).ready(function() {
	$(".btn-danger").click(function(e) {
		var id = $(this).attr("id");

		var endereco = "remover/";
		
		// teste
		$.ajax({
			url : endereco + id,
			type : "DELETE",

			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},

			success : function() {

				var tr = $(e.target).closest("tr");
				tr.css("background-color", "#000000");
				tr.fadeIn(1000).fadeOut(200, function() {
					tr.remove();
				})
				
				var li = $(e.target).closest("li");;
				li.css("backgroud-color","#000000");
				li.fadeIn(1000).fadeOut(200,function(){
					li.remove();
				})
			},

			error : function(e) {

				$('#bodyteste').append(erroDiv); // VARIÁVEL LOCALIZADA EM OUTRO ARQUIVO -> erroDivMessage.js

			}
		});
	});
});

$('#inputMonthYear').on("change", function(){
	
	var teste = $(this).val();
	
	enviaDataPost();
	
	
});

function enviaDataPost(){
	
	var urlTeste = '/lancamento/tabela';
	
	if($('#inputMonthYear').val() != ''){
		urlTeste = urlTeste + '/' + encodeURIComponent($('#inputMonthYear').val());
	}
	
	$("#tabelaBlock").load(urlTeste);

	// TREINAMENTO.. RSRS
	/*$jq.when(
			$jq.getScript('/layout/javascript/jquery-2.2.3.min.js'),
			$jq.getScript('https://code.jquery.com/ui/1.11.4/jquery-ui.min.js'),
			$jq.getScript('/layout/bootstrap/js/bootstrap.min.js'),
			$jq.getScript('/layout/plugins/datatables/jquery.dataTables.min.js'),
			$jq.getScript('/layout/plugins/datatables/dataTables.bootstrap.min.js'),
		    $jq.Deferred(function( deferred ){
		    	$jq( deferred.resolve );
		    })
		).done(function(){

			$jq("#tabelaBlock").load(urlTeste);

		});
	
	
	$.ajax({
        
		type: "GET",
        url: urlTeste,
        datatype : "html",
        success: function(html) {
            console.log(html);
            alert('ddd');
             $("#tabelaBlock").load(html);
        },
		error : function(e) {
	
			alert('sdf');
	
		}
    });
	
	var urlSecond = '/lancamento/tabela';
	
	if($('#inputMonthYear').val() != ''){
		urlSecond = urlSecond + '/' + encodeURIComponent($('#inputMonthYear').val());
	}
	
	$.ajax({
	    url: '/lancamento/Tabela.html',
	    dataType: 'html',
	    success: function(html) {
	        var div = $('#tabelaBlock', $(html)).addClass('done');
	        $('#targetDiv').html(div);
	    }
	});*/
}

/*$(function(){
	var teste = $('#inputMonthYear').val();
	
		
		alert(teste);
		
});


function sendData() {

	alert('teste');
	
	$('#tabela1 .btn').click(function(){ // var id = $(this).attr("id");
	
	alert('haha');

});

var tr = $('#testee').closest("tr"); tr.css("background-color", "#000000");
tr.fadeIn(1000).fadeOut(200, function() { tr.remove(); }); };*/


