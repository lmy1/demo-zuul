package com.cd.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ValidatorFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(ValidatorFilter.class);

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
		String username = request.getParameter("username");
		if (username == null) {
			log.error("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"code\":4,\"msg\":\"username is null\"}");
			return null;
		}

		if (username.length() < 8) {
			log.error("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"code\":4,\"msg\":\"username is so short\"}");
			return null;
		}
		log.info("access token ok");

		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
