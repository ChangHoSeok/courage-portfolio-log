/**
 * 2021. 06. 26
 * Courage 관리자 Javascript
 */

var admin = (function() {
	var moduleID = "";
	
	$('#sidebarToggle').on('click', function() {
		$('body').toggleClass('sb-sidenav-toggled');
	});
	
	return {
		
	}
})();

/**
 * 공통 유틸리티
 */
var utils = (function() {
	function clearValidateForm(input) {
		input.val("");
		input.parent('div.form-group').removeClass("floating-label-form-group-with-value");
		input.parent().find('.help-block').html('');
	}
	
	return {
		clearValidateForm : clearValidateForm
	}
})();

(function($) {
	"use strict";
	
	
})(jQuery);