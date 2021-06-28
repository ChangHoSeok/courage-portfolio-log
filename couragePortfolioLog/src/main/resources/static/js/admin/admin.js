/**
 * 2021. 06. 26
 * Courage 관리자 Javascript
 */

var admin = (function() {
	var moduleID = "";
	
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
	
	function getFormToJson(form) {
		var unindexed_array = form.serializeArray();
		var indexed_array = {};
		
		$.map(unindexed_array, function(n, i) {
			indexed_array[n['name']] = n['value'];
		});
		
		return indexed_array;
	}
	
	function errorHandle(jqXHR) {
		var response = jqXHR.responseJSON;
		var isDefineErr = false;
		
		if (jqXHR.status == 400) {
			if (response.errors) {
				$('.errorSpan').remove();
				
	    		for (var i = 0; i < response.errors.length; i++) {
	    			var errorSpan = $('<span>');
	    			errorSpan.addClass('errorSpan').css('color', 'red');
	    			errorSpan.html(response.errors[i].defaultMessage);
	    			
	    			$('#' + response.errors[i].field).parent().append(errorSpan);
	    		}
	    		
	    		new PNotify({
	    			title: '안내',
	    			text: '입력값을 확인하세요.',
	    			type: 'warning',
	    			styling: 'bootstrap3',
	    			delay: 3000
	    		});
	    		isDefineErr = true;
	    	} else if (response.message) {
	    		new PNotify({
	    			title: '안내',
	    			text: response.message,
	    			type: 'warning',
	    			styling: 'bootstrap3',
	    			delay: 3000
	    		});
	    		isDefineErr = true;
	    	}
		} else if (jqXHR.status == 404) {
			new PNotify({
    			title: '안내',
    			text: response.message,
    			type: 'warning',
    			styling: 'bootstrap3',
    			delay: 3000
    		});
    		isDefineErr = true;
		}
		
		if (!isDefineErr) {
			new PNotify({
				title: '오류안내',
				text: '처리중 오류가 발생되었습니다.',
				type: 'error',
				styling: 'bootstrap3',
				delay: 3000
			});
		}
	}
	
	function isAuthentication(status) {
		if (status == 401) {
			alert('세션이 만료되었습니다.\n재로그인 후 이용하시기 바랍니다.');
			location.href = jsContextPath + '/';
		} else if (status == 403) {
			alert('접근 권한이 없습니다.');
		}
	}
	
	function alertInfo(alertMessage) {
		new PNotify({
			title: '안내',
			text: alertMessage,
			type: 'success',
			styling: 'bootstrap3',
			delay: 3000
		});
	}
	
	function isEmpty(value) {
		if (value == undefined || value == null || value == '' || value == 'undefined') {
			return true;
		} else {
			return false;
		}
	}
	
	function setFormData(formId, jsonData) {
		for (key in jsonData) {
			var obj = $('#' + formId).find('#' + key);
			var type = obj.prop('type');
			
			if (type == 'text' || type == 'hidden') {
				obj.val(jsonData[key]);
			} else if (type == 'textarea') {
				obj.html(jsonData[key]);
			}
		}
	}
	
	return {
		clearValidateForm : clearValidateForm,
		getFormToJson : getFormToJson,
		errorHandle : errorHandle,
		isAuthentication : isAuthentication,
		alertInfo : alertInfo,
		isEmpty : isEmpty,
		setFormData : setFormData
	}
})();

(function($) {
	"use strict";
	
	
})(jQuery);