package lab.zlren.springcloud.movie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lab.zlren.springcloud.movie.entity.User;
import lab.zlren.springcloud.movie.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zlren
 * @date 2017-11-07
 */
@RestController
@RequestMapping("movie")
@Slf4j
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public User findById(@PathVariable Long id) {

        ServiceInstance instance = loadBalancerClient.choose("microservice-provider-user");
        log.info("使用RestTemplate进行调用: {}, {}", instance.getHost(), instance.getPort());

        // 这里的RestTemplate使用了Ribbon客户端负载均衡，在请求服务提供者的时候就可以使用服务提供者的application-name
        // 实际上是virtual-name
        return this.restTemplate.getForObject("http://microservice-provider-user/user/" + id, User.class);
    }

    /**
     * findById的回退方法
     * findById方法中调用了用户模块的方法，当用户模块不可用的时候就会进入回退方法
     * 这个方法要和原方法有同样的参数列表和返回类型
     *
     * @param id
     * @return
     */
    public User findByIdFallback(Long id) {
        return new User().setId(id).setAge(28).setUsername("default-username");
    }

    @Autowired
    @Qualifier
    private UserFeignClient userFeignClient;

    @GetMapping("/user/feign/{id}")
    public User findByIdWithFeign(@PathVariable Long id) {
        log.info("使用feign来进行调用");
        return userFeignClient.findById(id);
    }

    /**
     * 这里的方法是get的，背后使用了feign的post形式调用user服务
     *
     * @param user
     * @return
     */
    @GetMapping("/user/feign_post")
    public User testPost(User user) {
        // http://localhost:7901/movie/user/feign_post?username=zlren&age=24&id=999
        log.info("参数信息 {}", user);
        return userFeignClient.postUser(user);
    }
}
