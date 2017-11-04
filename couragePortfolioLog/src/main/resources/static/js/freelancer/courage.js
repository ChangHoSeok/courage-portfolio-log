/**
 * 2017. 10. 24
 * Courage 공통 Javascript
 */

/**
 * 사용자 인증
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
        		url: jsContextPath + "/member/signin",
        		type: "POST",
                data: JSON.stringify(params),
                dataType:'json',
                contentType : 'application/json; charset=UTF-8',
                cache: false,
                success: function(resultData) {
                    if (resultData.isLogin) {
                    	checkSignIn();
                    	$(moduleID).modal("toggle");
                    } else {
                    	alert("패스워드 인증 실패!!");
                    }
                },
                error	: function(x, e) {
                	// server side validation errors
                	if (x.status == 400) {
                		$.each(x.responseJSON.errors, function(idx, error) {
                			alert(error.defaultMessage);
                		});
                	} else {
                		alert("오류가 발생되었습니다.");
                	}
				}
            });
        }
	});
	
	function checkSignIn() {
		$.ajax({
    		url: jsContextPath + "/member/me",
    		type: "GET",
            dataType:'json',
            contentType : 'application/json; charset=UTF-8',
            cache: false,
            success: function(resultData) {
            	if (resultData) {
            		$(".gravatarImg").attr("src", resultData.memberInfo.gravatarUrl);
            		
            		$("#signin").attr("style", "display:none !important");
            		$(".avartar").show();
            	} else {
            		$("#signin").show();
            		$(".avartar").attr("style", "display:none !important");
            	}
            },
            error: function(x, e) {
            	$("#signin").show();
        		$(".avartar").attr("style", "display:none !important");
            }
        });
	}
	
	function init() {
		checkSignIn();
	}
	
	return {
		init : init,
		checkSignIn : checkSignIn
	}
})();

/**
 * 인증 해제
 */
var signOut = (function() {
	$(".signOut").on("click", function() {
		$.ajax({
    		url: jsContextPath + "/member/signout",
    		type: "POST",
            cache: false,
            success: function() {
                signIn.checkSignIn();
            }
        });
	})
	
	return {
		
	}
})();

signIn.init();