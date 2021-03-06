package br.avcaliani.dxburgerapi.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Abstract Controller.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@CrossOrigin(allowedHeaders = "*")
public abstract class AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Default Error Handler.
     *
     * @param ex {@link Exception}.
     * @return {@link ResponseEntity} with Status Code {@code 500}.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exceptionHandler(Exception ex){
        if (logger.isErrorEnabled())
            logger.error(String.format("ERROR: %s at %s", ex.getMessage(), ex.getStackTrace()[0]), ex);
        return ResponseEntity.status(200).body(new Response(ex, 200));
    }
}
