package com.banktalib.gateway.config;

import lombok.AllArgsConstructor;
import org.keycloak.representations.account.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @AllArgsConstructor
    @RequestMapping(path = "api/v1/KeyCloak")
    public class KeyCloakController {

        @Autowired
        KeyCloakService service;
        @PostMapping
        public String addUser(@RequestBody UserDto userDTO){
            service.addUser(userDTO);
            return "User Added Successfully.";
        }
//
//        @GetMapping(path = "/{userName}")
//        public List<UserRepresentation> getUser(@PathVariable("userName") String userName){
//            List<UserRepresentation> user = service.getUser(userName);
//            return user;
//        }
//
//        @PutMapping(path = "/update/{userId}")
//        public String updateUser(@PathVariable("userId") String userId,   @RequestBody UserDTO userDTO){
//            service.updateUser(userId, userDTO);
//            return "User Details Updated Successfully.";
//        }
//
//        @DeleteMapping(path = "/{userId}")
//        public String deleteUser(@PathVariable("userId") String userId){
//            service.deleteUser(userId);
//            return "User Deleted Successfully.";
//        }
//
//        @GetMapping(path = "/verification-link/{userId}")
//        public String sendVerificationLink(@PathVariable("userId") String userId){
//            service.sendVerificationLink(userId);
//            return "Verification Link Send to Registered E-mail Id.";
//        }
//
//        @GetMapping(path = "/reset-password/{userId}")
//        public String sendResetPassword(@PathVariable("userId") String userId){
//            service.sendResetPassword(userId);
//            return "Reset Password Link Send Successfully to Registered E-mail Id.";
//        }
    }

