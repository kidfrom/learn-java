package com.example.bankaccount.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;

@Service
public class GenerateTokenService {
  @Value("${jwt.secret}")
  private String jwtSecret;

  public String generate(String account_number) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, 10);

    String token = Jwts.builder()
            .claim("Account_Number", account_number)
            .setExpiration(calendar.getTime())
            .signWith(key)
            .compact();

    return token;
  }
}