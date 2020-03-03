$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({thousands:'', decimal:',', allowZero:true});
});
