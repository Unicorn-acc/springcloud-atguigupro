package springcloud.filter;

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
 * 自定义过滤器，引用全局过滤器GlobalFilter接口
 * 常用 模板实现GlobalFilter，Ordered接口
 * Created by Miraclo Wei on 2020/12/18 11:15
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("**********come in MyLogGateWayFilter:"+ new Date());
        //http://localhost:9527/payment/lb?uname=23过滤链中得到uname值
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null){
            log.info("*********用户名为null，非法用户，o(╥﹏╥)o。");
            //给一个回应
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);//不被接受
            return exchange.getResponse().setComplete();
        }
        return chain.filter((exchange));//去下一个过滤链进行把关
    }

    @Override
    public int getOrder() { //加载过滤器顺序 数值越小优先级越高
        return 0;
    }
}
