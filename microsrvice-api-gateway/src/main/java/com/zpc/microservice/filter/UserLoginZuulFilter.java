package com.zpc.microservice.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 *  网关过滤器
 *  加入到Spring容器
 * @author
 */
@Component
public class UserLoginZuulFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		//该过滤器需要执行
		return true;
	}

	@Override
	public Object run() throws ZuulException {//编写业务逻辑
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)) {
			// 过滤该请求，不对其进行路由
			requestContext.setSendZuulResponse(false);
			// 设置响应状态码
			requestContext.setResponseStatusCode(401);
			requestContext.setResponseBody(" token is empty!!");
			return null;
		}
		return null;
	}

	@Override
	public String filterType() {
		//设置过滤器类型为：pre
		//filterType：返回字符串代表过滤器的类型
		//a)pre：请求在被路由之前执行
		//b)routing：在路由请求时调用
		//c)post：在routing和errror过滤器之后调用
		//d)error：处理请求时发生错误调用
		return "pre";
	}

	@Override
	public int filterOrder() {
		//设置执行顺序为0
		return 0;
	}

}
