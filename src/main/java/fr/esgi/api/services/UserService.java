package fr.esgi.api.services;

import fr.esgi.api.dto.user.UserDto;
import fr.esgi.api.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserDto userDto;

    @Transactional(readOnly = true)
    @Override
    public List<User> getUser() {
        return userDto.getUser();
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        return userDto.getUserById(id);
    }

    @Transactional
    @Override
    public User getUserByUsername(String username) {
        return userDto.getUserByUsername(username);
    }

    @Transactional
    @Override
    public User updateUser(User user, Long id) {
        return userDto.updateUser(user, id);
    }
}
