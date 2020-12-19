package myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Miraclo Wei on 2020/12/11 8:24
 */
//如何替换负载均衡规则？
//不要放在能被spirngbootscan扫描到的包下（或者排除）
@Configuration
public class Myselfrule {

    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机
    }
}
