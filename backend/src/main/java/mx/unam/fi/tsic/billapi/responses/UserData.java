package mx.unam.fi.tsic.billapi.responses;

import lombok.Data;

@Data
public class UserData{
  private long id;
  private String email;
  private String name;
  private String rfc;
  private String role;
}
