package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.JwtResponse;
import nbu.bg.logisticscompany.model.dto.UserLoginDto;
import nbu.bg.logisticscompany.model.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<JwtResponse> login(UserLoginDto userLoginDto);

    boolean register(UserRegisterDto userRegisterDto);
}
