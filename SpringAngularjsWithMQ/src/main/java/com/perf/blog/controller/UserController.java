package com.perf.blog.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.perf.blog.model.UserDetails;
import com.perf.blog.service.UserDetailServiceImpl;



@RestController
public class UserController{
	
	@Autowired
	private UserDetailServiceImpl impl;
	
    public List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
    
    public UserController() {
        userDetailsList.add(new UserDetails("21748","SasiKumar", "Mechanical Engg"));
        userDetailsList.add(new UserDetails("21749","Ansar", "Mechanical Engg , The DON of GCE"));
        userDetailsList.add(new UserDetails("21750","Rafeek", "Computer Science"));
        userDetailsList.add(new UserDetails("21751","Sharath", "Information Technology"));
    }
    
    @RequestMapping(value="/userdetails",method=RequestMethod.GET,produces="application/json")
    public List<UserDetails> GetUserdetails(){
        return userDetailsList;
    }
    
    @RequestMapping(value="/user",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<UserDetails> ProcessUser(@RequestBody UserDetails userDetails){
        boolean nameExist = false;
        for(UserDetails ud : userDetailsList){
            if(ud.getName().equals(userDetails.getName())){
                nameExist = true;
                ud.setDepartment(userDetails.getDepartment());
                break;
            }
        }
        if(!nameExist){
            userDetailsList.add(userDetails);
        }
        // save
        impl.sendOrder(userDetails);
        return userDetailsList;
    }
   
    
    @RequestMapping(value="/saveUser",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<UserDetails> saveUser(@RequestBody UserDetails userDetails){
        boolean nameExist = false;
        for(UserDetails ud : userDetailsList){
            if(ud.getName().equals(userDetails.getName())){
                nameExist = true;
                ud.setDepartment(userDetails.getDepartment());
                break;
            }
        }
        if(!nameExist){
            userDetailsList.add(userDetails);
        }
       // save
        impl.sendOrder(userDetails);
        return userDetailsList;
    }
   
    @RequestMapping(value="/deleteUsers",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<UserDetails> deleteUsers(@RequestBody UserDetails userDetails){
       Iterator<UserDetails> it = userDetailsList.iterator();
       while(it.hasNext()){
             UserDetails ud = (UserDetails) it.next();
             for (String id  : userDetails.getEditList()) {
            	 if(ud.getId().equals(id)){
                	 it.remove();
                 }
             }
       }
       return userDetailsList;
    }
    
    @RequestMapping(value="/editUser",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public UserDetails editUser(@RequestBody UserDetails ud){
      UserDetails userDetails = null;
       Iterator<UserDetails> it = userDetailsList.iterator();
       while(it.hasNext()){
             UserDetails details = (UserDetails) it.next();
             for (String id  : ud.getEditList()) {
            	 if(details.getId().equals(id)){
            		 userDetails = details; 
            	 }
             }
       }
       return userDetails;
    }
    
    
    @RequestMapping(value="/updateUser",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void updateUser(@RequestBody UserDetails ud){
       Iterator<UserDetails> it = userDetailsList.iterator();
       while(it.hasNext()){
             UserDetails details = (UserDetails) it.next();
            	 if(details.getId().equals(ud.getId())){
            		 details.setDepartment(ud.getDepartment());
            		 details.setName(ud.getName());
            	 }
       }
    }
    
    
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteuser",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity DeleteUser(@RequestBody UserDetails userDetails) {
        Iterator<UserDetails> it = userDetailsList.iterator();
        while(it.hasNext()){
            UserDetails ud = (UserDetails) it.next();
            if(ud.getName().equals(userDetails.getName()))
                it.remove();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}