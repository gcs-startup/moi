package gcs.moi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheck {

    @RequestMapping("/healthcheck")
    public String healthcheck() {
        return "ok";
    }
}
