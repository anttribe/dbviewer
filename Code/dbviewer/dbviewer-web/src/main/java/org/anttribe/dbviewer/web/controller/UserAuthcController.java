package org.anttribe.dbviewer.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anttribe.dbviewer.web.constants.ResultCode;
import org.anttribe.dbviewer.web.vo.Result;
import org.anttribe.dbviewer.web.vo.authc.LoginParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户鉴权处理
 * 
 * @author zhaoyong
 * @date 2021-01-08
 */
@Controller
public class UserAuthcController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest requst, HttpServletResponse response, ModelAndView mv) {
		mv.setViewName(Views.LOGIN);
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> doLogin(HttpServletRequest requst, HttpServletResponse response, LoginParam loginParam) {
		Result<String> r = new Result<String>(ResultCode.RESULT_SUCCESS);
		// 用户登录
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(loginParam.getUsername());
		token.setPassword(loginParam.getPassword().toCharArray());
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			// 根据不同异常类型返回不同的resultCode
			r.setResultCode(ResultCode.Authc.RESULT_UNKNOWN_ACCOUNT);
		} catch (IncorrectCredentialsException e) {
			r.setResultCode(ResultCode.Authc.RESULT_INCORRECT_CREDENTIALS);
		} catch (DisabledAccountException e) {
			r.setResultCode(ResultCode.Authc.RESULT_DISABLED_ACCOUNT);
		}
		return r;
	}

	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest requst, HttpServletResponse response, ModelAndView mv) {
		mv.setViewName(Views.REGISTER);
		return mv;
	}

	class Views {

		public static final String LOGIN = "login";

		public static final String REGISTER = "register";

	}

}