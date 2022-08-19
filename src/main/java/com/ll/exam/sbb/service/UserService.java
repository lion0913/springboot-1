package com.ll.exam.sbb.service;

import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final SiteUserRepository siteUserRepository;
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
}
