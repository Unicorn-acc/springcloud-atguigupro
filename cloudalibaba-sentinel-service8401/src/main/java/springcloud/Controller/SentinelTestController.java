package springcloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by Miraclo Wei on 2020/12/25 11:27
 */
@RestController
@Slf4j
public class SentinelTestController {

    @GetMapping("/testA")
    public String testA() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        }catch (Exception e){
            System.out.println(e.getMessage());
            //e.getMessage()并不能获取全部的错误信息,
            //需要用到e.printStackTrace()查看完整错误信息，但是这个方法是void 只能在控制台输出。
        }
        return "testA----";
    }

    @GetMapping("/testB")
    public String testB(){
        return "testB*****";
    }


    @GetMapping("/testD")//RT测试
    public String testD(){
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        log.info("testD测试RT");
        return "-----TestD";
    }

    @GetMapping("/testHotKey")
    //某个地址违背热点规则，用此处理,不设方法就errorpage两种一起配
    //参数索引 0 单机阈值 1 统计窗口时长1 |||  Qps等于1  1秒允许带参数0的请求发送一次
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHostKey")
    public String testHotkey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "Sucess --testHotKey";
    }
    //兜底方法
    public String deal_testHostKey(String p1, String p2, BlockException e){
        return "------deal_deal_testHostKey,o(╥﹏╥)o";//sentinel 系统默认提示：Sentinel by Sentinel(flow limiting)
    }
}
