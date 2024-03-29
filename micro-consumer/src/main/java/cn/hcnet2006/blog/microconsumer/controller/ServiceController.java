package cn.hcnet2006.blog.microconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务
     * @return
     */
    @RequestMapping("/services")
    public Object services(){
        return discoveryClient.getInstances("hcnet-website-1");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     * @return
     */
    @RequestMapping("/discover")
    public Object discovery(){
        return loadBalancerClient.choose("hcnet-website-1").getUri().toString();
    }
}
