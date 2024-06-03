package com.realeaste.MockProject_052024.user;

import com.realeaste.MockProject_052024.entities.Role;
import com.realeaste.MockProject_052024.entities.User;
import com.realeaste.MockProject_052024.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userLongpt = new User("longpt35@gmail.com","longdn123","Long","Phi Thanh");
        userLongpt.addRole(roleAdmin);

        User saveUser = repo.save(userLongpt);

        assertThat(saveUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetUserByEmail(){
        String email = "longpt35@gmail.com";
        User user = repo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }
}
