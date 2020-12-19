package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Miraclo Wei on 2020/12/9 17:20
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMainzk {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainzk.class,args);
    }
}
