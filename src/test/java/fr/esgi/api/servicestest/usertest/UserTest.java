package fr.esgi.api.servicestest.usertest;

import fr.esgi.api.models.user.User;
import fr.esgi.api.repositories.user.UserRepository;
import fr.esgi.api.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {

    protected UserRepository userRepository;
    protected UserService userService;

    @BeforeEach
    protected void setup() {
        userRepository = mock(UserRepository.class);
        userService = mock(UserService.class);
    }

    @Test
    void should_add_new_user_and_returns_new_user_id() {
        ArrayList<User> mockUsers = new ArrayList<>();
        given(userRepository.findAll()).willReturn(mockUsers);
        for (long i = 0; i < 10; i++) {
            String username = "zakaria";
            String curUsername = username + i;
            User newUser = new User();
            newUser.setUser_id(i);
            newUser.setUsername(curUsername);
            mockUsers.add(newUser);

            given(userRepository.save(newUser)).willReturn(newUser);
            Optional<User> u = userRepository.findById(newUser.getUser_id());
            assertNotNull(u);
        }
    }

    @Test
    void should_User_empty_after_init() {
        given(userService.getUser()).willReturn(new ArrayList<>());
        assertTrue(userService.getUser().isEmpty());
    }

    @Test
    void should_return_AllUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());

        given(userService.getUser()).willReturn(users);
        assertFalse(users.isEmpty());
        assertEquals(5, userService.getUser().size());
    }

    @Test
    void should_return_user_with_id() {
        User user = new User();
        given(userService.getUserById(1L)).willReturn(user);
        assertNotNull(userService.getUserById(1L));
    }

    @Test
    void should_return_user_with_username() {
        User user = new User();
        user.setUsername("zakaria");
        given(userService.getUserByUsername("zakaria")).willReturn(user);
        assertFalse(user.equals("test"));
        assertThat(user.getUsername()).isEqualTo("zakaria");
    }

    @Test
    void should_update_profilUser() {
        User test = new User();
        test.setUser_id(1L);

        when(userService.updateUser(any(), eq(1L))).thenReturn(test);
        User user = userService.updateUser(test, test.getUser_id());
        assertNotNull(user);
        assertEquals(user, test);
    }
}
