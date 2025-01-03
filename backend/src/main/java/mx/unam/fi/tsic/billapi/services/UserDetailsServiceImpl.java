package mx.unam.fi.tsic.billapi.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    mx.unam.fi.tsic.billapi.entities.User user = userRepository
      .getUserByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return User.withUsername(user.getEmail())
      .password(user.getPassword())
      .authorities(user.getRole())
      .accountExpired(false)
      .credentialsExpired(false)
      .disabled(false)
      .build();
  }
}
