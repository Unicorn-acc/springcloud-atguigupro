package springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springcloud.domain.CommonResult;
import springcloud.service.StorageService;

/**
 * Created by Miraclo Wei on 2020/12/27 12:56
 */
@RestController
public class StorageController {


    @Autowired
    private StorageService storageService;

    @PostMapping("/storage/decrease")///storage/decrease
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200, "成功减扣库存---");
    }

}
