package lab.zlren.springcloud.movie.config.ribbon;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 这个类会被扫描，所以对于microservice-provider-user的配置就会生效
 *
 * @author zlren
 * @date 2017-11-08
 */
@Configuration
@RibbonClient(name = "microservice-provider-user", configuration = RibbonRandomConfiguration.class)
public class RibbonUserConfiguration {

}
