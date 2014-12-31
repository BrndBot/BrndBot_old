package com.brndbot.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** This filter needs to be put in front of every AJAX servlet so that
 *  it can't be used by someone who isn't logged in.
 */
public class AuthenticationFilter implements Filter {

	final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hReq = (HttpServletRequest) req;
		HttpServletResponse hResp = (HttpServletResponse) resp;
		logger.debug ("in doFilter");
		if (authenticationOK (hReq)) {
			chain.doFilter ((HttpServletRequest) req, hResp);
		} else {
			hResp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private boolean authenticationOK (HttpServletRequest hReq) {
		// TODO stub for testing
		return true;
	}

}
