package Springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Miraclo Wei on 2020/12/27 12:27
 */
@Configuration
@MapperScan({"Springcloud.dao"})
public class MyBatisConfig {
}
