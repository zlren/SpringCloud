package lab.zlren.springcloud.user.repository;

import lab.zlren.springcloud.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zlren
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}