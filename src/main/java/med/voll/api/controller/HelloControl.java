package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloControl {
    @GetMapping
    public String HelloWorld() {
        return "Hello World! for SpringBoot :D";
    }
}
