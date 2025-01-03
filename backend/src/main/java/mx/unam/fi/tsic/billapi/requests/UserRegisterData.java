package mx.unam.fi.tsic.billapi.requests;

import lombok.Data;

@Data
public class UserRegisterData {
  private String name;
  private String email;
  private String rfc;
  private String password;
  private String role;
  private String taxRegimeCode;
  private String postalCode;
};
