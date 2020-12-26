package springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手动版动态刷新
 * 命令行：curl -X POST "http://localhost:3355/actuator/refresh"
 * C:\Users\..>curl -X POST "http://localhost:3355/actuator/refresh"
 * ["config.client.version","config.info"]
 * Created by Miraclo Wei on 2020/12/19 14:42
 */
@RestController
@RefreshScope   //刷新标签 实现刷新功能 需要运维人员发送post请求刷新3355
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverport;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "serverPort:"+serverport+"configInfo"+configInfo;
    }
}
