package br.avcaliani.dxburgerapi.controller;

import br.avcaliani.dxburgerapi.controller.common.AbstractController;
import br.avcaliani.dxburgerapi.controller.common.Response;
import br.avcaliani.dxburgerapi.domain.to.OrderTO;
import br.avcaliani.dxburgerapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Order REST Controller.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(allowedHeaders = "*")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService service;

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

    @PostMapping
    @ResponseBody
    public ResponseEntity<Response> find(@RequestBody OrderTO order) {
        return ResponseEntity.ok(new Response(this.service.save(order)));
    }
}
