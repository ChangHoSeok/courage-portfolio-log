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
		utils.clearValidateForm($(this).find('#password'));
	});
	
	$(moduleID + " input").jqBootstrapValidation({
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
		var memberInfo = member.getMember();
		
		if(memberInfo === undefined) {
			$("#signin").show();
    		$(".avartar").attr("style", "display:none !important");
		} else {
			$(".gravatarImg").attr("src", memberInfo.gravatarUrl);
    		
    		$("#signin").attr("style", "display:none !important");
    		$(".avartar").show();
		}
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
 * 회원정보
 */
var member = (function() {
	function getMember() {
		var memberInfo = undefined;
		
		$.ajax({
    		url: jsContextPath + "/member",
    		type: "GET",
            dataType:'json',
            contentType : 'application/json; charset=UTF-8',
            cache: false,
            success: function(resultData) {
            	if (resultData) {
            		memberInfo = resultData.memberInfo;
            	}
            },
            error: function(x, e) {
            	alert("오류가 발생되었습니다.");
            },
            async: false
        });
		
		return memberInfo;
	}
	
	return {
		getMember : getMember
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
	
	/**
	 * bootstrap multiple modal 사용시 scroll bug fix
	 */
	$(document).on('hidden.bs.modal', '.modal', function () {
	    $('.modal:visible').length && $(document.body).addClass('modal-open');
	});
	
	signIn.init();
})(jQuery);