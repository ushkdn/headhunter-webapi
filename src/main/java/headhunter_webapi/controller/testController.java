package headhunter_webapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goida")
public class testController {
    @GetMapping("/test")
    public String hello(){
        return "hello";
    }
}
