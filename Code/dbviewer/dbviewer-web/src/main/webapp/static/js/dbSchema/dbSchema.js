var dbSchema = {
	route : { // 路由统一定义
		doList : {type: 'POST', url: contextPath + 'dbSchema/list'}  //ajax获取列表数据
	},
	doList: function(dataSourceId, params, callback) { // ajax获取列表数据
		var $this = this;
		var route = dbSchema.route.doList;
		if(route && route['url']){
			$.ajax({
				type : route['type'] || 'POST',
        		url: route['url'],
        		data: $.extend(params, {dataSourceId: dataSourceId}) || {},
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
	},
	doGenerateDoc: function($dataSourceId, $dbSchema){
		console.log($dataSourceId);
		console.log($dbSchema);
	}
};