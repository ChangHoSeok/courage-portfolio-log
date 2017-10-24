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
            
        },
        submitSuccess: function($form, event) {
        	event.preventDefault();
        	
        	var params = {
        		password : $form.find('#password').val()
        	};
        	
        	$.ajax({
        		url: jsContextPath + "/member/auth",
        		type: "POST",
                data: JSON.stringify(params),
                dataType:'json',
                contentType : 'application/json; charset=UTF-8',
                cache: false,
                success: function(resultData) {
                    console.info(resultData);
                },
                error	: function(x, e) {
                	// server side validation errors
                	if (x.status == 400) {
                		$.each(x.responseJSON.errors, function(idx, error) {
                			alert(error.defaultMessage);
                		});
                	}
				}
            });
        }
	});
	
	return {
		init : function() {
			
		},
	}
})();