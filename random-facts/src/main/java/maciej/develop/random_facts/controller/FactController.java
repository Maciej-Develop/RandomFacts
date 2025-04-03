package maciej.develop.random_facts.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import maciej.develop.random_facts.model.Fact;
import maciej.develop.random_facts.service.FactService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FactController {

    @Autowired
    private FactService service;

    @GetMapping
    public ResponseEntity<List<Fact>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/fact")
    public ResponseEntity<Fact> getOne() {
        try {
            List<Long> ids = service.getIds();
            Random r = new Random();
            Fact f = service.getById(ids.get(r.nextInt(ids.size())));
            if (f == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(f);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/img")
    public ResponseEntity<byte[]> displayImg(@RequestParam("id") long id) {
        try {
            Fact fact = service.getById(id);
            if (fact == null) {
                return ResponseEntity.notFound().build();
            }
            byte[] img = fact.getImage().getBytes(1, (int) fact.getImage().length());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
