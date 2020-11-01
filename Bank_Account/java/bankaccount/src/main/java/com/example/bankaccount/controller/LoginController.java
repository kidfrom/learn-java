package com.example.bankaccount.controller;

import com.example.bankaccount.model.User_InfoModel;
import com.example.bankaccount.model.User_LoginModel;
import com.example.bankaccount.repository.User_InfoImpl;
import com.example.bankaccount.repository.User_LoginImpl;
import com.example.bankaccount.service.GenerateTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
  @Autowired
  User_LoginImpl user_login;

  @Autowired
  User_InfoImpl user_info;

  @Autowired
  GenerateTokenService generateTokenService;

  @PostMapping("api/v1/login")
  public ResponseEntity<?> login(@RequestBody User_LoginModel user_loginModel) {
    if (this.user_login.login(user_loginModel)) {
      User_InfoModel user_infoModel = this.user_info.select(user_loginModel.getUser_ID());

      String token = generateTokenService.generate(user_infoModel.getAccount_Number());

      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 200);
      responseBody.put("message", "OK");
      responseBody.put("Full_Name", user_infoModel.getFull_Name());
      responseBody.put("ISO_4217", user_infoModel.getISO_4217());
      responseBody.put("token", token);

      return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 404);
      responseBody.put("message", "NOT_FOUND");

      return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

  }
}
