package com.gt.cds.service;

import com.gt.cds.model.User;
import com.gt.cds.repository.UserRepository;
import com.gt.cds.util.CsvToJPA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserSvcImpl implements UserSvc{

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    Logger logger = LogManager.getRootLogger();

    public List<User> getUsersWithinSalaryRange(float min, float max){
        return userRepository.findBySalaryRange(min, max);
    }

    @PostConstruct
    public void init(){
        String file_path = env.getProperty("users.file_path");
        List<User> userList = new ArrayList<>();
        try {
            userList = CsvToJPA.readCSVtoBeans(file_path, User.class);
        } catch (IOException e) {
            logger.error(e);
        }
        for (User user : userList)
            logger.info(user);
        logger.info("User list size: " + userList.size());
        userRepository.saveAll(userList);
        logger.info("Saved all users into h2");
    }
}
