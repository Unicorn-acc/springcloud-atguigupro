package springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 主实体Payment
 * Json封装体 CommonResult（与前端接口对应，传给前端的类）
 * Created by Miraclo Wei on 2020/12/3 18:49
 */
@Data
@AllArgsConstructor     //全参数
@NoArgsConstructor      //空参数
public class Payment implements Serializable {  //加上序列化，分布式部署可能用的到
    private Long id;
    private String serial;
}
