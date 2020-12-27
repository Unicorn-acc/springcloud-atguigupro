package springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Created by Miraclo Wei on 2020/12/27 13:07
 */

@Mapper
public interface AccountDao {

    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
