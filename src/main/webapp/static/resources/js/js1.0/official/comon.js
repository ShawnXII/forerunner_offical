$(document).ready(function(){
	var url=window.location.href;
	var str=url.split("/");
	if(str[5]!=""){
		var str1=str[5].split(".")[0];
		 if(str1=="newDynamic"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(1).addClass("checked");
			//$(".two_leftNav_c a").eq(0).addClass("checked");
			$(".two_nav span:last").html("新闻动态");
		}else if(str1=="newDynamic_detail"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(1).addClass("checked");
			//$(".two_leftNav_c a").eq(0).addClass("checked");
			$(".two_nav span:last").html("新闻动态");
		} 
		 else if(str1=="aboutUs"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(2).addClass("checked");
			//$(".two_leftNav_c a").eq(1).addClass("checked");
			$(".two_nav span:last").html("关于我们");
		}else if(str1=="joinIn"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(3).addClass("checked");
			//$(".two_leftNav_c a").eq(2).addClass("checked");
			$(".two_nav span:last").html("加盟合作");
		}else if(str1=="employment"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(4).addClass("checked");
			//$(".two_leftNav_c a").eq(3).addClass("checked");
			$(".two_nav span:last").html("诚聘英才");
		}else if(str1=="contactUs"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(5).addClass("checked");
			//$(".two_leftNav_c a").eq(5).addClass("checked");
			$(".two_nav span:last").html("联系我们");
		}else if(str1=="sitesMap"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(6).addClass("checked");
			//$(".two_leftNav_c a").eq(4).addClass("checked");
			$(".two_nav span:last").html("网站地图");
		}else if(str1=="links"){
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(7).addClass("checked");
			//$(".two_leftNav_c a").eq(6).addClass("checked");
			$(".two_nav span:last").html("友情链接");
		}else{
			$(".head_bar_con_l a").removeClass("checked");
			//$(".two_leftNav_c a").removeClass("checked");
			$(".head_bar_con_l a").eq(0).addClass("checked");
			//$(".two_leftNav_c a").eq(0).addClass("checked");
		}
	}
	/**加载更多**/
	$(".employment_btn").live("click",function(){
		$(".employment_data_c:gt(5)").show();
	})
})


/**分页公共方法**/
function pageList(pageNum,pageTotal){
	var pageNum=parseInt(pageNum);
	var pageTotal=parseInt(pageTotal);
	$('.dis_page_list').find('li').remove();
	if(pageTotal<=0){
		var li=$("<li class='pageff pageff_hover'><a onclick='list(1)'>1</a></li>").appendTo($('.dis_page_list'));
	}else{
		if((pageNum-1)>=3){
			for(var i=(pageNum-2);i<=pageTotal&&i<(pageNum+3);i++){
				if(pageNum==i){
					var li=$("<li class='pageff pageff_hover'><a onclick='list(1)'>"+i+"</a></li>").appendTo($('.dis_page_list'));
				}else{
					var li=$("<li class='pageff'><a onclick='list("+i+")'>"+i+"</a></li>").appendTo($('.dis_page_list'));
				}
			}
		}else{
			for(var i=1;i<=pageTotal&&i<6;i++){
				if(pageNum==i){
					var li=$("<li class='pageff pageff_hover'><a onclick='list(1)'>"+i+"</a></li>").appendTo($('.dis_page_list'));
				}else{
					var li=$("<li class='pageff'><a onclick='list("+i+")'>"+i+"</a></li>").appendTo($('.dis_page_list'));
				}
			}
		}
	}
	/**上一页**/
	if(pageNum>0){
		if(pageNum==1){
			$(".prevtm").attr("onclick","list(1)");
			
		}else{
			$(".prevtm").attr("onclick","list("+(pageNum-1)+")");
		}
	}
	/**下一页**/
	if(pageNum<pageTotal){
		$(".lastm").attr("onclick","list("+(pageNum+1)+")");
	}else{
		$(".lastm").attr("onclick","list("+pageTotal+")");
	}
	/**总条数**/
	$("#pageCount_s").html(pageTotal);
	$(".pages").val(pageNum);
	$(".user_cart_detail_1").css("height","auto");
	var height=parseInt($(".user_cart_detail_1").height());
	if(height<800){
		height=800;
		$(".user_cart_detail_1").height(800);
	}
	$(".user_all").height(height+10);
	$(".floot_total").height(height+236);
}