package com.example.ticketShop.security;


import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.entity.User;
import com.example.ticketShop.enums.UserType;
import com.example.ticketShop.exception.BadRequestException;
import com.example.ticketShop.exception.ForbiddenException;
import com.example.ticketShop.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.System.currentTimeMillis;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    public static final String secret = "secret-secret-sercet";

    public static final long JWT_TOKEN_VALIDITY = 18000;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public String authenticateUser(String username, String password) {
        User foundUser = userService.findByName(username, ErrorCodes.USER_NOT_FOUND_BY_NAME);

        if (passwordEncoder.matches(password, foundUser.getPassword())) {
            return generateToken(foundUser);
        } else {
            throw new BadRequestException(ErrorCodes.LOGIN_ERROR);
        }
    }

    private String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getUserType());
        claims.put("user_id", user.getUuid());
        return doGenerate(claims, user.getEmail());
    }

    private String doGenerate(Claims claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentTimeMillis()))
                .setExpiration(new Date(currentTimeMillis() + JWT_TOKEN_VALIDITY * 100000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private static void validateToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();

        if (Long.valueOf(claims.get("exp").toString()) > (currentTimeMillis() / 1000)) {
            log.info("token is valid {}", token);
        }else{
            throw new BadRequestException(ErrorCodes.TOKEN_EXPIRED);
        }
    }

    public static void validateAdmin(String token){
        validateToken(token);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();

        if(!claims.get("role").equals(UserType.ADMIN)){
            throw new ForbiddenException(ErrorCodes.UNAUTHORIZED);
        }
        log.info("Authorized admin user!");
    }

    public static void validateLogged(String token){
        validateToken(token);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();

        if(!claims.get("role").equals(UserType.LOGGED_USER)){
            throw new ForbiddenException(ErrorCodes.UNAUTHORIZED);
        }
        log.info("Authorized basic user!");
    }
}

