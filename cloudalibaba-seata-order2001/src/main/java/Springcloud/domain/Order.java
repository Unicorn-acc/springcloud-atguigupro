package Springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 实体类entity domain vo (value object) 前端（viewObject）
 * Created by Miraclo Wei on 2020/12/27 11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;  //订单状态：0创建中，1创建完成
}
