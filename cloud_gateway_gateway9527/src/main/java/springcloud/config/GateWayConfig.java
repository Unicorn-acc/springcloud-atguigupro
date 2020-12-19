package springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Miraclo Wei on 2020/12/18 10:15
 */
@Configuration
public class GateWayConfig {
    @Bean       //路由
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        //相当于配置中的routes
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu1", //相当于id
                r -> r.path("/guonei")  //访问localhost:9527/guonei将会转发到下面uri路径
                        .uri("http://news.baidu.com/guonei")).build();

        routes.route("path_route_atguigu2", //相当于id
                r -> r.path("/guoji")  //访问localhost:9527/guonei将会转发到下面uri路径
                        .uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
