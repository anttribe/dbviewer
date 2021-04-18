$(function(){
    /********************************页面元素美化初始化********************************/

	/********************************页面数据初始化*********************************/
	// 数据源列表展示
	listDataSources({});
	
	function listDataSources(params){
		dataSource.doList(params, {
			success: function($dataSources){
				if($dataSources && $dataSources.length>0){
					var $dataSourceHtml = '';
					for(var i=0; i<$dataSources.length; i++){
						var $dataSource = $dataSources[i];
						if(!$dataSource || !$dataSource['alias']){
							continue;
						}
						$dataSourceHtml += '<li class="">' 
							             + '<a href="javascript:;" data-id="' + $dataSource['id'] + '" class="dropdown-toggle ds-list-item" title="' + $dataSource['alias'] + '"><i class="menu-icon fa fa-database"></i><span class="menu-text"> ' + $dataSource['alias'] + '</span><span class="toolbar"><i class="action-edit-ds ace-icon fa fa-edit" title="编辑数据源"></i></span><b class="arrow fa fa-angle-down"></b></a>'
							             + '<b class="arrow"></b>'
							             + '<ul class="submenu"></ul>'
							             + '</li>';
					}
					$('#ds-list').empty().append($dataSourceHtml);
					
					$('a.ds-list-item', '#ds-list').click(function(e){
						// 判断是否已经加载
						if(!$(this).hasClass('loaded')){
							var $dataSourceId = $(this).attr('data-id');
							if($dataSourceId){
								listDbSchemas($dataSourceId, {}, $(this).siblings('.submenu'));
								$(this).addClass('loaded');
							}
						}
					});
					$('.toolbar>.action-edit-ds', 'a.ds-list-item').click(function(e){
						e.preventDefault();
						// 阻止事件冒泡 
						e.stopPropagation();
						var $aParents = $(this).parents('a');
						if($aParents && $aParents.length>0){
							var $dataSourceId = $($aParents).attr('data-id');
							if($dataSourceId){
								dataSource.doGet($dataSourceId, {
									success: function($dataSource){
										dataSource.edit($dataSource, {
											success: function(){
												BootstrapDialog.alert('数据源修改成功!', function(){
													window.location.reload();
										        });
											}
										});
									}
								});
							}
						}
					});
				}
			}
		});
	}
	
	function listDbSchemas($dataSourceId, params, $container){
		dbSchema.doList($dataSourceId, params, {
			success: function($dbSchemas){
				if($dbSchemas && $dbSchemas.length>0){
					var $dbSchemaHtml = '';
					for(var i=0; i<$dbSchemas.length; i++){
						var $dbSchema = $dbSchemas[i];
						if(!$dbSchema || !$dbSchema['fullName']){
							continue;
						}
						// <i class="action-schema-showER ace-icon fa fa-th-large" title="ER图表"></i>
						// <i class="action-schema-generateDoc ace-icon fa fa-file-word-o" title="生成文档"></i>
						$dbSchemaHtml += '<li class="">'
							           + '<a href="javascript:;" data-id="' + $dbSchema['fullName'] + '" class="dropdown-toggle ds-schema-list-item" title="' + $dbSchema['fullName'] + '"><span class="menu-text"> ' + $dbSchema['fullName'] + '</span><span class="toolbar"><i class="action-schema-generateDoc ace-icon fa fa-file-word-o" title="生成文档"></i></span><b class="arrow fa fa-angle-down"></b></a>'
							           + '<b class="arrow"></b>'
							           + '<ul class="submenu">'
							           + '<li class="">'
							           + '<a href="javascript:;" data-schema="' + $dbSchema['fullName'] + '" class="dropdown-toggle ds-schema-table"><i class="menu-icon fa fa-table"></i><span class="menu-text"> 表</span><b class="arrow fa fa-angle-down"></b></a>'
							           + '<ul class="submenu">'
							           + '</ul>'
							           + '</li>'
							           + '</ul>'
							           + '</li>';
					}
					$($container).empty().append($dbSchemaHtml);
					
					$('a.ds-schema-table', $container).click(function(e){
						if(!$(this).hasClass('loaded')){
							var $dbSchema = $(this).attr('data-schema');
						    if($dbSchema){
						    	listSchemaDbTables($dataSourceId, $dbSchema, {}, $(this).siblings('.submenu'));
						    	$(this).addClass('loaded');
						    }
						}
					});
					
					$('.toolbar>.action-showER', 'a.ds-schema-list-item').click(function(e){
						// 阻止事件冒泡 
						e.stopPropagation();
					});
					
					// 生成数据库文档
					$('.toolbar>.action-schema-generateDoc', 'a.ds-schema-list-item').click(function(e){
						// 阻止事件冒泡 
						e.stopPropagation();
						
						var $a = $(this).parents('a');
						if($a && $a.length>0){
							var $dbSchema = $($a[0]).attr('data-id');
						    if($dbSchema){
						    	dbSchema.doGenerateDoc($dataSourceId, $dbSchema);
						    }
						}
					});
				}
			}
		});
	}
	
	function listSchemaDbTables($dataSourceId, $dbSchema, params, $container){
		dbTable.doList($dataSourceId, $dbSchema, params, {
			success: function($dbTables){
			    if($dbTables && $dbTables.length > 0){
			    	var $dbTableHtml = '';
			    	for(var i=0; i<$dbTables.length; i++){
			    		var $dbTable = $dbTables[i];
			    		if(!$dbTable || !$dbTable['tableName']){
			    			continue;
			    		}
			    		$dbTableHtml += '<li class="">'
					                  + '<a href="javascript:;" data-id="' + $dbTable['tableName'] + '" class="dropdown-toggle ds-table-list-item" title="' + ($dbTable['comment'] || '') + '"><i class="menu-icon fa fa-table"></i><span class="menu-text"> ' + $dbTable['tableName'] + '</span></a>'
					                  + '<b class="arrow"></b>'
					                  + '</li>';
			    	}
			    	$($container).empty().append($dbTableHtml);
			    	
			    	$('a.ds-table-list-item', $container).click(function(e){
			    		var $dbTableName = $(this).attr('data-id');
					    if($dbTableName){
					    	getDbTableInfo($dataSourceId, $dbSchema, $dbTableName);
					    	$(this).addClass('loaded');
					    }
					});
			    }	
			}
		});
	}
	
	function getDbTableInfo($dataSourceId, $dbSchema, $dbTableName){
		if($dbTableName){
			var $dbTableKey = $dbSchema + '_' + $dbTableName;
			var $dbTableTab = $('#tab-' + $dbTableKey);
			if($dbTableTab && $dbTableTab.length>0){
				$('a[data-toggle="tab"]', $dbTableTab).tab('show');
				return;
			}
			
			// 展示表结构
			var $dbTableTabHtml = '<li id="tab-' + $dbTableKey + '" data-id="' + $dbTableName + '" class="">'
			                    + '<a data-toggle="tab" href="#tab-content-' + $dbTableKey + '" class=""><i class="ace-icon fa fa-table bigger-120 blue"></i> ' + ($dbTableName + '@' + $dbSchema) + ' <i class="action-remove ace-icon fa fa-close red2"></i></a>'
			                    + '</li>';
			$dbTableTab = $($dbTableTabHtml);
			$('.ds-content-tabs>.nav-tabs').append($dbTableTab);
			
			var $dbTableTabContentHtml = '<div id="tab-content-' + $dbTableKey + '" data-id="' + $dbTableName + '" class="tab-pane fade">'
			                           + '</div>';
			var $dbTableTabContent = $($dbTableTabContentHtml);
			$('.ds-content-tabs>.tab-content').append($dbTableTabContent);
			
			// 展示当前tab
			$('a[data-toggle="tab"]', $dbTableTab).tab('show');
			$('.ds-content-tabs').removeClass('hidden').show();
			
			//关闭tab页签
			$('.action-remove', $dbTableTab).click(function(e){
				e.preventDefault();
				
				var $tempTab = $('#tab-' + $dbTableKey, $('.ds-content-tabs>.nav-tabs'));
				var $tempTabContent = $('#tab-content-' + $dbTableKey, $('.ds-content-tabs>.tab-content'));
				$tempTabContent.remove();
				$tempTab.remove();
				
				// 默认显示第一个tab
				var $tabs = $('li', $('.ds-content-tabs>.nav-tabs'));
				if($tabs.length <=0 ){
					$('.ds-content-tabs').addClass('hidden');
				} else{
					$('a[data-toggle="tab"]', $tabs[0]).tab('show');
				}
			});
			
			// 表视图展现
			dbTable.doGet($dataSourceId, $dbSchema, $dbTableName, {
				success: function($dbTable){
					var $viewer = dbTable.dbTableView($.extend($dbTable, {dbSchema: $dbSchema}));
					$dbTableTabContent.append($viewer);
				}
			});
		}
	}
	
	/********************************页面表单事件********************************/
	// 按钮事件绑定
	$('#action-add-ds').click(function(e){
		e.preventDefault();
		
		dataSource.add({}, {
			success: function(){
				BootstrapDialog.alert('数据源添加成功!', function(){
					window.location.reload();
		        });
			}
		});
	});
	$('.action-edit').click(function(e){
		e.preventDefault();
		
		var $widgetCon = $(this).parents('.widget-container-col');
		if($widgetCon && $widgetCon.length>0){
			var id = $widgetCon.attr('data-id');
			if(id){
				dataSource.doGet(id, {
					success: function($dataSource){
						dataSource.edit($dataSource, {
							success: function(){
								BootstrapDialog.alert('数据源修改成功!', function(){
									window.location.reload();
						        });
							}
						});
					}
				});
			}
		}
	});
	$('.action-remove').click(function(e){
		e.preventDefault();
		
		var $widgetCon = $(this).parents('.widget-container-col');
		if($widgetCon && $widgetCon.length>0){
			var id = $widgetCon.attr('data-id');
			if(id){
				dataSource.doRemove(id, {
					success: function(){
						BootstrapDialog.alert('数据源删除成功!', function(){
							window.location.reload();
				        });
					}
				});
			}
		}
	});
});