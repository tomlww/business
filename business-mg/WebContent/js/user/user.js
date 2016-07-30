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
				var userCode = $.trim($('#userCode').val());
				var phoneVali = phoneValidate(userCode);
				if(!phoneVali) return;

				if(!$(this).hasClass('disabled')){
					$('#userCode').addClass('disabled').attr('disabled',true);
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
				var userCode = $.trim($('#userCode').val());
				var phoneVali = userValidate(userCode);
				if(!phoneVali) return;
				
				var password = $.trim($('#password').val());
				var pwsVali = pwsValidate(password);
				if(!pwsVali) return;
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
				/*var userName = $("#phoneNum").val();
				var codeInp = $("#codeInp").val();*/
				
				var user = {
						"userCode":$("#userCode").val(),
						"password":$("#password").val(),
						"verifyCode":$("#btnCode").val()
				};
				
				$.ajax({
			   		url:"../user/add.do",
			   		type:"post",
			   		data:user,
					dataType:'json',
			  		success:function(res){
			  			if(res.status == "fail"){
			  				alert("注册失败!");
			  			}
			  		},
			  		error:function(){
//						alert(系统异常);
			  		}
			  	});
			
			});
			$("#loginbtn").click(function(){
				var userCode = $.trim($('#userCode').val());
				var phoneVali = userValidate(userCode);
				if(!phoneVali) return;
				
				var password = $.trim($('#password').val());
				var pwsVali = pwsValidate(password);
				if(!pwsVali) return;
				
				var user = {
						"userCode":$("#userCode").val(),
						"password":$("#password").val()
				};
				$.ajax({
					url:"../user/login",
					data:user,
					type:"post",
					dataType:'json',
					success:function(res){
						if(res.status == "fail"){
							alert("失败!");
						}else{
							window.location.href="../sys/index";
						}
						
					}
				});
			});
		}
		
	}
	check.init();
})
