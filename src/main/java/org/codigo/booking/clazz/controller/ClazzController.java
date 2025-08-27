package org.codigo.booking.clazz.controller;

import org.codigo.booking.clazz.model.Clazz;
import org.codigo.booking.clazz.service.ClazzService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClazzController {
    private final ClazzService clazzService;

    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    @GetMapping
    public List<Clazz> findAll() {
        return clazzService.findAll();
    }

    @GetMapping("/{id}")
    public Clazz findById(@PathVariable Long id) {
        return clazzService.findById(id);
    }

    @PostMapping
    public Clazz save(@RequestBody Clazz clazz) {
        return clazzService.save(clazz);
    }

    @PutMapping("/{id}")
    public Clazz update(@PathVariable Long id, @RequestBody Clazz clazz) {
        clazz.setId(id);
        return clazzService.update(clazz);
    }
}
