$('.btn').on('click',function(){
	$('#perror').remove();
});

//input 获得焦点，失去焦点，有输入值监听事件
$('input').on('focus',function(){
	var $this = $(this);
	$this.parent().addClass('safe');
	

});
$('input').on('blur',function(){
	$('.perror').remove();
	$('.icon-error').removeClass('icon-error r0');
	var $this = $(this);
	$this.parent().removeClass('safe');
});
$('input').on('input',function(){
	var val = $(this).val();
	$('.perror').remove();
	$('.icon-error').removeClass('icon-error r0');
	var $this = $(this);
	var $placeholder = $this.siblings('.placeholder');
	$placeholder.text('');
	if($this.hasClass('color-error') && !val.length){
 		$this.removeClass('color-error');
 	}
});



//验证码倒计时
function countdown(num,obj){
 	var timer = setInterval(function(){
 		if(num<0){
 			clearInterval(timer);
 			obj.html('重新发送').removeClass('disabled row2');	
 			$('#phoneNum').removeClass('disabled').attr('disabled',false);	
 			return;
 		}
 		
 		obj.html('输入时间<br/>'+(num--)+'s').addClass('disabled row2');		
 	},1000);
}

// 获取当前页面高度
function getDocHeight(){
	return $(document).height();
}


//显示错误信息
function showError(id,str){
	var $perror = $('#error');
	if($perror.length){
		$perror.remove();

	}
	var $wrap = $('#'+id).parents('.item-wrap');
	if(!$perror.length){
		$error = $wrap.after('<p class="perror" id="perror">'+str+'</p>');
		$error.show();
		var top = $error.position()['top'];
		var scrollTop = $(window).scrollTop(top);

	}
	
}



//隐藏错误信息
function hideError(){
	$('.perror').remove();
	$('.icon-error').removeClass('icon-error r0');
}



// 手机号校验
function phoneValidate(inpVal){
	var phoneNum = inpVal;
	if(phoneNum == null || phoneNum == ""){
		$('#phoneNum').parent().addClass('icon-error');
		showError('phoneNum','请输入手机号码');
		return false ;
	}else if(phoneNum.match(/^1(3[0-9]|7[0-9]|5[0-35-9]|8[01235-9])\d{8}$/)){
		$('#phoneNum').parent().addClass('icon-error');
		showError('phoneNum','请正确填写手机号码');
		return false;
	}
	return true;
}
//验证user
function userValidate(inpVal){
	var userCode = inpVal;
	if(userCode == null || userCode == ""){
		$('#userCode').parent().addClass('icon-error');
		showError('userCode','请输入用户名');
		return false ;
	}else if(userCode.match(/[^\u0000-\u00FF]/)){
		$('#userCode').parent().addClass('icon-error');
		showError('userCode','用户名不能为中文');
		return false;
	}
	return true;
}

//验证user
function pwsValidate(inpVal){
	var userCode = inpVal;
	if(userCode == null || userCode == ""){
		$('#password').parent().addClass('icon-error');
		showError('password','请输入密码');
		return false ;
	}else if(userCode.match(/[^\u0000-\u00FF]/)){
		$('#password').parent().addClass('icon-error');
		showError('password','密码不能为中文');
		return false;
	}
	return true;
}


//判断是否是空
function isNotEmpty(str) {
		str = str.replace(/\s/g, '');
		if(str == "")
			return false;
		else
			return true;
}

var commoncheck = {
	 trim : function(str){
	 	str = str.replace(/\s/g, '');
	 	return  str;
	}
};

function isPirce(s){
	s = commoncheck.trim(s);
    /*var p=/^[1-9]?$/;
    return p.test(s);*/
	return s;
}

