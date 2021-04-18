var authc = {
	route : { // 路由统一定义
		doLogin : {type: 'POST', url: contextPath + 'login'}
	},
	doLogin: function($loginUser, callback){
		if($loginUser){
			var $this = this;
			var route = authc.route.doLogin;
			if(route && route['url']){
				$.ajax({
					type : route['type'] || 'POST',
					url : route['url'],
					data : $loginUser,
            		success: function(r){
            			if(r){
            				if(r.resultCode == '000000'){
            					if (callback && callback.success && $.isFunction(callback.success)) {
            						callback.success.call($this, r['resultBody']);
    							}
            				} else{
            					if (callback && callback.error && $.isFunction(callback.error)) {
    								callback.error.call($this, r.resultCode);
    							}
            				}
    				    }
            		},
            		error: function(e){
            			if (callback && callback.error && $.isFunction(callback.error)) {
							callback.error.call($this);
						}
            		}
            	});
			}
		}
	}
};