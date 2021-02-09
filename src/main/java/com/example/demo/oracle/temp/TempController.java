package com.example.demo.oracle.temp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temps")
public class TempController {
    private final TempMapper mapper;

    public TempController(final TempMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public Temp find(@PathVariable final long id) {
        return mapper.find(id);
    }

    @PostMapping("/{id}")
    public void save(@PathVariable final long id) {
        mapper.save(id);
    }
}
