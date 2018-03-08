package me.ggulmool.lss.service;

import me.ggulmool.lss.model.User;
import me.ggulmool.lss.validation.EmailExistsException;

public interface IUserService {

    // read

    User findUserByEmail(String email);

    // write

    User registerNewUser(User user) throws EmailExistsException;

}

