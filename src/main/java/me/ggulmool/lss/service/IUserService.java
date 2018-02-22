package me.ggulmool.lss.service;

import me.ggulmool.lss.validation.EmailExistsException;
import me.ggulmool.lss.web.model.User;

public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

}
