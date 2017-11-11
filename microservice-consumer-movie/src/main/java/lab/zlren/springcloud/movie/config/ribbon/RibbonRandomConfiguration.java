package lab.zlren.springcloud.movie.config.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import lab.zlren.springcloud.movie.config.ExcludeFromApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon复杂均衡策略
 *
 * @author zlren
 * @date 2017-11-08
 */
@Configuration
@ExcludeFromApplication
public class RibbonRandomConfiguration {

    /**
     * 随机策略
     *
     * @return 随机
     */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
