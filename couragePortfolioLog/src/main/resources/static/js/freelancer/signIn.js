/**
 * 2017. 10. 24
 * SignIn 관련 Javascript
 */

var signIn = (function() {
	var moduleID = "#signInModal";
	
	$(moduleID).on('hidden.bs.modal', function () {
    	var passwordInput = $(this).find('#password');
    	
    	passwordInput.val('');
    	passwordInput.parent('div.form-group').removeClass("floating-label-form-group-with-value");
    	passwordInput.parent().find('.help-block').html('');
	});
	
	$(moduleID + " input, textarea").jqBootstrapValidation({
		preventSubmit: true,
		submitError: function($form, event, errors) {
            console.info('validation error');
        },
        submitSuccess: function($form, event) {
        	console.info('validation success');
        	event.preventDefault();
        }
	});
	
	return {
		init : function() {
			
		},
	}
})();