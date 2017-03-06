package com.kd.example.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.kd.example.hibernate.dao.UserDao;
import com.kd.example.hibernate.entity.Address;
import com.kd.example.hibernate.entity.UserInfo;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    UserDao userDao;

    /** Simply selects the home view to render by returning its name. */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, ModelMap model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.put("serverTime", formattedDate);
        //prepareUsers(); //this is used to populate some test records in database
        return "home";
    }

    @SuppressWarnings("unused")
    private void prepareUsers() {
        for (int i = 51; i <= 55; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("username_" + i);
            userInfo.setAge(18 + i);
            userInfo.setFirstName("firstName_" + i);
            userInfo.setLastName("lastName_" + i);
            Address address = new Address();
            address.setLine1("line1_" + i);
            address.setLine2("line2_" + i);
            address.setPincode(123450 + i);
            address.setCity("City_" + i);
            address.setCountry("country_" + i);
            address.setState("state_" + i);
            userInfo.setAddress(address);
            userDao.save(userInfo);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(int pageNum, Locale locale, ModelMap model) throws JsonGenerationException, JsonMappingException, IOException {
        long numPages = userDao.getNumPages();
        model.put("pageNum", pageNum);
        if (pageNum == 0) {
            model.put("page", "first");
        } else if ((pageNum + 1) == numPages) {
            model.put("page", "last");
        }
        List<UserInfo> userInfos = userDao.getUsers(pageNum);
        model.put("users", mapper.writeValueAsString(userInfos));
        return "users";
    }
    
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userInfo.getId()).toUri());
        return new ResponseEntity<UserInfo>(userInfo, headers, HttpStatus.CREATED);
    }
}