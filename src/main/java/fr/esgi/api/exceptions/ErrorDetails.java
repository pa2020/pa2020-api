package fr.esgi.api.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorDetails {
    private final Date timestamp;
    private final String message;
    private final String details;
}
