package com.store.api.session;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.store.api.common.AjaxObject;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			response.setContentType("text/html;charset=UTF-8");
			// 没有声明需要权限
			StringBuffer sbUrl = new StringBuffer();
			Enumeration<String> en = request.getParameterNames();// 请求参数-值
			sbUrl.append(request.getRequestURI().toString());
			for (int i = 0; en.hasMoreElements(); i++) {
				String arg = en.nextElement().toString();
				if (i == 0) {
					sbUrl.append("?");
				} else {
					sbUrl.append("&");
				}
				sbUrl.append(arg + "=" + request.getParameterValues(arg)[0]);
			}

			LOG.error("{}", sbUrl.toString());
			Object user = request.getSession().getAttribute("user");
			if (null == user) {
				if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {
					AjaxObject ajaxObject=AjaxObject.newError("会话超时，请重新登录");
					response.getWriter().write(ajaxObject.toString());
					return false;
				} else {
					if (!request.getRequestURI().contains("login")) {
						RequestDispatcher rd = request.getRequestDispatcher("/login");
						rd.forward(request, response);
						return false;
					}
					return true;
				}
			} else
				return true;

		} else
			return true;
	}

}
