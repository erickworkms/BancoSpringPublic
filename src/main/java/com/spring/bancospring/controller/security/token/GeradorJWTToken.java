package com.spring.bancospring.controller.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeradorJWTToken {
    public static final String ROLES_AUTHORITIES = "authorities";

    @Value("${security.config.key}")
    private String secret;

    @Value("${security.config.prefix}")
    private String prefix;

    public String criaToken(GeradorToken jwtObject) {
        try {
            System.out.println("O segredo é este aqui: "+secret);
            String token = JWT.create().withIssuer("auth-api")
                    .withSubject(jwtObject.getUsuario())
                    .withIssuedAt(jwtObject.getDataCriacaoToken())
                    .withExpiresAt(jwtObject.getDataExpiracaoToken())
                    .withClaim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRegras()))
                    .sign(Algorithm.HMAC256(secret));
            return prefix + " " + token;

        } catch (Exception exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String verificaToken(String token) {

        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_", ""))).collect(Collectors.toList());
    }
}
