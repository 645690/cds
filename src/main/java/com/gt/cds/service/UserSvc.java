package com.gt.cds.service;

import com.gt.cds.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserSvc {

    public List<User> getUsersWithinSalaryRange(float min, float max);
}
