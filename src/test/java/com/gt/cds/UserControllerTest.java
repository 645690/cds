package com.gt.cds;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt.cds.controller.UserController;
import com.gt.cds.model.User;
import com.gt.cds.service.UserSvc;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserSvc userSvc;

    /**
     * Overrides default - JsonSmartJsonProvider
     * Used for keeping BigDecimal type when serializing
     * **/
    @BeforeEach
    public void init(){
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonProvider(objectMapper);
            private final MappingProvider mappingProvider = new JacksonMappingProvider(objectMapper);

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });
    }

    @Test
    public void usersController() throws Exception {

        User john = new User("John", new BigDecimal("2500.05"));
        User mary = new User("Mary", new BigDecimal("3500.00"));

        List<User> userList = new ArrayList<User>();
        userList.add(john);
        userList.add(mary);

        given(userSvc.getUsersWithinSalaryRange(0L, 4000L)).willReturn(userList);

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result[0].name", is(john.getName())))
                .andExpect(jsonPath("$.result[0].salary", is(john.getSalary())));
    }
}
