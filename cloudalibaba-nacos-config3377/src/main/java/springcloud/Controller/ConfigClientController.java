package springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Miraclo Wei on 2020/12/22 22:39
 */
@RestController
@RefreshScope  //支持Nacos的动态刷新功能
public class ConfigClientController {

    //Could not resolve placeholder 'config.info' in value "${config.info}"
    //nacos配置文件名错误 yml-> yaml
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("configclient/getconfiginfo")
    public String getConfigInfo(){
        return configInfo;
    }
}