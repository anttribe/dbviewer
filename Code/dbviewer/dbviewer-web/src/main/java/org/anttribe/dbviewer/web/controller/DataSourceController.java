package org.anttribe.dbviewer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anttribe.dbviewer.web.constants.ResultCode;
import org.anttribe.dbviewer.web.service.IDataSourceService;
import org.anttribe.dbviewer.web.vo.Result;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceAddParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceEditParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceListParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @date 2020-12-07
 */
@Controller
@RequestMapping("/dataSource")
public class DataSourceController {

	@Autowired
	private IDataSourceService dataSourceService;

	@RequestMapping({ "", "/" })
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		mv.setViewName(Views.INDEX);
		return mv;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<DataSourceVo>> doList(HttpServletRequest request, HttpServletResponse response,
			DataSourceListParam dataSourceListParam) {
		Result<List<DataSourceVo>> r = new Result<List<DataSourceVo>>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证
		
		List<DataSourceVo> datas = dataSourceService.list(dataSourceListParam);
		r.setResultBody(datas);
		return r;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> doAdd(HttpServletRequest request, HttpServletResponse response,
			DataSourceAddParam dataSourceAddParam) {
		Result<String> r = new Result<String>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证
		
		dataSourceService.add(dataSourceAddParam);
		return r;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> doEdit(HttpServletRequest request, HttpServletResponse response,
			DataSourceEditParam dataSourceEditParam) {
		Result<String> r = new Result<String>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证
		
		dataSourceService.edit(dataSourceEditParam);
		return r;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<DataSourceVo> doGet(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String id) {
		Result<DataSourceVo> r = new Result<DataSourceVo>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证
		
		DataSourceVo dataSource = dataSourceService.getById(id);
		r.setResultBody(dataSource);
		return r;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public Result<DataSourceVo> doRemove(HttpServletRequest request, HttpServletResponse response, String id) {
		Result<DataSourceVo> r = new Result<DataSourceVo>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证
		
		dataSourceService.remove(id);
		return r;
	}

	class Views {

		public static final String INDEX = "dataSource/index";

		public static final String LIST = "dataSource/list";

		public static final String VIEW = "dataSource/view";

	}

}