package com.example.citizen.service;

import com.example.citizen.model.User;
import com.example.citizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    BCryptPasswordEncoder bCryptEncoder;
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptEncoder = new BCryptPasswordEncoder(12);
    }

    public boolean findUser(Long phone, String password) {
        User user = userRepository.findByPhone(phone).orElseThrow(() -> null);
        if (user != null) {
            String bdPass = user.getPassword();
            String currentPass = bCryptEncoder.encode(password);
            return bCryptEncoder.matches(bdPass, currentPass);
        }
        return false;
    }
    public void save (User user) {
         String hash = bCryptEncoder.encode(user.getPassword());
         user.setPassword(hash);
         userRepository.save(user);
    }

}
