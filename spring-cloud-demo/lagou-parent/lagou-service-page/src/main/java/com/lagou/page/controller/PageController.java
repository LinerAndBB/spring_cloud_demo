package com.lagou.page.controller;

import com.lagou.page.feign.ProductFeign;
import com.lagou.pojo.Products;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("page")
public class PageController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductFeign productFeign;

    @GetMapping("/getData/{id}")
    public Products findDataById(@PathVariable Integer id){
        //获取lagou-service-product实例集合，取出第一个，获取信息，拼接url
//        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-product");
//        ServiceInstance instance = instances.get(0);
//        String host = instance.getHost();
//        int port = instance.getPort();
//        //获取自定义元数据
//        Map<String, String> metadata = instance.getMetadata();
//        String url = "http://" + host + ":" + port + "/product/query/" + id;
//        Products products = restTemplate.getForObject(url, Products.class);

        Products products = productFeign.query(id);
        System.out.println("从lagou-service-product获得product对象:"+products);
        return products;
    }

    @RequestMapping("getPort")
    public String getProductServerPort(){
//        String url = "http://lagou-service-product/server/query";
//        return restTemplate.getForObject(url, String.class);
        return productFeign.findServerPort();
    }

    @HystrixCommand(
            //线程池标识，不唯一就会共用线程池
            threadPoolKey = "getProductPort2",
            // 线程池细节属性
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"), //线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20")  //等待队列长度
            },
            // commandProperties 熔断的一些细节属性配置
            commandProperties = {
                    /**
                      * 8秒钟内，请求次数达到2个，并且失败率在50%以上，就跳闸
                      * 跳闸后活动窗口设置为3s
                     **/
//                    //统计时间窗设置
//                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "8000"),
//                    //统计时间窗的最小请求数
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
//                    //统计窗口内错误请求阈值的设置
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
//                    //自我修复的窗口时间
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
                    //超时时间设置
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "2000")
            },
            fallbackMethod = "myFallBack"  // 回退方法
    )
    @RequestMapping("getPort2")
    public String getProductServerPort2(){
        String url = "http://lagou-service-product/server/query";
        return restTemplate.getForObject(url, String.class);
    }

    /*
    * 定义回退方法，返回预设默认值
    * 注意：该方法形参和返回值与原始方法保持一致
     */
    public String myFallBack(){
        return "-1"; // 兜底数据
    }
}
