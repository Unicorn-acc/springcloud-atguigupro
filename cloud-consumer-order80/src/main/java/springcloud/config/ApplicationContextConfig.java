package springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Miraclo Wei on 2020/12/4 1:25
 */
@Configuration
public class ApplicationContextConfig {

    @Bean   //applicationContext.xml<bean id="" class="">
    //LoadBalanced   //赋予负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
