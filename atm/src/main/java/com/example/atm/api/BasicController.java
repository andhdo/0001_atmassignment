package com.example.atm.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @RequestMapping("/home")
    public String home() {
        return "home solution for assignment; use /api as subcontext to see it in action";
    }

}
