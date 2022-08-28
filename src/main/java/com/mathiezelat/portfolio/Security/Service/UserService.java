package com.mathiezelat.portfolio.Security.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathiezelat.portfolio.Security.Entity.User;
import com.mathiezelat.portfolio.Security.Repository.IUserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public Optional<User> getByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return iUserRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return iUserRepository.existsByEmail(email);
    }

    public void save(User user) {
        iUserRepository.save(user);
    }
}
