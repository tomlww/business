$(function(){
	var index = {
			init:function(){
				$.ajax({
					url:"../sys/getAdPosition",
					type:"post",
					dataType:"json",
					success:function(data){
			  			if(data.status == "success" && data.list.length>0){
			  				$(".ad_div").css("background-image"," url("+data.list[0].srcPath+")");
			  			}else{
			  				alert("加载失败!");
			  			}
			  		},
			  		error:function(){
						alert("系统异常,请联系商家!");
			  		}
				});
			},
			initApPosition:function(){
				$.ajax({
					url:"../sys/getGoodsTypeAd",
					type:"post",
					dataType:"json",
					success:function(data){
						/*if(){
							
						}else{
							
						}*/
					}
				});
			},
			initGoods:function(){
				$.ajax({
					url:"../goods/getGoods",
					type:"post",
					dataType:"json",
					success:function(data){
						alert(data);
					}
				});
			}
	};
	index.init();//初始化首页广告
});