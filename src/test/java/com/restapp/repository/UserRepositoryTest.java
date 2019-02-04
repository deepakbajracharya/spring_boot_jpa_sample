package com.restapp.repository;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.restapp.models.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUserAdd() {
	User u = new User();
	u.setUsername("test-user");
	u.setFullname("Test User");

	User u1 = userRepository.save(u);

	Optional<User> optUser = userRepository.findByUsername("test-user");
	User u2 = optUser.get();

	Assert.assertEquals(u.getUsername(), u2.getUsername());
	Assert.assertEquals(u1.getId(), u2.getId());
    }

}
