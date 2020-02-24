package com.gt.cds;

import com.gt.cds.model.User;
import com.gt.cds.model.UserDTO;
import com.gt.cds.service.UserSvc;
import com.gt.cds.util.CsvToJPA;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CdsApplicationTests {

	@Autowired
	private Environment env;

	@Autowired
	private UserSvc userSvc;

	@Test
	void contextLoads() {
		List<User> userList = new ArrayList<>();
		try {
			userList = CsvToJPA.readCSVtoBeans(env.getProperty("users.file_path"), User.class);
		} catch (IOException e) {
			System.out.println(e);
		}
		assertEquals(2, userList.size());
	}

	@Test
	void modelMapperTest(){
		ModelMapper modelMapper = new ModelMapper();
		List<User> userList = userSvc.getUsersWithinSalaryRange(0L, 4000L);
		for (User user : userList) {
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			assertEquals(user.getName(), userDTO.getName());
			assertEquals(user.getSalary(), userDTO.getSalary());
		}
	}
}
