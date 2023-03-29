package com.BikkadIt.controller;

import com.BikkadIt.dto.UserDto;
import com.BikkadIt.services.FileService;
import com.BikkadIt.services.UserServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private FileService fileService;

    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceI userServiceI;

    /**
     * Method under test: {@link UserController#createUser(UserDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateUser() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.BikkadIt.dto.UserDto["createdAt"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1300)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4568)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3821)
        //   See https://diff.blue/R013 to resolve this issue.

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON);

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setEmail("jane.doe@example.org");
        userDto.setGender("Gender");
        userDto.setImage("Image");
        userDto.setIsActive('A');
        userDto.setPassword("iloveyou");
        userDto.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setUserId(1L);
        userDto.setUserName("janedoe");
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(userDto));
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
    }
}

