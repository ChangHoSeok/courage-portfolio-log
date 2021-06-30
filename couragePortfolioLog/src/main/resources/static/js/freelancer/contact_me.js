$(function() {
	
	function getFormToJson(form) {
		var unindexed_array = form.serializeArray();
		var indexed_array = {};
		
		$.map(unindexed_array, function(n, i) {
			indexed_array[n['name']] = n['value'];
		});
		
		return indexed_array;
	}
	
    $("#formContact input,#formContact textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            $("#btnSubmit").attr("disabled", true);
            event.preventDefault();
            
            $.ajax({
                url: jsContextPath + "/contact",
                type: "POST",
                data: JSON.stringify(getFormToJson($('#formContact'))),
                dataType:'json',
                contentType : 'application/json; charset=UTF-8',
                cache: false,
                success: function(json) {
                    // Enable button & show success message
                    $("#btnSubmit").attr("disabled", false);
                    $('#success').html("<div class='alert alert-success'>");
                    $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-success')
                        .append("<strong>정상적으로 메시지를 전달했습니다. 빠른 시일 내에 회신 드리도록 하겠습니다. </strong>");
                    $('#success > .alert-success')
                        .append('</div>');

                    //clear all fields
                    $('#formContact').trigger("reset");
                },
                error: function() {
                    // Fail message
                    $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;").append("</button>");
                    $('#success > .alert-danger').append("<strong>죄송합니다, 메시지를 전송하는 데 실패했습니다. 다시 시도해주세요.");
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#formContact').trigger("reset");
                },
            });
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});

// When clicking on Full hide fail/success boxes
$('#name').focus(function() {
    $('#success').html('');
});
