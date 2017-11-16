package lab.zlren.springcloud.movie.feign;

import lab.zlren.springcloud.movie.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 这是UserFeignClient的回退类
 *
 * @author zlren
 * @date 2017-11-09
 */
@Slf4j
@Component
public class UserFeignFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        log.info("进入feign的回退类");
        return new User().setId(id).setAge(28).setUsername("default-username-feign-fallback");
    }

    @Override
    public User postUser(User user) {
        return null;
    }
}
