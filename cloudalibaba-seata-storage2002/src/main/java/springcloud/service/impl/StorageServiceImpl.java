package springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springcloud.Dao.StorageDao;
import springcloud.service.StorageService;

/**
 * Created by Miraclo Wei on 2020/12/27 12:55
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        try {
            Thread.sleep(5000);
            storageDao.decrease(productId,count);
        }catch (Exception e){
            e.printStackTrace();
        }
        //storageDao.decrease(productId,count);
    }
}
