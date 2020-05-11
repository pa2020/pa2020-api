package fr.esgi.api.dto.mapper;

import fr.esgi.api.dto.model.user.UserDto;
import fr.esgi.api.dto.model.user.RoleDto;
import fr.esgi.api.models.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setRoles(new HashSet<RoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDto.class))
                        .collect(Collectors.toSet())));
    }

}
