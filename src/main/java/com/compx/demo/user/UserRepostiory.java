package com.compx.demo.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepostiory extends CrudRepository<User,Integer> {
    
}
