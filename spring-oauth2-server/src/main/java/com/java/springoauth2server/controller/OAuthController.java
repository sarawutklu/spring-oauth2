package com.java.springoauth2server.controller;

import com.java.springoauth2server.models.StringResponse;
import com.java.springoauth2server.models.UserRequest;
import com.java.springoauth2server.service.UserService;
import com.java.springoauth2server.util.PkceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class OAuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/code-challenge")
    public Map<String, String> getCodeChallenge() {
        Map<String, String> response = new HashMap<>();
        try {
            PkceUtil pkce = new PkceUtil();
            String codeVerifier = pkce.generateCodeVerifier();
            String codeChallenge = pkce.generateCodeChallange(codeVerifier);
            response.put("code_challenge", codeChallenge);
            response.put("code_verifier", codeVerifier);
            response.put("code_challenge_method", "S256");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(OAuthController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @PostMapping("/singup")
    public ResponseEntity<StringResponse> singUp(@RequestBody UserRequest user) {
        try {
            userService.singup(user);
        }catch (Exception ex){
            return new ResponseEntity<StringResponse>(new StringResponse("sign up failure!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<StringResponse>(new StringResponse("sign up successful!"), HttpStatus.CREATED);
    }


}
