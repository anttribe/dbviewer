$(function(){
    /********************************页面元素美化初始化********************************/

	/********************************页面数据初始化*********************************/
	
	/********************************页面表单事件********************************/
	// 按钮事件绑定
	$('.action-login').click(function(e){
		e.preventDefault();
		
		var $userNameVal = $('#login-username-input').val();
		if(!$userNameVal){
			BootstrapDialog.warning('请输入登录名!');
			return;
		}
		var $passwordVal = $('#login-password-input').val();
		if(!$passwordVal){
			BootstrapDialog.warning('请输入登录密码!');
			return;
		}
		var $loginUser = {
		    username: $userNameVal,
		    password: $passwordVal
		};
		console.log($loginUser);
		
		authc.doLogin($loginUser, {
			success: function(){
				// 登录成功，跳转首页
				window.location.href = contextPath + 'index';
			},
			error: function(resultCode){
				BootstrapDialog.alert(resultCode, function(){
		        });
			}
		});
	});
});