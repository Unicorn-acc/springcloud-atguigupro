package springcloud.service;

/**
 * Created by Miraclo Wei on 2020/12/27 12:55
 */
public interface StorageService {

    void decrease(Long productId,Integer count);
}
