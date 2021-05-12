package com.Kelp2.kelp.services;

import com.Kelp2.kelp.DAO.UserRepo;
import com.Kelp2.kelp.models.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){this.userRepo = userRepo;}

    public User findUserByID(int id){
        return userRepo.getOne(id);
    }

    public User findByUserName(String user){
        return userRepo.findUserByUserName(user);
    }

    public User findByEmail(String user){
        return userRepo.findUserByEmail(user);
    }

    public User updateUserInfoByEmail(String json){
        try{

            ObjectMapper om = new ObjectMapper();
            User user = null;
            user=om.readValue(json,User.class);
            logger.info(user.toString());

            User oldInfo = userRepo.findUserByEmail(user.getEmail());

            if(user.getUserName().isEmpty()){
                logger.info("nothing");
            }else {
                oldInfo.setUserName(user.getUserName());
            }

            if(user.getProfilePic().isEmpty()){
                logger.info("nothing");
            }else {
                oldInfo.setProfilePic(user.getProfilePic());
            }
            if(user.getFishPersonality().isEmpty()){
                logger.info("nothing");
            }else{
                oldInfo.setFishPersonality(user.getFishPersonality());
            }

            if(user.getLocation().isEmpty()){
                logger.info("nothing");
            }else{
                oldInfo.setLocation(user.getLocation());
            }

            if(user.getTwitter().isEmpty()){
                logger.info("nothing");
            }else{

            oldInfo.setTwitter(user.getTwitter());
            }

            if(user.getFacebook().isEmpty()){
                logger.info("nothing");
            }else{

                oldInfo.setFacebook(user.getFacebook());
            }

            if(user.getInstagram().isEmpty()){
                logger.info("nothing");
            }else{

                oldInfo.setInstagram(user.getInstagram());
            }

            if(user.getBio().isEmpty()){
                logger.info("nothing");
            }else{

                oldInfo.setBio(user.getBio());
            }


            oldInfo.setEmail(user.getEmail());

            userRepo.save(oldInfo);

            return oldInfo;
        }catch (Exception e){
            logger.warn(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public User saveUser(String json)  {
        ObjectMapper om = new ObjectMapper();
        System.out.println("in the service!!");
        User user = null;
        try {
            user = om.readValue(json, User.class);
            if(user !=null) userRepo.save(user);
            return user;
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
            return null;
        } catch (Exception e){
            logger.warn(e.getMessage());
            return null;
        }

    }

}
