package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Miraclo Wei on 2020/12/3 17:36
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient  //重要
public class PaymentMain8001
{
    public static void main(String[] args) {    //mainbo
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
