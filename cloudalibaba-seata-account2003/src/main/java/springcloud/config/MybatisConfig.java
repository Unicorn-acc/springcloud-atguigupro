package springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Miraclo Wei on 2020/12/27 13:11
 */
@Configuration
@MapperScan({"springcloud.dao"})
public class MybatisConfig {
}
