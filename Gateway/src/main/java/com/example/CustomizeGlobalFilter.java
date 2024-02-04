package com.example;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Order(-1) //过滤器的优先级，数字越小，优先级越高 || 也可以实现Ordered接口，重写getOrder方法
@Component
public class CustomizeGlobalFilter implements GlobalFilter, Ordered {

    //测试通过的地址：http://localhost:10011/users/login/test3/test4?authorization=pass
    //测试失败的地址：http://localhost:10011/users/login/test3/test4?authorization=123

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        //获取参数中的authorization参数值
        MultiValueMap<String, String> params = request.getQueryParams();
        String authorization = params.getFirst("authorization");
        //判断key为authorization的参数值是否为”pass“
        if("pass".equals(authorization)) {
            //如果有，放行
            //chain是过滤器链，如果有多个过滤器，就会有多个chain
            //chain.filter(exchange)是放行的意思 将请求继续传递下去
            return chain.filter(exchange);
        }
        //如果没有，返回错误信息
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//设置响应状态码
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1; //数字越小，优先级越高  等同于@Order(-1)
    }
}
