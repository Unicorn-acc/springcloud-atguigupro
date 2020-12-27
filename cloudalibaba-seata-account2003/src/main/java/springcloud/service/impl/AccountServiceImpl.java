package springcloud.service.impl;

import org.springframework.stereotype.Service;
import springcloud.dao.AccountDao;
import springcloud.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Miraclo Wei on 2020/12/27 13:09
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {

        accountDao.decrease(userId, money);
    }
}
