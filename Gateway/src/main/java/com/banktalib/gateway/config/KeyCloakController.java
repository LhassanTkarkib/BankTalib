package com.banktalib.gateway.config;

import com.banktalib.gateway.config.DTO.AuthenticationResponse;
import com.banktalib.gateway.config.DTO.UserAuthenticationDto;
import com.banktalib.gateway.config.DTO.UserDto;
import com.banktalib.gateway.config.DTO.UserRepresentationDto;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

    @RestController
    @AllArgsConstructor
    @RequestMapping(path = "api/v1/Auth")
    public class KeyCloakController {

        @Autowired
        KeyCloakService service;
        @PostMapping(path = "/register")
        public ResponseEntity<UserRepresentationDto> addUser(@RequestBody UserDto userDTO){
            return ResponseEntity.ok(service.addUser(userDTO));
        }

        @PostMapping(path = "/authenticate")
        public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserAuthenticationDto userDTO){
            return ResponseEntity.ok(service.authenticate(userDTO));
        }

        @GetMapping(path = "/{userName}")
        public List<UserRepresentation> getUser(@PathVariable("userName") String userName){
            List<org.keycloak.representations.idm.UserRepresentation> user = service.getUser(userName);
            return user;
        }

        @PutMapping(path = "/update/{userId}")
        public String updateUser(@PathVariable("userId") String userId,   @RequestBody UserDto userDTO){
            service.updateUser(userId, userDTO);
            return "User Details Updated Successfully.";
        }

        @DeleteMapping(path = "/{userId}")
        public String deleteUser(@PathVariable("userId") String userId){
            service.deleteUser(userId);
            return "User Deleted Successfully.";
        }

//        @GetMapping(path = "/verification-link/{userId}")
//        public String sendVerificationLink(@PathVariable("userId") String userId){
//            service.sendVerificationLink(userId);
//            return "Verification Link Send to Registered E-mail Id.";
//        }

//        @GetMapping(path = "/reset-password/{userId}")
//        public String sendResetPassword(@PathVariable("userId") String userId){
//            service.sendResetPassword(userId);
//            return "Reset Password Link Send Successfully to Registered E-mail Id.";
//        }
    }

