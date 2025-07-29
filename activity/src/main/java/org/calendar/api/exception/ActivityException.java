package org.calendar.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ActivityException extends RuntimeException {

    public ActivityException(final String message) {

        super(message);
    }
}
