package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.service.service.JwtService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.publicKey}")
    private String publicKey;

    @Override
    public Authentication parseToken(String jwt) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        List<String> roles = (List<String>) claims.get("authorities");
        var authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

    private PublicKey getPublicKey() {
        try {
            var cleanKey = publicKey.replaceAll("\\s+", "");
            var keyFactory = KeyFactory.getInstance("RSA");
            var keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(cleanKey));
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            log.error("Error decrypting public key");
            return null;
        }
    }
}
