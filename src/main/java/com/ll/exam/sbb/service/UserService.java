package com.ll.exam.sbb.service;

import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean existsByUsername(String username) {
        return siteUserRepository.existsByUsername(username);
    }

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);

        user.setPassword(passwordEncoder.encode(password));

        siteUserRepository.save(user);
        return user;
    }


    public SiteUser getUser(String name) {
        return siteUserRepository.findByUsername(name).orElse(null);
    }
}
