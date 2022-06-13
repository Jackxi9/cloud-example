package com.xiaoxi.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * ServerWebExchange:类似于Servlet，拿前端发来的请求，看里面的请求头，请求参数符不符合设定预期，来做过滤。
 * GatewayFilterChain:filter链，如果请求在这个过滤器里没问题，就放给下一个过滤器处理。
 */
@Component
@Slf4j
public class GatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("****************GatewayFilter"+ new Date());
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if (name == null){
            log.info("未输入name");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);//返回一个不被接受的状态码
            return exchange.getResponse().setComplete();
        }
        log.info("成功通过");
        return chain.filter(exchange);//通过此过滤器，发往下一个过滤器
    }

    /**
     * 过滤器的先后顺序，数字越小越靠前
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
