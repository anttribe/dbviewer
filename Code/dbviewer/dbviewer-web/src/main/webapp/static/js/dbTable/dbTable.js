var dbTable = {
	route : { // 路由统一定义
		doList : {type: 'POST', url: contextPath + 'dbTable/list'}, //ajax获取列表数据
        doGet : {type: 'POST', url: contextPath + 'dbTable'}  //ajax获取列表数据
	},
	doList: function(dataSourceId, dbSchema, params, callback) { // ajax获取列表数据
		var $this = this;
		var route = dbTable.route.doList;
		if(route && route['url']){
			$.ajax({
				type : route['type'] || 'POST',
        		url: route['url'],
        		data: $.extend(params, {dataSourceId: dataSourceId, dbSchema: dbSchema}) || {},
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
	doGet: function(dataSourceId, dbSchema, dbTableName, callback) {
		var $this = this;
		var route = dbTable.route.doGet;
		if(route && route['url']){
			$.ajax({
				type : route['type'] || 'POST',
        		url: route['url'] + '/' + dbTableName,
        		data: {dataSourceId: dataSourceId, dbSchema: dbSchema},
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
	dbTableView: function($dbTable){
		if($dbTable){
			return new dbTableViewer($dbTable);
		}
	}
};

var dbTableViewer = function($dbTable){
	var $container = $('<div class="widget-box transparent">')
	                           .append('<div class="widget-header"><h4 class="widget-title lighter">' + ($dbTable['tableName'] + '(' + ($dbTable['comment'] || '') + ')') + '</h4><div class="widget-toolbar no-border"></div></div>')
	                           .append('<div class="widget-body"><div class="widget-main padding-12 no-padding-left no-padding-right"></div></div>');
	this.$dbTable = $dbTable;
	this.$container = $container;
	this.render();
	
	return $container;
}
dbTableViewer.prototype = {
	render: function(){
		this.createTabs();
	},
	createTabs: function(){
		this.$dbTableViewerTabs = $('<ul class="nav nav-tabs">');
		$('.widget-toolbar', this.$container).append(this.$dbTableViewerTabs);
		
		this.$dbTableViewerTabContent = $('<div class="tab-content">');
		$('.widget-main', this.$container).append(this.$dbTableViewerTabContent);
		
		// 表结构
		this.createDbTableStructureTab();
		// 表注释
		this.createDbTableCommentTab();
		
		// 显示第一个tab
		$('a[data-toggle="tab"]:first', this.$dbTableViewerTabs).tab('show');
		$('div[role="tabpanel"]:first', this.$dbTableViewerTabContent).addClass('active in');
	},
	createDbTableStructureTab: function(){
		var $dbTableStructureTabKey = this.$dbTable['dbSchema'] + '_' + this.$dbTable['tableName'] + '_structure';
		var $dbTableStructureTabHtml = '<li class="">'
                                     + '<a data-toggle="tab" role="tab" href="#tab-content-' + $dbTableStructureTabKey + '" class="">列</a>'
                                     + '</li>';
		var $dbTableStructureTab = $($dbTableStructureTabHtml);
		this.$dbTableViewerTabs.append($dbTableStructureTab);

        var $dbTableStructureTabContentHtml = '<div id="tab-content-' + $dbTableStructureTabKey + '" role="tabpanel" class="tab-pane fade"></div>';
        var $dbTableStructureTabContent = $($dbTableStructureTabContentHtml);
        this.$dbTableViewerTabContent.append($dbTableStructureTabContent);
        
        // 表结构
        var $dbTableStructureHtml = '<table class="table table-striped table-bordered table-hover">'
                                  + '<thead><tr>' + '<th class="">列名</th>' + '<th class="">注释</th>' + '<th class="">类型</th>' + '<th class="">长度</th>' + '<th class="">允许空值(NULL)</th>' + '<th class="">主键</th>' + '</tr></thead>'
                                  + '<tbody>'
                                  + function($dbTable, $columns){
        	                            if($columns && $columns.length>0){
        	                            	var $columnsHtml = '';
        	                            	for(var i=0; i<$columns.length; i++){
        	                            		var $column = $columns[i];
        	                            		if(!$column || !$column['columnName']){
        	                            			continue;
        	                            		}
        	                            	    $columnsHtml += '<tr>'
        	                            	    	          + '<td>' + $column['columnName'] + '</td>'
        	                            	    	          + '<td>' + ($column['comment'] || '') + '</td>'
        	                            	    	          + '<td>' + ($column['jdbcType'] || '') + '</td>'
        	                            	    	          + '<td>' + ($column['size'] || '') + '</td>'
        	                            	    	          + '<td><i class="ace-icon fa ' + ($column['nullable'] ? 'fa-check-square-o green' : 'fa-close red2') + '"></i></td>'
        	                            	    	          + '<td>' + function($dbTable, $column){
        	                            	    	                         var $html = '';
        	                            	    	                         var $primaryKey = $dbTable.primaryKey;
        	                            	    	                         if($primaryKey && $primaryKey.columns && $primaryKey.columns.length>0){
        	                            	    	                        	 for(var i=0; i<$primaryKey.columns.length; i++){
        	                            	    	                        		 if($column['columnName'] == $primaryKey.columns[i]){
        	                            	    	                        			 $html = '<i class="ace-icon fa fa-key orange"></i>' + (i + 1);
        	                            	    	                        			 break;
        	                            	    	                        		 }
        	                            	    	                        	 }
        	                            	    	                         }
        	                            	    	                         return $html;
        	                            	                             }($dbTable, $column) + '</td>'
        	                            	    	          + '</tr>';
        	                                }
        	                            	return $columnsHtml;
        	                            }
        	                            return '';
        	                        }(this.$dbTable, this.$dbTable['columns'])
        	                      + '</tbody>'
        	                      + '</table>';
        $dbTableStructureTabContent.append($dbTableStructureHtml);
	},
	createDbTableCommentTab: function(){
		var $dbTableCommentTabKey = this.$dbTable['dbSchema'] + '_' + this.$dbTable['tableName'] + '_comment';
		var $dbTableCommentTabHtml = '<li class="">'
                                   + '<a data-toggle="tab" role="tab" href="#tab-content-' + $dbTableCommentTabKey + '" class="">注释</a>'
                                   + '</li>';
		var $dbTableCommentTab = $($dbTableCommentTabHtml);
		this.$dbTableViewerTabs.append($dbTableCommentTab);

        var $dbTableCommentTabContentHtml = '<div id="tab-content-' + $dbTableCommentTabKey + '" role="tabpanel" class="tab-pane fade"></div>';
        var $dbTableCommentTabContent = $($dbTableCommentTabContentHtml);
        this.$dbTableViewerTabContent.append($dbTableCommentTabContent);
        
        // 表结构
        var $dbTableCommentHtml = '<div class="">' + (this.$dbTable['comment'] || '') + '</div>';
        $dbTableCommentTabContent.append($dbTableCommentHtml);
	}
};