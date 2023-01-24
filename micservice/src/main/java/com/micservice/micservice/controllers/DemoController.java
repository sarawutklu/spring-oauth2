package com.micservice.micservice.controllers;

import com.micservice.micservice.service.CustomAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private CustomAuthorityService customAuthorityService;

    @GetMapping("/demo")
    public Map<String, Object> demo(@RequestHeader("Authorization") String authHeader) {
        // Extract the JWT from the Authorization header
        String jwt = authHeader.substring("Bearer ".length());

        // Decode the JWT
        Jwt decodedJwt = jwtDecoder.decode(jwt);

        // Get the claims from the JWT
        return decodedJwt.getClaims();
    }

    @PreAuthorize("@customAuthorityService.hasAuthority('ADMIN')")
    @GetMapping("/secure")
    public String secureEndpoint() {
        return "This endpoint is secured with a JWT that has the 'read' scope.";
    }
}
