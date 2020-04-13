package fr.esgi.api.services;

import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Data
public class URIService {

    public static URI fromParent(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
