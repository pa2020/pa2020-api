package fr.esgi.api.dto.user;

import fr.esgi.api.models.user.User;
import fr.esgi.api.repositories.user.UserRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
@RequiredArgsConstructor
public class UserDto implements IUserDto{

    private final UserRepository userRepository;

    /**
     * Show All users in db
     *
     * @return
     */
    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    /**
     * Search an existing user
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(long id) {
        Optional<User> user = Optional.of(userRepository.findById(id)).get();
        if (user.isPresent()){
            return user.get();
        }else
        throw new RuntimeException("Id client Introuvable!");
    }

    /**
     * Search an existing user
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username)).get();
        if (user.isPresent()) {
            return userRepository.listByUser(username);
        }
        throw new RuntimeException("Username Introuvable!");
    }


    /**
     * Update User Profile
     *
     * @param user
     * @return
     */
    @Override
    public User updateUser(User user, Long id) {
        Optional<User> search = Optional.of(userRepository.findById(id)).get();

        if (search.isPresent()) {
            User _user = search.get();
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setEmail(user.getEmail());
            return userRepository.save(_user);
        } else {
            throw new RuntimeException("User Id Introuvable!");
        }
    }
}
