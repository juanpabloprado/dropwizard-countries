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
    @NotBlank
    @Length(min = 2, max = 255)
    private final String developerMessage;

    public ErrorRepresentation(int status, String property, String message, String developerMessage) {
        this.status = status;
        this.property = property;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public ErrorRepresentation() {
        status = 0;
        property = null;
        message = null;
        developerMessage = null;
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

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
