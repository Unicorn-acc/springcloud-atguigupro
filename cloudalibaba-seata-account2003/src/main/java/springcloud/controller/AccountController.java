package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springcloud.domain.CommonResult;
import springcloud.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Miraclo Wei on 2020/12/27 13:09
 */
@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountService.decrease(userId, money);
        return new CommonResult(200, "账户成功扣除余额！");
    }
}
