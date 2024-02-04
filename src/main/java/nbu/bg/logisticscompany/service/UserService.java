package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.UserRegisterDto;

public interface UserService {
    boolean registerClient(UserRegisterDto userRegisterDto);
}
