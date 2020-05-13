package fr.esgi.api.security.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class SignUpForm {

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private Set<String> role;

    private String password;

}
