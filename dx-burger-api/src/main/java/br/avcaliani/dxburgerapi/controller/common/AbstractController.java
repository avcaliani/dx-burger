package br.avcaliani.dxburgerapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AbstractController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exceptionHandler(Exception ex){
        LOG.error(String.format("ERROR: %s at %s", ex.getMessage(), ex.getStackTrace()[0]));
        Response<Object> response = new Response<Object>();
        response.setStatus(500);
        response.getErrors().add(ex.getMessage());

        return ResponseEntity.status(500).body(response);
    }
}
