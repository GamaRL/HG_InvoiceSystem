package mx.unam.fi.tsic.billapi.requests;

public record UserRegisterRequest(
  String email,
  String rfc,
  String password,
  String role,
  String taxRegimeCode,
  String postalCode
) { }
