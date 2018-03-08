package me.ggulmool.lss.service;

import me.ggulmool.lss.model.User;
import me.ggulmool.lss.validation.EmailExistsException;

public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

    User findUserByEmail(String email);

}
