package lab.zlren.springcloud.movie.feign;

import feign.Param;
import feign.RequestLine;
import lab.zlren.springcloud.movie.config.feign.FeignConfig;
import lab.zlren.springcloud.movie.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 指定了配置文件的FeignClient
 *
 * @author zlren
 */
@FeignClient(name = "xxx", configuration = FeignConfig.class)
public interface UserFeignClientWithConfig {

    @RequestLine("GET /user/{id}")
    User getUserById(@Param("id") Long id);
}
