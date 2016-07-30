$(function(){
	var check = {
		init:function(){
			var self = this;
			self.bind();
		},
		bind:function(){
			var self = this;
			//验证码按钮
			$('#btnCode').on('click',function(){
				$('.icon-error').removeClass('icon-error r0');

				//手机号前台验证
				var phoneNum = $.trim($('#phoneNum').val());
				var phoneVali = phoneValidate(phoneNum);
				if(!phoneVali) return;

				if(!$(this).hasClass('disabled')){
					$('#phoneNum').addClass('disabled').attr('disabled',true);
					countdown(120,$('#btnCode'));
				}
			})
			//验证手机号 下一步按钮
			$('#btnNext').on('click',function(){
				if($(this).hasClass('icon-load')){
	 				alert('页面正在努力加载中，请耐心等待')
	 				return;
	 			}
				$('.item-right').removeClass('icon-error r0');
				
				//手机号前台验证
				var phoneNum = $.trim($('#phoneNum').val());
				var phoneVali = phoneValidate(phoneNum);
				if(!phoneVali) return;

				//验证码前台验证
				var codeVal = $('#codeInp').val();
				if(codeVal == null || codeVal == ''){
					$('#codeInp').parent().addClass('icon-error r0');
					showError('codeInp','请输入验证码');
					return;

				}else if(!/^\d{6}$/g.test(codeVal)){
					$('#codeInp').parent().addClass('icon-error r0');
					showError('codeInp','验证码格式有误');
					return;
				}
				//ajax验证 验证码
				var userName = $("#phoneNum").val();
				var codeInp = $("#codeInp").val();
				  $.ajax({
			      url : "../user/register.do",
			      type : 'post',
			      dataType : "json",
			      data:{"userName":userName,"codeInp":codeInp},
			      success : function(result){
				    	if(result.status=="fail"){
				    	 alert(result.msger);
				    	}
				    },
				    error:function(){
				    	$("#"+tableId+" tr").eq(0).after('<tr class="odd"><td colspan="7" >对不起，当前条件下无记录</td></tr>');
				    }
				  });
				/*var isPass = true;
				if(isPass){
					$(this).text('加载中').addClass('icon-load');				
					window.location = 'XXX.html';	
				}*/
			});
		}
	}
	check.init();
})
