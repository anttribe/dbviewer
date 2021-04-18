$(function(){
    /********************************页面元素美化初始化********************************/

	/********************************页面数据初始化*********************************/
	
	/********************************页面表单事件********************************/
	// 按钮事件绑定
	$('#action-add').click(function(e){
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
	$('.action-add-db').click(function(e){
		e.preventDefault();
		
		var $widgetCon = $(this).parents('.widget-container-col');
		if($widgetCon && $widgetCon.length>0){
			var dataSourceId = $widgetCon.attr('data-id');
			if(dataSourceId){
				database.add({dataSource: {id: dataSourceId}}, {
					success: function(){
						BootstrapDialog.alert('数据库添加成功!', function(){
							window.location.reload();
				        });
					}
				});
			}
		}
	});
});