package springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Miraclo Wei on 2020/12/3 19:04
 */
@Data
@AllArgsConstructor     //全参数
@NoArgsConstructor      //空参数
public class CommonResult<T> {  //<T>通用的，payment就返回payment order就返回order
    //例如404 not found
    private Integer code;
    private String message;
    private T   data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }


}
