$(document).ready(function(){
	checkNews(null);
});

function checkNews(id){
	if(!(id!=null&&id!='')){
		var hash=window.location.hash;
		if(hash!=null&&hash!=''){
			id=hash;
		}
	}
	var data={'id':id};
	$.ajax({
		type:'POST',
		dataType:'json',
		url:"/index/news",
		data:data,
		async:true,
		success:function(msg){
			if(typeof(msg)!='undefined'){
				var flag=msg.flag;
				if(flag==true){
					var art=msg.art;
					var id=art.id;
					window.location.hash=id;
					var title=art.title;
					var color=art.color;
					var description=art.description;
					var content=art.articleData.content;
					var copyfrom=art.articleData.copyfrom;
					var relation=art.articleData.relation;
					var username=art.user.name;
					var author=art.author;
					var d=new Date(art.createDate);
					$('#current_position').find('span').last().remove();
					$('<a href="/officer/newdynamic">新闻公告</a><span class="arrow">&gt;</span><span>'+title+'</span>').appendTo($('#current_position'));
					var nowDate=d.getFullYear()+'-'+d.getMonth()+'-'+d.getDay();
					$('.two_con').find('.two_leftNav').hide();
					$('.two_con').find('.two_con_r').remove();
					var tcr=$('<div class="two_con_r"></div>').appendTo($('.two_con'));
					var cd=$('<div class="common_data"></div>').appendTo(tcr);
					var nt=$('<div class="newDynamic_title"></div>').appendTo(cd)
					$('<h1>'+title+'</h1>').appendTo(nt);
					if(copyfrom!=null&&copyfrom!=''){
						$('<div class="time">'+nowDate+'<span>来源：'+copyfrom+'</span></div>').appendTo(nt);
					}else{
						$('<div class="time">'+nowDate+'</div>').appendTo(nt);
					}
					var nc=$('<div class="newDynamic_con"></div>').appendTo(cd);
					if(description!=null&&description!=''){
						$('<div class="newDynamic_con_zy"><span>摘要 :</span>'+description+'</div>').appendTo(nc);
					}
					$('<div class="newDynamic_con_data">'+content+'</div>').appendTo(nc);
					var nb=$('<div class="newDynamic_bj"></div>').appendTo(cd);
					if(author!=null&&author!=''){
						nb.html('责编:'+author);
					}else{
						nb.html('责编:'+username);
					}
					$('<div class="clear"></div>').appendTo(tcr);
				}
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			return;
		}
	});				
}