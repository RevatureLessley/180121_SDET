package com.trms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {
	private final static Logger logger = Logger.getLogger(UserFilter.class);
	
	public void destroy() {
		logger.info("destory() : UserFilter destroyed");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession(false);
		
		// TODO ask Ryan why my filter isn't working
		logger.info("Session is: " + session);
		if(session == null) {
			logger.info("doFilter() : session does not exist.  So back to the homepage.");
			/*
			RequestDispatcher rd = req.getRequestDispatcher("../index.html");
			rd.forward(req, res);
			*/
			/*res.sendRedirect("index.html");
		} else {
			chain.doFilter(req, res);
		}*/
		
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("init() : UserFilter initialized");
	}

}
