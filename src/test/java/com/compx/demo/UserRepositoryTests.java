package com.compx.demo;

import java.util.Optional;

import com.compx.demo.user.User;
import com.compx.demo.user.UserRepostiory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTests {
	@Autowired
	private UserRepostiory repository;

	@Test
	void addNewUser() {
		User user = new User("tes2@gmail.com", "password", "Test2", "Akdogan", true);
		User savedUser = repository.save(user);

		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	void testListAll() {
		Iterable<User> users = repository.findAll();
		Assertions.assertThat(users).hasSizeGreaterThan(0);

		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	void testUpdate() {
		Optional<User> optionalUser = repository.findById(1);
		User user = optionalUser.get();
		user.setPassword("newPassword");
		repository.save(user);
		User updatedUser = repository.findById(1).get();
		Assertions.assertThat(updatedUser.getPassword()).isEqualTo("newPassword");
	}

	@Test
	void testGet() {
		Optional<User> optionalUser = repository.findById(4);
		Assertions.assertThat(optionalUser).isPresent();
		System.out.println(optionalUser.get());
	}

	@Test
	void testDelete() {
		repository.deleteById(4);
		Optional<User> optionalUser = repository.findById(4);
		Assertions.assertThat(optionalUser).isNotPresent();
	}
}
