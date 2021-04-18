package org.anttribe.dbviewer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anttribe.dbviewer.web.constants.ResultCode;
import org.anttribe.dbviewer.web.service.IDbTableService;
import org.anttribe.dbviewer.web.vo.Result;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableGetParam;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableListParam;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaoyong
 * @date 2020-12-07
 */
@Controller
@RequestMapping("/dbTable")
public class DbTableController {

	@Autowired
	private IDbTableService dbTableService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<DbTableVo>> doList(HttpServletRequest request, HttpServletResponse response,
			DbTableListParam dbTableListParam) {
		Result<List<DbTableVo>> r = new Result<List<DbTableVo>>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证

		List<DbTableVo> dbTables = dbTableService.list(dbTableListParam);
		r.setResultBody(dbTables);
		return r;
	}

	@RequestMapping(value = "/{tableName}", method = RequestMethod.POST)
	@ResponseBody
	public Result<DbTableVo> doGet(HttpServletRequest request, HttpServletResponse response,
			DbTableGetParam dbTableGetParam, @PathVariable String tableName) {
		Result<DbTableVo> r = new Result<DbTableVo>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证

		DbTableVo dbTable = dbTableService.get(tableName, dbTableGetParam);
		r.setResultBody(dbTable);
		return r;
	}

}