package com.nysit.stay.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nysit.stay.domain.User;

@Component
public class InterceptorConfig implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null){
			return true;
		}
		String url=request.getRequestURI();
		if(url.startsWith("/static/")){
			return true;
		}
		if("/user/login".equals(url)){
			return true;
		}
		if("/ui/index".equals(url)){
			return true;
		}
		request.getRequestDispatcher("/ui/index").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
