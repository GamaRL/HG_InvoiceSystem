package mx.unam.fi.tsic.billapi.settings;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;
import mx.unam.fi.tsic.billapi.entities.User;
import mx.unam.fi.tsic.billapi.requests.UserRegisterData;
import mx.unam.fi.tsic.billapi.responses.UserData;

@Configuration
public class UtilsConfiguration {
  
  @Bean
  public ModelMapper getModelMapper() {
    var mp = new ModelMapper();

    
    mp.typeMap(UserRegisterData.class, User.class)
    .addMappings(m -> {
      m.skip(User::setId);
      m.skip(User::setPassword);
    });

    mp.typeMap(User.class, UserData.class);

    return mp;
  }

  @Bean
  public SecretKey getSigningKey(
          @Value("${app.jwt.key}") String jwtKey
  ) {
    var keyBytes = jwtKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
