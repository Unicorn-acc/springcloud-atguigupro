package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Miraclo Wei on 2020/12/4 21:11
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer   // 表示它是服务注册中心
public class EurekaServerMain7001 {

    public static void main(String[] args){
        SpringApplication.run(EurekaServerMain7001.class, args);
    }
}
/* 自己创建的错误:找不到主类  在edit configuration中更换主类
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer //代表我7001就是Eureka的服务注册中心，由我管理配置登记注册
public class EurekaServerMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7001.class,args);
    }
*/