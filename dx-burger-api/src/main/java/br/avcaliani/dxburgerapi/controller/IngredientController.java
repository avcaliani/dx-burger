package br.avcaliani.dxburgerapi.controller;

import br.avcaliani.dxburgerapi.controller.common.AbstractController;
import br.avcaliani.dxburgerapi.controller.common.Response;
import br.avcaliani.dxburgerapi.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Ingredient REST Controller.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@RestController
@RequestMapping("/ingredient")
@CrossOrigin(allowedHeaders = "*")
public class IngredientController extends AbstractController {

    @Autowired
    private IngredientService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Response> find() {
        return ResponseEntity.ok(new Response(this.service.find()));
    }
}
