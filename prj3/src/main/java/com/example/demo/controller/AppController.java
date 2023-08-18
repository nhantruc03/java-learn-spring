package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    @GetMapping("")
    public ResponseEntity<JsonObject> test(){
        JsonObject res = new JsonObject();
        res.addProperty("test", "test after change 2");
        return new ResponseEntity<JsonObject>(res,HttpStatus.OK);
    }
    
}
