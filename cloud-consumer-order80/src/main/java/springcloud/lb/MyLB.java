package springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Miraclo Wei on 2020/12/11 10:16
 */
@Component
public class MyLB implements LoadBalancer {
    //与RoundRobinRule.java中的逻辑仿写
    private AtomicInteger atomicInteger = new AtomicInteger(0);//原子类

    public final int getAndIncrement(){
        int current;
        int next;
        //自旋锁
        do{
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;

        }while(!this.atomicInteger.compareAndSet(current,next));//CAS
        System.out.println("next:"+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances){
        //第几次访问%服务器集群总数量
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);//传入list里
    }

}
