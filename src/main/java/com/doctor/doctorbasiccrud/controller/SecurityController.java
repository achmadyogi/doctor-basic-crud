package com.doctor.doctorbasiccrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SecurityController {
    @Autowired
    private JwtEncoder encoder;

    @Value("${jwt.duration}")
    private Long jwtDuration;

    @PostMapping("/token")
    public @ResponseBody ResponseEntity<Map<String, Object>> token(Authentication authentication) {
        Instant now = Instant.now();

        // @formatter:off
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtDuration))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        Map<String, Object> result = new HashMap<>();
        result.put("token", this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
        result.put("expiryTime", claims.getExpiresAt().toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
