package com.gt.cds.controller;

import com.gt.cds.model.User;
import com.gt.cds.model.UserDTO;
import com.gt.cds.service.UserSvc;
import com.gt.cds.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserSvc userSvc;

    @RequestMapping("/users")
    public ResponseEntity<Object> getUsersWithinSalaryRange() {
        List<User> userList = userSvc.getUsersWithinSalaryRange(0L, 4000L);

        List<UserDTO> userDTOList = ObjectMapperUtils.mapAll(userList, UserDTO.class);

        Map<String, Object> json = new HashMap<>();
        json.put("result", userDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
