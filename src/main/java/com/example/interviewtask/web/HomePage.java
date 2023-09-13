package com.example.interviewtask.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {

    @Operation(description = "when get request is done " +
            "html file is returned to show appropriate visualization")
    @GetMapping(value = "/")
    public String home() {
       return "index";
    }

}
