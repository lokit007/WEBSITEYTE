var refreshId = setInterval(function(){
	    $("#load-60").load('../dataThongTin.jsp');
}, 60000);

var dataState = setInterval(function(){
    $("#div-state").load('data-state.action');
}, 1800000);

$(function() {
	$("#div-state").load('data-state.action');
});

function runSourd() {
	var snd = new Audio("../sourds/button-3.wav");
	snd.volume = 0.05;
	snd.play();
}

function InitPopover(idelement, title) {
	$("#" + idelement).popover({
		title : title,
		content : $('#divContent' + idelement).html(),
		placement : 'bottom',
		delay : {
			show : 300,
			hide : 100
		},
		html : true
	});
}

$('body').on('click', function(e) {
	$('.btn-open-popover').each(function() {
		if(!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
			$(this).popover('hide');
		}
	});
});

function show(e){
	if(e == 1){
		$('#div-menu').css('display', 'inline');
		$('#icon-menu').attr('onclick', 'show(0);');
	} else {
		$('#div-menu').css('display', 'none');
		$('#icon-menu').attr('onclick', 'show(1);')
	}
}
function ShowView(e, o){
	if(e == 1){
		$('#dataChange').css('display', 'inline');
		$(o).attr('onclick', 'show(0, this);');
	} else {
		$('#dataChange').css('display', 'none');
		$(o).attr('onclick', 'show(1, this);');
	}
}
function ShowSubMenu(e){
	if(e == 1){
		$('#dataMenu').css('display', 'inline');
		$('#qltv').attr('onclick', 'ShowSubMenu(0);');
		$('#icon-caret').attr('class', 'fa fa-caret-square-o-up');
	} else {
		$('#dataMenu').css('display', 'none');
		$('#qltv').attr('onclick', 'ShowSubMenu(1);');
		$('#icon-caret').attr('class', 'fa fa-caret-square-o-down');
	}
}