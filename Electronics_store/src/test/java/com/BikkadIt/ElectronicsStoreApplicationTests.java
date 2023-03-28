package com.BikkadIt;

import com.BikkadIt.dto.UserDto;
import com.BikkadIt.entities.User;
import com.BikkadIt.repository.UserRepository;
import com.BikkadIt.services.UserServiceI;
import org.hibernate.query.criteria.internal.expression.SimpleCaseExpression;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ElectronicsStoreApplicationTests {
    @Autowired
    private UserServiceI userServiceI;
    @MockBean
    private UserRepository userRepo;

//    @Test
//    public void getAllUsersTest() {
//        when(userRepo.findAll()).thenReturn((List<User>) (new User(1, "gita", "gitamali@gmail.com", "acd", "female", "I am engg")), (List<User>) new User(1, "shradha", "shradha12@gmail.com", "xyz", "female", "I am doc"));
//
//        String userId = null;
//        assertEquals(2, userServiceI.getAllUsers(1, 2, userId, "asc").getPageSize());
//    }

}
