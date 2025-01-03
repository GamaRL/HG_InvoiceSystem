package mx.unam.fi.tsic.billapi.services;

import mx.unam.fi.tsic.billapi.requests.UserRegisterData;
import mx.unam.fi.tsic.billapi.responses.UserData;

public interface IAuthService {

	String login(String username, String password);
	void register(UserRegisterData user);
	UserData validate(String username);
}

