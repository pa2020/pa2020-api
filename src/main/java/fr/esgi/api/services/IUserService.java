package fr.esgi.api.services;

import fr.esgi.api.models.user.User;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

public interface IUserService {
    List<User> getUser();

    User getUserById(long id);

    User getUserByUsername(String username);

    User updateUser(User user, Long id);
}
