package crudsample.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Cepro on 15.07.2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    Page<User> findAll(Pageable pageable);

    Page<User> findByNameIgnoreCaseContaining(String name, Pageable pageable);

    List<User> findByNameIgnoreCaseContaining(String name);

    List<User> findById(int id);
}
