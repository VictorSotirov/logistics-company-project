package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.UserRegisterDto;
import nbu.bg.logisticscompany.model.dto.UserUpdateDto;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Register client boolean.
     *
     * @param userRegisterDto the user register dto
     * @return the boolean
     */
    boolean registerClient(UserRegisterDto userRegisterDto);

    /**
     * Update user.
     *
     * @param id   the id
     * @param user the user
     */
    void updateUser(String id, UserUpdateDto user);
}
