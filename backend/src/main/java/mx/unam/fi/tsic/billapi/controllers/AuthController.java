package mx.unam.fi.tsic.billapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.requests.LoginRequest;
import mx.unam.fi.tsic.billapi.requests.UserRegisterData;
import mx.unam.fi.tsic.billapi.responses.LoginResponse;
import mx.unam.fi.tsic.billapi.responses.UserData;
import mx.unam.fi.tsic.billapi.services.IAuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

  private final IAuthService authService;

  @PostMapping("/auth/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
      String token = authService.login(request.getEmail(), request.getPassword());
      if (token == null)
        return ResponseEntity.status(401).build();
      return ResponseEntity.ok(new LoginResponse(token));
  }
  
  @PostMapping("/auth/register")
  public ResponseEntity<Void> register (@RequestBody UserRegisterData request) {
    authService.register(request);
      
      return ResponseEntity.ok(null);
  }

  @GetMapping("/auth")
  public ResponseEntity<UserData> verify(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
      
      return ResponseEntity.ok(authService.validate(authentication.getName()));
  }
  
}
