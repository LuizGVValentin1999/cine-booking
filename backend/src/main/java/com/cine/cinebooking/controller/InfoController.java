package com.cine.cinebooking.controller;

import com.cine.cinebooking.dto.InfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/info")
    public InfoResponse info() {
        return new InfoResponse("cine-booking", "running");
    }
}