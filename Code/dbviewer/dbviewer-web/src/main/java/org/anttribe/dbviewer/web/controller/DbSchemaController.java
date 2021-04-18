package org.anttribe.dbviewer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anttribe.dbviewer.web.constants.ResultCode;
import org.anttribe.dbviewer.web.service.IDbSchemaService;
import org.anttribe.dbviewer.web.vo.Result;
import org.anttribe.dbviewer.web.vo.dbSchema.DbSchemaListParam;
import org.anttribe.dbviewer.web.vo.dbSchema.DbSchemaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaoyong
 * @date 2020-12-07
 */
@Controller
@RequestMapping("/dbSchema")
public class DbSchemaController {

	@Autowired
	private IDbSchemaService dbSchemaService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<DbSchemaVo>> doList(HttpServletRequest request, HttpServletResponse response,
			DbSchemaListParam dbSchemaListParam) {
		Result<List<DbSchemaVo>> r = new Result<List<DbSchemaVo>>(ResultCode.RESULT_SUCCESS);
		// TODO: 参数验证

		List<DbSchemaVo> dbSchemas = dbSchemaService.list(dbSchemaListParam);
		r.setResultBody(dbSchemas);
		return r;
	}

}