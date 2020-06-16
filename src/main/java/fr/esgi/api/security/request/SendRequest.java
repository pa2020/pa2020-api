package fr.esgi.api.security.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class SendRequest {
    private String sentence;
    private Set<String> state;
    private Date created_time;
}
