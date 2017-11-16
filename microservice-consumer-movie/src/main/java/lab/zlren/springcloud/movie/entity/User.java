package lab.zlren.springcloud.movie.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author zlren
 * @date 2017-11-07
 */
@Data
@ToString
@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private Integer age;
}
