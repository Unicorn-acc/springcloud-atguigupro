package springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import java.util.List;

/**
 * Created by Miraclo Wei on 2020/12/11 10:11
 */
public interface LoadBalancer {
    //收集集群总数 有多少台能提供服务的机器放到list中
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
