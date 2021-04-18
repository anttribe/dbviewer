var dataSource = {
	supportDatabases: [{
		name: 'MySQL', 
		dialect: 'MYSQL'
	}],
	route : { // 路由统一定义
		index : {type: 'GET', url: contextPath + 'dataSource'},  //请求主页面
		doList : {type: 'POST', url: contextPath + 'dataSource/list'},  //ajax获取列表数据
		// add : {type: 'GET', url: contextPath + 'dataSource/add'},  //请求新增页面
		doAdd : {type: 'POST', url: contextPath + 'dataSource/add'},  //新增数据
		//edit : {type: 'GET', url: contextPath + 'dataSource/edit'},  //请求编辑页面
		doEdit : {type: 'POST', url: contextPath + 'dataSource/edit'},  //编辑更新数据
		doGet : {type: 'POST', url: contextPath + 'dataSource'},
		view : {type: 'GET', url: contextPath + 'dataSource'},  //请求查看页面
		doRemove : {type: 'POST', url: contextPath + 'dataSource/remove'}  //删除数据
	},
	doList: function(params, callback) { // ajax获取列表数据
		var $this = this;
		var route = dataSource.route.doList;
		if(route && route['url']){
			$.ajax({
				type : route['type'] || 'POST',
        		url: route['url'],
        		data: params || {},
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
	add: function(params, callback){
		var $this = this;
		
		BootstrapDialog.show({
			title: '新增数据源',
			message: $('<div>').append('<form class="form-horizontal">'
	                                 + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-alias-input">别名</label>' + '<div class="col-sm-10"><input type="text" id="dataSource-alias-input" placeholder="数据源别名" class="form-control"></input></div>' + '</div>'
	                                 + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-dialect-selector">数据库类型</label>' + '<div class="col-sm-10"><select id="dataSource-dialect-selector" class="form-control">' + (function(){
	                	                 var $html = '';
	                	                 if(dataSource.supportDatabases && dataSource.supportDatabases.length>0){
	                		                 for(var i=0; i<dataSource.supportDatabases.length; i++){
	                		                	 var db = dataSource.supportDatabases[i];
	                		                	 $html += '<option value="' + (db['dialect']) + '">' + (db['name']) + '</option>';
	                		                 }
	                		             }
	                	                 return $html;
	                	             }()) + '</select></div>' + '</div>'
	                	             + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-connUrl-input">数据库连接URL</label>' + '<div class="col-sm-10"><input type="text" id="dataSource-connUrl-input" value="" placeholder="数据库连接URL" class="form-control"></input></div>' + '</div>'
	                	             + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-username-input">用户名</label>' + '<div class="col-sm-10"><input type="text" id="dataSource-username-input" placeholder="用户名" class="form-control"></input></div>' + '</div>'
	                	             + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-password-input">密码</label>' + '<div class="col-sm-10"><input type="password" id="dataSource-password-input" placeholder="密码" class="form-control"></input></div>' + '</div>'
	                	             + '</form>'),
			size: BootstrapDialog.SIZE_WIDE,
			buttons: [{
				label: '确定',
				icon: 'ace-icon fa fa-check-circle mr5',
				cssClass: 'btn btn-info btn-sm',
				action: function(dialogRef){
					// 数据校验
					var dataSourceAliasVal = $('#dataSource-alias-input', dialogRef.getMessage()).val();
					if(!dataSourceAliasVal){
						BootstrapDialog.warning('数据源别名 不能为空!');
						return;
					}
					var dataSourceConnUrlVal = $('#dataSource-connUrl-input', dialogRef.getMessage()).val();
					if(!dataSourceConnUrlVal){
						BootstrapDialog.warning('数据库链接url 不能为空!');
						return;
					}
					var dataSourceUsernameVal = $('#dataSource-username-input', dialogRef.getMessage()).val();
					if(!dataSourceUsernameVal){
						BootstrapDialog.warning('数据源用户 不能为空!');
						return;
					}

					// 构造数据对象
					var $dataSource = {
						alias: dataSourceAliasVal,
						dialect: $('#dataSource-dialect-selector', dialogRef.getMessage()).val(),
						connUrl: dataSourceConnUrlVal,
						username: dataSourceUsernameVal,
						password: $('#dataSource-password-input', dialogRef.getMessage()).val(),
					};
					dataSource.doAdd($dataSource, {
						success: function(){
							dialogRef.close();
							if (callback && callback.success && $.isFunction(callback.success)) {
								callback.success.call($this);
							}
						},
						error: function(rCode){
							if (callback && callback.error && $.isFunction(callback.error)) {
								callback.error.call($this, rCode);
							}
						}
					});
				}
			}, {
				label: '取消',
				icon: 'ace-icon fa fa-times-circle mr5',
				cssClass: 'btn btn-default btn-sm',
				action: function(dialogRef){
					dialogRef.close();
				}
			}]
		});
	},
	doAdd: function($dataSource, callback){  //新增数据
		if($dataSource){
			var $this = this;
			var route = dataSource.route.doAdd;
			if(route && route['url']){
				$.ajax({
					type : route['type'] || 'POST',
					url : route['url'],
					data : $dataSource,
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
	},
	edit: function($dataSource, callback){  //请求编辑
		if($dataSource && $dataSource['id']){
			var $this = this;
			
			BootstrapDialog.show({
				title: '新增数据源',
				message: $('<div>').append('<form class="form-horizontal">'
		                                 + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-alias-input">别名</label>' + '<div class="col-sm-10"><input type="text" id="dataSource-alias-input" value="' + ($dataSource['alias'] || '') + '" placeholder="数据源别名" class="form-control"></input></div>' + '</div>'
		                                 + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-dialect-selector">数据库类型</label>' + '<div class="col-sm-10"><select id="dataSource-dialect-selector" value="' + ($dataSource['dialect']) + '" class="form-control">' + (function(){
		                	                 var $html = '';
		                	                 if(dataSource.supportDatabases && dataSource.supportDatabases.length>0){
		                		                 for(var i=0; i<dataSource.supportDatabases.length; i++){
		                		                	 var db = dataSource.supportDatabases[i];
		                		                	 $html += '<option value="' + (db['dialect']) + '">' + (db['name']) + '</option>';
		                		                 }
		                		             }
		                	                 return $html;
		                	             }()) + '</select></div>' + '</div>'
		                	             + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-connUrl-input">数据库连接URL</label>' + '<div class="col-sm-10"><input type="text" id="dataSource-connUrl-input" value="' + ($dataSource['connUrl'] || '') + '" placeholder="数据库连接URL" class="form-control"></input></div>' + '</div>'
		                	             + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-username-input">用户名</label>' + '<div class="col-sm-10"><input type="text" id="dataSource-username-input" value=' + ($dataSource['username'] || '') + ' placeholder="用户名" class="form-control"></input></div>' + '</div>'
		                	             + '<div class="form-group">' + '<label class="col-sm-2 control-label no-padding-right" for="dataSource-password-input">密码</label>' + '<div class="col-sm-10"><input type="password" id="dataSource-password-input" value="' + ($dataSource['password'] || '') + '" placeholder="密码" class="form-control"></input></div>' + '</div>'
		                	             + '</form>'),
				size: BootstrapDialog.SIZE_WIDE,
				buttons: [{
					label: '确定',
					icon: 'ace-icon fa fa-check-circle mr5',
					cssClass: 'btn btn-info btn-sm',
					action: function(dialogRef){
						// 数据校验
						var dataSourceAliasVal = $('#dataSource-alias-input', dialogRef.getMessage()).val();
						if(!dataSourceAliasVal){
							BootstrapDialog.warning('数据源别名 不能为空!');
							return;
						}
						var dataSourceConnUrlVal = $('#dataSource-connUrl-input', dialogRef.getMessage()).val();
						if(!dataSourceConnUrlVal){
							BootstrapDialog.warning('数据库链接url 不能为空!');
							return;
						}
						var dataSourceUsernameVal = $('#dataSource-username-input', dialogRef.getMessage()).val();
						if(!dataSourceUsernameVal){
							BootstrapDialog.warning('数据源用户 不能为空!');
							return;
						}

						// 构造数据对象
						var $tempDataSource = {
							id: $dataSource['id'],
							alias: dataSourceAliasVal,
							dialect: $('#dataSource-dialect-selector', dialogRef.getMessage()).val(),
							connUrl: dataSourceConnUrlVal,
							username: dataSourceUsernameVal,
							password: $('#dataSource-password-input', dialogRef.getMessage()).val(),
						};
						
						dataSource.doEdit($tempDataSource, {
							success: function(){
								dialogRef.close();
								if (callback && callback.success && $.isFunction(callback.success)) {
									callback.success.call($this);
								}
							},
							error: function(rCode){
								if (callback && callback.error && $.isFunction(callback.error)) {
									callback.error.call($this, rCode);
								}
							}
						});
					}
				}, {
					label: '取消',
					icon: 'ace-icon fa fa-times-circle mr5',
					cssClass: 'btn btn-default btn-sm',
					action: function(dialogRef){
						dialogRef.close();
					}
				}]
			});
		}
	},
	doEdit: function($dataSource, callback){  //编辑更新数据
		if($dataSource && $dataSource['id']){
			var $this = this;
			var route = dataSource.route.doEdit;
			if(route && route['url']){
				$.ajax({
					type : route['type'] || 'POST',
					url : route['url'],
					data : $dataSource,
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
	},
	doGet: function(id, callback){
		if(id){
			var $this = this;
			var route = dataSource.route.doGet;
			if(route && route['url']){
				$.ajax({
					type : route['type'] || 'POST',
            		url: route['url'] + '/' + id,
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
	},
	doRemove: function(id, callback){  //删除数据
		if(id){
			var $this = this;
			var route = dataSource.route.doRemove;
			if(route && route['url']){
				$.ajax({
					type : route['type'] || 'POST',
            		url: route['url'],
            		data: {id: id},
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
	},
	view: function(id, params){
		if(id){
			var route = dataSource.route.view;
			if(route && route['url']){
				var queryString = params ? Utils.$buildRequestParams(params) : '';
				var _url = route.url + '/' + id + (queryString ? '?' + queryString : '');
				window.open(_url, '_blank');
			}
		}
	}
};