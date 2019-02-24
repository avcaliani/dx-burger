package br.avcaliani.dxburgerapi.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Abstract Controller.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public class AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Default Error Handler.
     *
     * @param ex {@link Exception}.
     * @return {@link ResponseEntity} with Status Code {@code 500}.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exceptionHandler(Exception ex){
        logger.error("ERROR: {0} at {1}", ex.getMessage(), ex.getStackTrace()[0], ex);
        return ResponseEntity.status(500).body(new Response(ex, 200));
    }
}
