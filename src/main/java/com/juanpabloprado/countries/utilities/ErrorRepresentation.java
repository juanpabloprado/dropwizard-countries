package com.juanpabloprado.countries.utilities;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Juan on 12/19/2014.
 */
public class ErrorRepresentation {
    @NotBlank
    @Length(min = 3)
    private final int status;
    @NotBlank
    @Length(min = 2, max = 30)
    private final String property;
    @NotBlank
    @Length(min = 2, max = 255)
    private final String message;

    public ErrorRepresentation(int status, String property, String message) {
        this.status = status;
        this.property = property;
        this.message = message;
    }

    public ErrorRepresentation() {
        status = 0;
        property = null;
        message = null;
    }

    public int getStatus() {
        return status;
    }

    public String getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }
}
