package com.example.citizen.service;

import com.example.citizen.model.User;
import com.example.citizen.repository.UserRepository;
import com.example.citizen.utils.LoginData;
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

    public boolean findUser(LoginData loginData) {
        Long phone = loginData.getPhone();
        String password = loginData.getPassword();
        User user = userRepository.findUserByPhone(phone);
        if (user != null) {
            String bdPass = user.getPassword();
            return bCryptEncoder.matches(password, bdPass);
        }
        return false;
    }
    public void save (User user) {
         String hash = bCryptEncoder.encode(user.getPassword());
         user.setPassword(hash);
         userRepository.save(user);
    }

}
