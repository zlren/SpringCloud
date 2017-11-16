package lab.zlren.springcloud.movie.config.feign;

import feign.Contract;
import lab.zlren.springcloud.movie.config.ExcludeFromApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个配置文被排除了，否则对于所有的FeignClient都生效了。。
 *
 * @author zlren
 * @date 2017-11-08
 */
@Configuration
@ExcludeFromApplication
public class FeignConfig {

    /**
     * 使用了feign的自带契约，这样可以使用它自己的注解，比如RequestLine
     *
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
