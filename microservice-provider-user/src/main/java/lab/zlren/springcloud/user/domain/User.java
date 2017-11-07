package lab.zlren.springcloud.user.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zlren
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private Integer age;
}