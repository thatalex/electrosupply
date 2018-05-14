package by.alex.web.site.repository;

import by.alex.web.site.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	 User findByEmail(String email);

    User getById(Integer id);

    @Transactional
    void deleteById(Integer id);
}
