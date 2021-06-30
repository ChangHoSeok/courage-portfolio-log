/**
 * Resize function without multiple trigger
 * 
 * Usage: $(window).smartresize(function(){ // code here });
 */
(function($, sr) {
	// debouncing function from John Hann
	// http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
	var debounce = function(func, threshold, execAsap) {
		var timeout;

		return function debounced() {
			var obj = this, args = arguments;
			function delayed() {
				if (!execAsap)
					func.apply(obj, args);
				timeout = null;
			}

			if (timeout)
				clearTimeout(timeout);
			else if (execAsap)
				func.apply(obj, args);

			timeout = setTimeout(delayed, threshold || 100);
		};
	};

	// smartresize
	jQuery.fn[sr] = function(fn) {
		return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr);
	};

})(jQuery, 'smartresize');
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */

var CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
	$BODY = $('body'),
	$MENU_TOGGLE = $('#menu_toggle'),
	$SIDEBAR_MENU = $('#sidebar-menu'),
	$SIDEBAR_FOOTER = $('.sidebar-footer'),
	$LEFT_COL = $('.left_col'),
	$RIGHT_COL = $('.right_col'),
	$NAV_MENU = $('.nav_menu'),
	$FOOTER = $('footer');

// Sidebar
function init_sidebar() {
	// TODO: This is some kind of easy fix, maybe we can improve this
	var setContentHeight = function() {
		// reset height
		$RIGHT_COL.css('min-height', $(window).height());

		var bodyHeight = $BODY.outerHeight(), footerHeight = $BODY
				.hasClass('footer_fixed') ? -10 : $FOOTER.height(), leftColHeight = $LEFT_COL
				.eq(1).height()
				+ $SIDEBAR_FOOTER.height(), contentHeight = bodyHeight < leftColHeight ? leftColHeight
				: bodyHeight;

		// normalize content
		contentHeight -= $NAV_MENU.height() + footerHeight;

		$RIGHT_COL.css('min-height', contentHeight);
	};

	$SIDEBAR_MENU.find('a').on('click', function(ev) {
		console.log('clicked - sidebar_menu');
		var $li = $(this).parent();

		if ($li.is('.active')) {
			$li.removeClass('active active-sm');
			$('ul:first', $li).slideUp(function() {
				setContentHeight();
			});
		} else {
			// prevent closing menu if we are on child menu
			if (!$li.parent().is('.child_menu')) {
				$SIDEBAR_MENU.find('li').removeClass('active active-sm');
				$SIDEBAR_MENU.find('li ul').slideUp();
			} else {
				if ($BODY.is(".nav-sm")) {
					$SIDEBAR_MENU.find("li").removeClass("active active-sm");
					$SIDEBAR_MENU.find("li ul").slideUp();
				}
			}
			$li.addClass('active');

			$('ul:first', $li).slideDown(function() {
				setContentHeight();
			});
		}
	});

	// toggle small or large menu
	$MENU_TOGGLE.on('click', function() {
		console.log('clicked - menu toggle');

		if ($BODY.hasClass('nav-md')) {
			$SIDEBAR_MENU.find('li.active ul').hide();
			$SIDEBAR_MENU.find('li.active').addClass('active-sm').removeClass(
					'active');
		} else {
			$SIDEBAR_MENU.find('li.active-sm ul').show();
			$SIDEBAR_MENU.find('li.active-sm').addClass('active').removeClass(
					'active-sm');
		}

		$BODY.toggleClass('nav-md nav-sm');

		setContentHeight();
	});

	// check active menu
	$SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass(
			'current-page');

	$SIDEBAR_MENU.find('a').filter(function() {
		return this.href == CURRENT_URL;
	}).parent('li').addClass('current-page').parents('ul').slideDown(
			function() {
				setContentHeight();
			}).parent().addClass('active');

	// recompute content when resizing
	$(window).smartresize(function() {
		setContentHeight();
	});

	setContentHeight();

	// fixed sidebar
	if ($.fn.mCustomScrollbar) {
		$('.menu_fixed').mCustomScrollbar({
			autoHideScrollbar : true,
			theme : 'minimal',
			mouseWheel : {
				preventDefault : true
			}
		});
	}
};
// /Sidebar

var randNum = function() {
	return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
};

// Panel toolbox
$(document)
		.ready(
				function() {
					$('.collapse-link')
							.on(
									'click',
									function() {
										var $BOX_PANEL = $(this).closest(
												'.x_panel'), $ICON = $(this)
												.find('i'), $BOX_CONTENT = $BOX_PANEL
												.find('.x_content');

										// fix for some div with hardcoded fix
										// class
										if ($BOX_PANEL.attr('style')) {
											$BOX_CONTENT
													.slideToggle(
															200,
															function() {
																$BOX_PANEL
																		.removeAttr('style');
															});
										} else {
											$BOX_CONTENT.slideToggle(200);
											$BOX_PANEL.css('height', 'auto');
										}

										$ICON
												.toggleClass('fa-chevron-up fa-chevron-down');
									});

					$('.close-link').click(function() {
						var $BOX_PANEL = $(this).closest('.x_panel');

						$BOX_PANEL.remove();
					});
				});
// /Panel toolbox

// Tooltip
$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip({
		container : 'body'
	});
});
// /Tooltip

// Progressbar
if ($(".progress .progress-bar")[0]) {
	$('.progress .progress-bar').progressbar();
}
// /Progressbar

// Switchery
$(document).ready(
		function() {
			if ($(".js-switch")[0]) {
				var elems = Array.prototype.slice.call(document
						.querySelectorAll('.js-switch'));
				elems.forEach(function(html) {
					var switchery = new Switchery(html, {
						color : '#26B99A'
					});
				});
			}
		});
// /Switchery

// iCheck
$(document).ready(function() {
	if ($("input.flat")[0]) {
		$(document).ready(function() {
			$('input.flat').iCheck({
				checkboxClass : 'icheckbox_flat-green',
				radioClass : 'iradio_flat-green'
			});
		});
	}
});
// /iCheck

// Table
$('table input').on('ifChecked', function() {
	checkState = '';
	$(this).parent().parent().parent().addClass('selected');
	countChecked();
});
$('table input').on('ifUnchecked', function() {
	checkState = '';
	$(this).parent().parent().parent().removeClass('selected');
	countChecked();
});

var checkState = '';

$('.bulk_action input').on('ifChecked', function() {
	checkState = '';
	$(this).parent().parent().parent().addClass('selected');
	countChecked();
});
$('.bulk_action input').on('ifUnchecked', function() {
	checkState = '';
	$(this).parent().parent().parent().removeClass('selected');
	countChecked();
});
$('.bulk_action input#check-all').on('ifChecked', function() {
	checkState = 'all';
	countChecked();
});
$('.bulk_action input#check-all').on('ifUnchecked', function() {
	checkState = 'none';
	countChecked();
});

function countChecked() {
	if (checkState === 'all') {
		$(".bulk_action input[name='table_records']").iCheck('check');
	}
	if (checkState === 'none') {
		$(".bulk_action input[name='table_records']").iCheck('uncheck');
	}

	var checkCount = $(".bulk_action input[name='table_records']:checked").length;

	if (checkCount) {
		$('.column-title').hide();
		$('.bulk-actions').show();
		$('.action-cnt').html(checkCount + ' Records Selected');
	} else {
		$('.column-title').show();
		$('.bulk-actions').hide();
	}
}

// Accordion
$(document).ready(function() {
	$(".expand").on("click", function() {
		$(this).next().slideToggle(200);
		$expand = $(this).find(">:first-child");

		if ($expand.text() == "+") {
			$expand.text("-");
		} else {
			$expand.text("+");
		}
	});
});

// NProgress
if (typeof NProgress != 'undefined') {
	$(document).ready(function() {
		NProgress.start();
	});

	$(window).load(function() {
		NProgress.done();
	});
}

// hover and retain popover when on popover content
var originalLeave = $.fn.popover.Constructor.prototype.leave;
$.fn.popover.Constructor.prototype.leave = function(obj) {
	var self = obj instanceof this.constructor ? obj
			: $(obj.currentTarget)[this.type](this.getDelegateOptions()).data(
					'bs.' + this.type);
	var container, timeout;

	originalLeave.call(this, obj);

	if (obj.currentTarget) {
		container = $(obj.currentTarget).siblings('.popover');
		timeout = self.timeout;
		container.one('mouseenter', function() {
			// We entered the actual popover – call off the dogs
			clearTimeout(timeout);
			// Let's monitor popover content instead
			container.one('mouseleave', function() {
				$.fn.popover.Constructor.prototype.leave.call(self, self);
			});
		});
	}
};

$('body').popover({
	selector : '[data-popover]',
	trigger : 'click hover',
	delay : {
		show : 50,
		hide : 400
	}
});

function gd(year, month, day) {
	return new Date(year, month - 1, day).getTime();
}

/* STARRR */

function init_starrr() {

	if (typeof (starrr) === 'undefined') {
		return;
	}
	console.log('init_starrr');

	$(".stars").starrr();

	$('.stars-existing').starrr({
		rating : 4
	});

	$('.stars').on('starrr:change', function(e, value) {
		$('.stars-count').html(value);
	});

	$('.stars-existing').on('starrr:change', function(e, value) {
		$('.stars-count-existing').html(value);
	});

};

function init_skycons() {

	if (typeof (Skycons) === 'undefined') {
		return;
	}
	console.log('init_skycons');

	var icons = new Skycons({
		"color" : "#73879C"
	}), list = [ "clear-day", "clear-night", "partly-cloudy-day",
			"partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
			"fog" ], i;

	for (i = list.length; i--;)
		icons.set(list[i], list[i]);

	icons.play();

}

/* AUTOSIZE */

function init_autosize() {

	if (typeof $.fn.autosize !== 'undefined') {

		autosize($('.resizable_textarea'));

	}

};

/* INPUTS */

function onAddTag(tag) {
	alert("Added a tag: " + tag);
}

function onRemoveTag(tag) {
	alert("Removed a tag: " + tag);
}

function onChangeTag(input, tag) {
	alert("Changed a tag: " + tag);
}

// tags input
function init_TagsInput() {

	if (typeof $.fn.tagsInput !== 'undefined') {

		$('#tags_1').tagsInput({
			width : 'auto'
		});

	}

};

/* SELECT2 */

function init_select2() {

	if (typeof (select2) === 'undefined') {
		return;
	}
	console.log('init_toolbox');

	$(".select2_single").select2({
		placeholder : "Select a state",
		allowClear : true
	});
	$(".select2_group").select2({});
	$(".select2_multiple").select2({
		maximumSelectionLength : 4,
		placeholder : "With Max Selection limit 4",
		allowClear : true
	});

};

/* WYSIWYG EDITOR */

function init_wysiwyg() {

	if (typeof ($.fn.wysiwyg) === 'undefined') {
		return;
	}
	console.log('init_wysiwyg');

	function init_ToolbarBootstrapBindings() {
		var fonts = [ 'Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
				'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact',
				'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
				'Times New Roman', 'Verdana' ], fontTarget = $('[title=Font]')
				.siblings('.dropdown-menu');
		$.each(fonts, function(idx, fontName) {
			fontTarget.append($('<li><a data-edit="fontName ' + fontName
					+ '" style="font-family:\'' + fontName + '\'">' + fontName
					+ '</a></li>'));
		});
		$('a[title]').tooltip({
			container : 'body'
		});
		$('.dropdown-menu input').click(function() {
			return false;
		}).change(
				function() {
					$(this).parent('.dropdown-menu').siblings(
							'.dropdown-toggle').dropdown('toggle');
				}).keydown('esc', function() {
			this.value = '';
			$(this).change();
		});

		$('[data-role=magic-overlay]').each(
				function() {
					var overlay = $(this), target = $(overlay.data('target'));
					overlay.css('opacity', 0).css('position', 'absolute')
							.offset(target.offset()).width(target.outerWidth())
							.height(target.outerHeight());
				});

		if ("onwebkitspeechchange" in document.createElement("input")) {
			var editorOffset = $('#editor').offset();

			$('.voiceBtn').css('position', 'absolute').offset({
				top : editorOffset.top,
				left : editorOffset.left + $('#editor').innerWidth() - 35
			});
		} else {
			$('.voiceBtn').hide();
		}
	}

	function showErrorAlert(reason, detail) {
		var msg = '';
		if (reason === 'unsupported-file-type') {
			msg = "Unsupported format " + detail;
		} else {
			console.log("error uploading file", reason, detail);
		}
		$(
				'<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
						+ '<strong>File upload error</strong> '
						+ msg
						+ ' </div>').prependTo('#alerts');
	}

	$('.editor-wrapper').each(function() {
		var id = $(this).attr('id'); // editor-one

		$(this).wysiwyg({
			toolbarSelector : '[data-target="#' + id + '"]',
			fileUploadError : showErrorAlert
		});
	});

	window.prettyPrint;
	prettyPrint();

};

/* KNOB */

function init_knob() {

	if (typeof ($.fn.knob) === 'undefined') {
		return;
	}
	console.log('init_knob');

	$(".knob").knob(
			{
				change : function(value) {
					// console.log("change : " + value);
				},
				release : function(value) {
					// console.log(this.$.attr('value'));
					console.log("release : " + value);
				},
				cancel : function() {
					console.log("cancel : ", this);
				},
				/*
				 * format : function (value) { return value + '%'; },
				 */
				draw : function() {

					// "tron" case
					if (this.$.data('skin') == 'tron') {

						this.cursorExt = 0.3;

						var a = this.arc(this.cv) // Arc
						, pa // Previous arc
						, r = 1;

						this.g.lineWidth = this.lineWidth;

						if (this.o.displayPrevious) {
							pa = this.arc(this.v);
							this.g.beginPath();
							this.g.strokeStyle = this.pColor;
							this.g.arc(this.xy, this.xy, this.radius
									- this.lineWidth, pa.s, pa.e, pa.d);
							this.g.stroke();
						}

						this.g.beginPath();
						this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
						this.g.arc(this.xy, this.xy, this.radius
								- this.lineWidth, a.s, a.e, a.d);
						this.g.stroke();

						this.g.lineWidth = 2;
						this.g.beginPath();
						this.g.strokeStyle = this.o.fgColor;
						this.g.arc(this.xy, this.xy, this.radius
								- this.lineWidth + 1 + this.lineWidth * 2 / 3,
								0, 2 * Math.PI, false);
						this.g.stroke();

						return false;
					}
				}

			});

	// Example of infinite knob, iPod click wheel
	var v, up = 0, down = 0, i = 0, $idir = $("div.idir"), $ival = $("div.ival"), incr = function() {
		i++;
		$idir.show().html("+").fadeOut();
		$ival.html(i);
	}, decr = function() {
		i--;
		$idir.show().html("-").fadeOut();
		$ival.html(i);
	};
	$("input.infinite").knob({
		min : 0,
		max : 20,
		stopper : false,
		change : function() {
			if (v > this.cv) {
				if (up) {
					decr();
					up = 0;
				} else {
					up = 1;
					down = 0;
				}
			} else {
				if (v < this.cv) {
					if (down) {
						incr();
						down = 0;
					} else {
						down = 1;
						up = 0;
					}
				}
			}
			v = this.cv;
		}
	});

};

/* INPUT MASK */

function init_InputMask() {

	if (typeof ($.fn.inputmask) === 'undefined') {
		return;
	}
	console.log('init_InputMask');

	$(":input").inputmask();

};

/* COLOR PICKER */

function init_ColorPicker() {

	if (typeof ($.fn.colorpicker) === 'undefined') {
		return;
	}
	console.log('init_ColorPicker');

	$('.demo1').colorpicker();
	$('.demo2').colorpicker();

	$('#demo_forceformat').colorpicker({
		format : 'rgba',
		horizontal : true
	});

	$('#demo_forceformat3').colorpicker({
		format : 'rgba',
	});

	$('.demo-auto').colorpicker();

};

/* ION RANGE SLIDER */

function init_IonRangeSlider() {

	if (typeof ($.fn.ionRangeSlider) === 'undefined') {
		return;
	}
	console.log('init_IonRangeSlider');

	$("#range_27").ionRangeSlider({
		type : "double",
		min : 1000000,
		max : 2000000,
		grid : true,
		force_edges : true
	});
	$("#range").ionRangeSlider({
		hide_min_max : true,
		keyboard : true,
		min : 0,
		max : 5000,
		from : 1000,
		to : 4000,
		type : 'double',
		step : 1,
		prefix : "$",
		grid : true
	});
	$("#range_25").ionRangeSlider({
		type : "double",
		min : 1000000,
		max : 2000000,
		grid : true
	});
	$("#range_26").ionRangeSlider({
		type : "double",
		min : 0,
		max : 10000,
		step : 500,
		grid : true,
		grid_snap : true
	});
	$("#range_31").ionRangeSlider({
		type : "double",
		min : 0,
		max : 100,
		from : 30,
		to : 70,
		from_fixed : true
	});
	$(".range_min_max").ionRangeSlider({
		type : "double",
		min : 0,
		max : 100,
		from : 30,
		to : 70,
		max_interval : 50
	});
	$(".range_time24").ionRangeSlider({
		min : +moment().subtract(12, "hours").format("X"),
		max : +moment().format("X"),
		from : +moment().subtract(6, "hours").format("X"),
		grid : true,
		force_edges : true,
		prettify : function(num) {
			var m = moment(num, "X");
			return m.format("Do MMMM, HH:mm");
		}
	});

};

/* DATERANGEPICKER */

function init_daterangepicker() {

	if (typeof ($.fn.daterangepicker) === 'undefined') {
		return;
	}
	console.log('init_daterangepicker');

	var cb = function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
		$('#reportrange span').html(
				start.format('MMMM D, YYYY') + ' - '
						+ end.format('MMMM D, YYYY'));
	};

	var optionSet1 = {
		startDate : moment().subtract(29, 'days'),
		endDate : moment(),
		minDate : '01/01/2012',
		maxDate : '12/31/2015',
		dateLimit : {
			days : 60
		},
		showDropdowns : true,
		showWeekNumbers : true,
		timePicker : false,
		timePickerIncrement : 1,
		timePicker12Hour : true,
		ranges : {
			'Today' : [ moment(), moment() ],
			'Yesterday' : [ moment().subtract(1, 'days'),
					moment().subtract(1, 'days') ],
			'Last 7 Days' : [ moment().subtract(6, 'days'), moment() ],
			'Last 30 Days' : [ moment().subtract(29, 'days'), moment() ],
			'This Month' : [ moment().startOf('month'), moment().endOf('month') ],
			'Last Month' : [ moment().subtract(1, 'month').startOf('month'),
					moment().subtract(1, 'month').endOf('month') ]
		},
		opens : 'left',
		buttonClasses : [ 'btn btn-default' ],
		applyClass : 'btn-small btn-primary',
		cancelClass : 'btn-small',
		format : 'MM/DD/YYYY',
		separator : ' to ',
		locale : {
			applyLabel : 'Submit',
			cancelLabel : 'Clear',
			fromLabel : 'From',
			toLabel : 'To',
			customRangeLabel : 'Custom',
			daysOfWeek : [ 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa' ],
			monthNames : [ 'January', 'February', 'March', 'April', 'May',
					'June', 'July', 'August', 'September', 'October',
					'November', 'December' ],
			firstDay : 1
		}
	};

	$('#reportrange span').html(
			moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - '
					+ moment().format('MMMM D, YYYY'));
	$('#reportrange').daterangepicker(optionSet1, cb);
	$('#reportrange').on('show.daterangepicker', function() {
		console.log("show event fired");
	});
	$('#reportrange').on('hide.daterangepicker', function() {
		console.log("hide event fired");
	});
	$('#reportrange').on(
			'apply.daterangepicker',
			function(ev, picker) {
				console.log("apply event fired, start/end dates are "
						+ picker.startDate.format('MMMM D, YYYY') + " to "
						+ picker.endDate.format('MMMM D, YYYY'));
			});
	$('#reportrange').on('cancel.daterangepicker', function(ev, picker) {
		console.log("cancel event fired");
	});
	$('#options1').click(function() {
		$('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
	});
	$('#options2').click(function() {
		$('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
	});
	$('#destroy').click(function() {
		$('#reportrange').data('daterangepicker').remove();
	});

}

function init_daterangepicker_right() {

	if (typeof ($.fn.daterangepicker) === 'undefined') {
		return;
	}
	console.log('init_daterangepicker_right');

	var cb = function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
		$('#reportrange_right span').html(
				start.format('MMMM D, YYYY') + ' - '
						+ end.format('MMMM D, YYYY'));
	};

	var optionSet1 = {
		startDate : moment().subtract(29, 'days'),
		endDate : moment(),
		minDate : '01/01/2012',
		maxDate : '12/31/2020',
		dateLimit : {
			days : 60
		},
		showDropdowns : true,
		showWeekNumbers : true,
		timePicker : false,
		timePickerIncrement : 1,
		timePicker12Hour : true,
		ranges : {
			'Today' : [ moment(), moment() ],
			'Yesterday' : [ moment().subtract(1, 'days'),
					moment().subtract(1, 'days') ],
			'Last 7 Days' : [ moment().subtract(6, 'days'), moment() ],
			'Last 30 Days' : [ moment().subtract(29, 'days'), moment() ],
			'This Month' : [ moment().startOf('month'), moment().endOf('month') ],
			'Last Month' : [ moment().subtract(1, 'month').startOf('month'),
					moment().subtract(1, 'month').endOf('month') ]
		},
		opens : 'right',
		buttonClasses : [ 'btn btn-default' ],
		applyClass : 'btn-small btn-primary',
		cancelClass : 'btn-small',
		format : 'MM/DD/YYYY',
		separator : ' to ',
		locale : {
			applyLabel : 'Submit',
			cancelLabel : 'Clear',
			fromLabel : 'From',
			toLabel : 'To',
			customRangeLabel : 'Custom',
			daysOfWeek : [ 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa' ],
			monthNames : [ 'January', 'February', 'March', 'April', 'May',
					'June', 'July', 'August', 'September', 'October',
					'November', 'December' ],
			firstDay : 1
		}
	};

	$('#reportrange_right span').html(
			moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - '
					+ moment().format('MMMM D, YYYY'));

	$('#reportrange_right').daterangepicker(optionSet1, cb);

	$('#reportrange_right').on('show.daterangepicker', function() {
		console.log("show event fired");
	});
	$('#reportrange_right').on('hide.daterangepicker', function() {
		console.log("hide event fired");
	});
	$('#reportrange_right').on(
			'apply.daterangepicker',
			function(ev, picker) {
				console.log("apply event fired, start/end dates are "
						+ picker.startDate.format('MMMM D, YYYY') + " to "
						+ picker.endDate.format('MMMM D, YYYY'));
			});
	$('#reportrange_right').on('cancel.daterangepicker', function(ev, picker) {
		console.log("cancel event fired");
	});

	$('#options1').click(
			function() {
				$('#reportrange_right').data('daterangepicker').setOptions(
						optionSet1, cb);
			});

	$('#options2').click(
			function() {
				$('#reportrange_right').data('daterangepicker').setOptions(
						optionSet2, cb);
			});

	$('#destroy').click(function() {
		$('#reportrange_right').data('daterangepicker').remove();
	});

}

function init_daterangepicker_single_call() {

	if (typeof ($.fn.daterangepicker) === 'undefined') {
		return;
	}
	console.log('init_daterangepicker_single_call');

	$('#single_cal1').daterangepicker({
		singleDatePicker : true,
		singleClasses : "picker_1"
	}, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
	});
	$('#single_cal2').daterangepicker({
		singleDatePicker : true,
		singleClasses : "picker_2"
	}, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
	});
	$('#single_cal3').daterangepicker({
		singleDatePicker : true,
		singleClasses : "picker_3"
	}, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
	});
	$('#single_cal4').daterangepicker({
		singleDatePicker : true,
		singleClasses : "picker_4"
	}, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
	});

}

function init_daterangepicker_reservation() {

	if (typeof ($.fn.daterangepicker) === 'undefined') {
		return;
	}
	console.log('init_daterangepicker_reservation');

	$('#reservation').daterangepicker(null, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
	});

	$('#reservation-time').daterangepicker({
		timePicker : true,
		timePickerIncrement : 30,
		locale : {
			format : 'MM/DD/YYYY h:mm A'
		}
	});

}

/* SMART WIZARD */

function init_SmartWizard() {

	if (typeof ($.fn.smartWizard) === 'undefined') {
		return;
	}
	console.log('init_SmartWizard');

	$('#wizard').smartWizard();

	$('#wizard_verticle').smartWizard({
		transitionEffect : 'slide'
	});

	$('.buttonNext').addClass('btn btn-success');
	$('.buttonPrevious').addClass('btn btn-primary');
	$('.buttonFinish').addClass('btn btn-default');

};

/* VALIDATOR */

function init_validator() {

	if (typeof (validator) === 'undefined') {
		return;
	}
	console.log('init_validator');

	// initialize the validator function
	validator.message.date = 'not a real date';

	// validate a field on "blur" event, a 'select' on 'change' event & a
	// '.reuired' classed multifield on 'keyup':
	$('form').on('blur', 'input[required], input.optional, select.required',
			validator.checkField).on('change', 'select.required',
			validator.checkField).on('keypress', 'input[required][pattern]',
			validator.keypress);

	$('.multi.required').on('keyup blur', 'input', function() {
		validator.checkField.apply($(this).siblings().last()[0]);
	});

	/*
	$('form').submit(function(e) {
		e.preventDefault();
		var submit = true;

		// evaluate the form using generic validaing
		if (!validator.checkAll($(this))) {
			submit = false;
		}

		if (submit)
			this.submit();

		return false;
	});
	*/
};

/* CUSTOM NOTIFICATION */

function init_CustomNotification() {

	console.log('run_customtabs');

	if (typeof (CustomTabs) === 'undefined') {
		return;
	}
	console.log('init_CustomTabs');

	var cnt = 10;

	TabbedNotification = function(options) {
		var message = "<div id='ntf"
				+ cnt
				+ "' class='text alert-"
				+ options.type
				+ "' style='display:none'><h2><i class='fa fa-bell'></i> "
				+ options.title
				+ "</h2><div class='close'><a href='javascript:;' class='notification_close'><i class='fa fa-close'></i></a></div><p>"
				+ options.text + "</p></div>";

		if (!document.getElementById('custom_notifications')) {
			alert('doesnt exists');
		} else {
			$('#custom_notifications ul.notifications')
					.append(
							"<li><a id='ntlink"
									+ cnt
									+ "' class='alert-"
									+ options.type
									+ "' href='#ntf"
									+ cnt
									+ "'><i class='fa fa-bell animated shake'></i></a></li>");
			$('#custom_notifications #notif-group').append(message);
			cnt++;
			CustomTabs(options);
		}
	};

	CustomTabs = function(options) {
		$('.tabbed_notifications > div').hide();
		$('.tabbed_notifications > div:first-of-type').show();
		$('#custom_notifications').removeClass('dsp_none');
		$('.notifications a')
				.click(
						function(e) {
							e.preventDefault();
							var $this = $(this), tabbed_notifications = '#'
									+ $this.parents('.notifications').data(
											'tabbed_notifications'), others = $this
									.closest('li').siblings().children('a'), target = $this
									.attr('href');
							others.removeClass('active');
							$this.addClass('active');
							$(tabbed_notifications).children('div').hide();
							$(target).show();
						});
	};

	CustomTabs();

	var tabid = idname = '';

	$(document).on('click', '.notification_close', function(e) {
		idname = $(this).parent().parent().attr("id");
		tabid = idname.substr(-2);
		$('#ntf' + tabid).remove();
		$('#ntlink' + tabid).parent().remove();
		$('.notifications a').first().addClass('active');
		$('#notif-group div').first().css('display', 'block');
	});

};

/* COMPOSE */

function init_compose() {

	if (typeof ($.fn.slideToggle) === 'undefined') {
		return;
	}
	console.log('init_compose');

	$('#compose, .compose-close').click(function() {
		$('.compose').slideToggle();
	});

};

/* CALENDAR */

function init_calendar() {

	if (typeof ($.fn.fullCalendar) === 'undefined') {
		return;
	}
	console.log('init_calendar');

	var date = new Date(), d = date.getDate(), m = date.getMonth(), y = date
			.getFullYear(), started, categoryClass;

	var calendar = $('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay,listMonth'
		},
		selectable : true,
		selectHelper : true,
		select : function(start, end, allDay) {
			$('#fc_create').click();

			started = start;
			ended = end;

			$(".antosubmit").on("click", function() {
				var title = $("#title").val();
				if (end) {
					ended = end;
				}

				categoryClass = $("#event_type").val();

				if (title) {
					calendar.fullCalendar('renderEvent', {
						title : title,
						start : started,
						end : end,
						allDay : allDay
					}, true // make the event "stick"
					);
				}

				$('#title').val('');

				calendar.fullCalendar('unselect');

				$('.antoclose').click();

				return false;
			});
		},
		eventClick : function(calEvent, jsEvent, view) {
			$('#fc_edit').click();
			$('#title2').val(calEvent.title);

			categoryClass = $("#event_type").val();

			$(".antosubmit2").on("click", function() {
				calEvent.title = $("#title2").val();

				calendar.fullCalendar('updateEvent', calEvent);
				$('.antoclose2').click();
			});

			calendar.fullCalendar('unselect');
		},
		editable : true,
		events : [ {
			title : 'All Day Event',
			start : new Date(y, m, 1)
		}, {
			title : 'Long Event',
			start : new Date(y, m, d - 5),
			end : new Date(y, m, d - 2)
		}, {
			title : 'Meeting',
			start : new Date(y, m, d, 10, 30),
			allDay : false
		}, {
			title : 'Lunch',
			start : new Date(y, m, d + 14, 12, 0),
			end : new Date(y, m, d, 14, 0),
			allDay : false
		}, {
			title : 'Birthday Party',
			start : new Date(y, m, d + 1, 19, 0),
			end : new Date(y, m, d + 1, 22, 30),
			allDay : false
		}, {
			title : 'Click for Google',
			start : new Date(y, m, 28),
			end : new Date(y, m, 29),
			url : 'http://google.com/'
		} ]
	});

};

$(document).ready(function() {
	init_sidebar();
	init_wysiwyg();
	init_InputMask();
	init_knob();
	init_IonRangeSlider();
	init_ColorPicker();
	init_TagsInput();
	init_daterangepicker();
	init_daterangepicker_right();
	init_daterangepicker_single_call();
	init_daterangepicker_reservation();
	init_SmartWizard();
	init_skycons();
	init_select2();
	init_validator();
	init_starrr();
	init_calendar();
	init_compose();
	init_CustomNotification();
	init_autosize();
});
