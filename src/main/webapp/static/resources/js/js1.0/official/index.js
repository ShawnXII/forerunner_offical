var news_showAuto=null;
var slide_showAuto=null;
$(document).ready(function(){
	news_showAuto=setInterval("showNews();",5000);
	slide_showAuto=setInterval("showSlide();",6000);
	
	$('.slide-img-switch_ul_btn li').each(function(){
		var that=$(this);
		var data={'type':'slide'}
		that.click(data,auto);
	})
	$('.news-img-switch_ul_btn li').each(function(){
		var that=$(this);
		var data={'type':'news'}
		that.click(data,auto);
		
	});
})

function auto(event){
	var type=event.data.type;
	var that=$(this);
	if(that.hasClass('img_select_1')){
		return;
	}
	var index=that.index();
	var len=that.parent().find('li').length;
	that.parent().find('li').removeClass('img_select_1');
	that.parent().find('li').addClass('img_unselect_1');
	that.addClass('img_select_1');
	that.removeClass('img_unselect_1');
	
	var img_li=that.parent().parent().prev().find('ul li');
	img_li.slideUp(500);
	img_li.eq(index).slideDown(800);
	if(type=='news'){
		if(news_showAuto!=null){
			clearInterval(news_showAuto);
			news_showAuto=setInterval("showNews();",5000);
		}
	}else if(type=='slide'){
		if(slide_showAuto!=null){
			clearInterval(slide_showAuto);
			slide_showAuto=setInterval("showSlide();",6000);
		}
	}
	
}
function showNews(){
	showAuto($('.news-img-switch_ul'));
}
function showSlide(){
	showAuto($('.slide-img-switch_ul'));
}
/**
 * @param st
 */
function showAuto(st){
	var that=$(st);
	if(that.length<=0){
		return ;
	}
	if(!that.is('ul')){
		return ;
	}
	var len=that.find('li').length;
	var index=that.find('li:visible').first().index();
	var btn_ul=that.parent().next().find('ul');
	var btn_li=btn_ul.find('li');
	btn_li.removeClass('img_select_1');
	btn_li.addClass('img_unselect_1');
	var i=index+1;
	if(i>len-1){
		i=0;
	}
	var li=that.find('li');
	li.slideUp(500);
	li.eq(i).slideDown(800);
	btn_li.eq(i).removeClass('img_unselect_1');
	btn_li.eq(i).addClass('img_select_1');
}

//返回顶部
var goto_top_type = -1;
var goto_top_itv = 0;
function goto_top_timer()
{
	var y = goto_top_type == 1 ? document.documentElement.scrollTop : document.body.scrollTop;
	var moveby = 15;
	y -= Math.ceil(y * moveby / 100);
	if (y < 0) {
		y = 0;
	}
	if (goto_top_type == 1) {
		document.documentElement.scrollTop = y;
	}
	else {
		document.body.scrollTop = y;
	}
	if (y == 0) {
		clearInterval(goto_top_itv);
		goto_top_itv = 0;
	}
}
function goto_top(){
	if (goto_top_itv == 0) {
		if (document.documentElement && document.documentElement.scrollTop) {
			goto_top_type = 1;
		}else if (document.body && document.body.scrollTop) {
			goto_top_type = 2;
		}else {
			goto_top_type = 0;
		}
		if (goto_top_type > 0) {
			goto_top_itv = setInterval('goto_top_timer()', 0);
		}
	}
}