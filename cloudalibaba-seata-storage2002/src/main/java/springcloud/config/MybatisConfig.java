package springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Miraclo Wei on 2020/12/27 12:57
 */

@Configuration
@MapperScan({"com.dkf.springcloud.alibaba.dao"})
public class MybatisConfig {
}
