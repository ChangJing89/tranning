package com.example.cloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import brave.Tracer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @description: Zuul日志过滤器
 * filterType：返回过滤器类型，有 pre、route、post、error 等几种取值
 * filterOrder：返回一个 int值指定过滤器执行顺序，不同过滤器允许返回相同的数字
 * shouldFilter：true 表示过滤器执行、false表示不执行
 * run：过滤器具体逻辑，下面代码打印了请求方法和URL
 */
@Slf4j
@Component
public class LogFilter extends ZuulFilter {

  @Autowired
  private Tracer tracer;


  /**
   * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为 pre, 代表会在请求被路由之前执行。路由类型有下面几种：
   * - pre: 可以在请求被路由之前调用。
   * - routing: 在路由请求时被调用。
   * - post: 在 routing 和 error 过滤器之后被调用。
   * - error: 处理请求时发生错误时被调用。
   * @return
   */
  @Override
  public String filterType() {
    return FilterConstants.POST_TYPE;
  }

  /**
   * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行，数值越小，优先级越高。
   *
   * @return
   */
  @Override
  public int filterOrder() {
    return 1;
  }

  /**
   * 判断该过滤器是否需要被执行。这里我们直接返回了true, 因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围。
   *
   * @return
   */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  /**
   * 过滤器的具体执行逻辑
   *
   * @return
   * @throws ZuulException
   */
  @Override
  public Object run() throws ZuulException {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    logger.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURL().toString()));
    logger.info("span:"+tracer.currentSpan());
    return null;
  }
}
