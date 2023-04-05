package net.botlify.brightdata.exception;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Exception to the bright data API.
 * This exception is called when the API return an error.
 */
public class BrightDataException extends Exception {

    /**
     * The code of the response.
     */
    @Getter
    private final int code;

    /**
     * The body of the response.
     */
    @NotNull @Getter
    private final String body;

    /**
     * Constructor of the bright data exception.
     * @param code The error code.
     * @param body The error body.
     */
    public BrightDataException(final int code,
                               @NotNull final String body) {
        super("(" + code + ") " + body);
        this.code = code;
        this.body = body;
    }

}
