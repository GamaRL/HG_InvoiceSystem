package mx.unam.fi.tsic.billapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.entities.User;
import mx.unam.fi.tsic.billapi.repositories.TaxRegimeRepository;
import mx.unam.fi.tsic.billapi.repositories.UserRepository;
import mx.unam.fi.tsic.billapi.requests.UserRegisterData;
import mx.unam.fi.tsic.billapi.responses.UserData;
import mx.unam.fi.tsic.billapi.utils.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class JwtAuthService implements IAuthService {

	private final UserRepository userRepository;
	private final TaxRegimeRepository taxRegimeRepository;
	private final UserDetailsService userDetailsServiceImpl;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider tokenProvider;
	private final ModelMapper mapper;
  
	public String login(String username, String password) {
		try {
			var user = userDetailsServiceImpl.loadUserByUsername(username);
		
			var validLogin = passwordEncoder.matches(password, user.getPassword());

			if (!validLogin) {
				return null;
			}

			return tokenProvider.createToken(username);
		} catch(UsernameNotFoundException ex) {
			return null;
		}
	}

	@Override
	public void register(UserRegisterData userData) {

		var user = mapper.getTypeMap(UserRegisterData.class, User.class)
		.map(userData);
		user.setPassword(passwordEncoder.encode(userData.getPassword()));

		var taxRegime = taxRegimeRepository.getByCode(userData.getTaxRegimeCode()).orElseThrow();
		user.setTaxRegime(taxRegime);

		userRepository.save(user);

		return;
	}

	@Override
	public UserData validate(String username) {
		var user = userRepository.getUserByUsername(username).get();

		return mapper.getTypeMap(User.class, UserData.class)
		.map(user);
	}
}
