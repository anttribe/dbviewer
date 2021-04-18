package org.anttribe.dbviewer.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @date 2020-12-03
 */
@Controller
public class IndexController {

	@RequestMapping({ "", "/", "/index" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		mv.setViewName(Views.INDEX_VIEW);
		return mv;
	}

	class Views {

		public static final String INDEX_VIEW = "redirect:/dataSource";

	}

}