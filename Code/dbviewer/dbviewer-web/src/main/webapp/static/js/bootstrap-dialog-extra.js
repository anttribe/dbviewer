(function(root, factory){
	"use strict";
	
	factory(root.jQuery);
}(this, function($){
	BootstrapDialog.DEFAULT_TEXTS = {};
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DEFAULT] = '提示';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_INFO] = '提示';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_PRIMARY] = '提示';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_SUCCESS] = '成功';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_WARNING] = '警告';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DANGER] = '危险';
    BootstrapDialog.DEFAULT_TEXTS['OK'] = '确定';
    BootstrapDialog.DEFAULT_TEXTS['CANCEL'] = '取消';
    BootstrapDialog.DEFAULT_TEXTS['CONFIRM'] = '确认';
}));