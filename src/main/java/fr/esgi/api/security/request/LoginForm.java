package fr.esgi.api.security.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Getter
@Setter
public class LoginForm {

    private String username;

    private String password;
}
