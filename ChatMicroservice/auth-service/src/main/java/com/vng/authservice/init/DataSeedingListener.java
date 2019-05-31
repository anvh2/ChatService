package com.vng.authservice.init;

import com.vng.authservice.model.User;
import com.vng.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        // Member account
        User user = new User();
        user.setEmail("user");
        user.setPassword("user");
        user.setChatCode("user"); //trung voi email
        user.setName("luffy");
        user.setGender("");
        user.setBirthday(null);
        user.setValid(true);
        userRepository.save(user);

        // Member account
        user = new User();
        user.setEmail("admin");
        user.setPassword("admin");
        user.setChatCode("admin"); //trung voi email
        user.setName("naruto");
        user.setGender("");
        user.setBirthday(null);
        user.setValid(true);
        userRepository.save(user);
    }

}

