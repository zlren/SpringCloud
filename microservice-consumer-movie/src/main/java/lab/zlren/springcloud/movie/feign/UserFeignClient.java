package lab.zlren.springcloud.movie.feign;

import lab.zlren.springcloud.movie.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * FeignClient 参数里面会被解析成eureka中的virtual-name 同时背后会有ribbon做负载均衡
 * 回退方法是一个实现了此接口的类
 * fallback和fallbackFactory属性二者配一个就可以，其中后者里面的信息更加丰富，包含了调用回退方法的异常信息
 * 如果两者都配的话，前者生效
 *
 * @author zlren
 * @date 2017-11-09
 */
@FeignClient(name = "microservice-provider-user",
        // fallback = UserFeignFallback.class,
        fallbackFactory = UserFeignFallbackFactory.class)
@Qualifier
public interface UserFeignClient {

    /**
     * 根据id查找用户信息
     * 注意两点 1是必须RequestMapping而不能GetMapping 2是@PathVariable("id")里面的id不能省略
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    /**
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    User postUser(@RequestBody User user);
}
