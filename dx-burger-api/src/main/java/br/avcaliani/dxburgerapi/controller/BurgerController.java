package br.avcaliani.dxburgerapi.controller;

import br.avcaliani.dxburgerapi.controller.common.AbstractController;
import br.avcaliani.dxburgerapi.controller.common.Response;
import br.avcaliani.dxburgerapi.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Burger REST Controller.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@RestController
@RequestMapping("/burger")
@CrossOrigin(allowedHeaders = "*")
public class BurgerController extends AbstractController {

    @Autowired
    private BurgerService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Response> find() {
        return ResponseEntity.ok(new Response(this.service.find()));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Response> find(@PathVariable Long id) {
        return ResponseEntity.ok(new Response(this.service.find(id)));
    }
}
