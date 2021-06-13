package com.bastos.hroauth.services;

import com.bastos.hroauth.entities.User;
import com.bastos.hroauth.feingclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

     private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();

        if (Objects.isNull(user)) {
            logger.error("Email not found");
            throw new IllegalArgumentException("Email not found");
        }

        logger.info("Email found: " + email);
        return user;
    }
}
