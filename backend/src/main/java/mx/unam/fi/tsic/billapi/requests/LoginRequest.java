package mx.unam.fi.tsic.billapi.requests;

import lombok.Data;

@Data
public class LoginRequest{
  private String email;
  private String password;
}
