package springcloud.service;

import java.math.BigDecimal;

/**
 * Created by Miraclo Wei on 2020/12/27 13:09
 */
public interface AccountService {

    void decrease(Long userId, BigDecimal money);
}
