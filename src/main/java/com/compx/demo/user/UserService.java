package com.compx.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired private UserRepostiory repostiory;

    public List<User> listAll(){
        return (List<User>) repostiory.findAll();
    }

    public void save(User user) {
        if(user.getId()!=null){
            User userOptional = repostiory.findById(user.getId()).get();
            
            repostiory.save(user);
        }else{
        repostiory.save(user);
        }
    }

    public User get(Integer id) throws UserNotFoundException{
        Optional<User> optionalUser = repostiory.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new UserNotFoundException("Couldn't find any users with ID : "+id);
    }

    public void delete(User user) {
        repostiory.delete(user);
    }

    
}
