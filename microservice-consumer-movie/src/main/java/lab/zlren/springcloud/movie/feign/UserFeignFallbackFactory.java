package lab.zlren.springcloud.movie.feign;

import feign.hystrix.FallbackFactory;
import lab.zlren.springcloud.movie.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zlren
 * @date 2017-11-09
 */
@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                log.info("导致的原因是：{}", throwable.getMessage());
                return new User().setUsername("UserFeignFallbackFactory").setAge(12).setId(999L);
            }

            @Override
            public User postUser(User user) {
                return null;
            }
        };
    }
}
