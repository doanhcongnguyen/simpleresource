package com.dcn.simpleresource.rest;

import com.dcn.simpleresource.aspect.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class SimpleResource {

    @Log("GET /hello")
    @GetMapping(value = "/hello")
    public ResponseEntity<?> getGreeting() {
        return new ResponseEntity<>("This api for every one..", HttpStatus.OK);
    }

    @Log("GET /protect-with-jwt")
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/protect-with-jwt")
    public ResponseEntity getSomething() {
        return new ResponseEntity("This resource is protected by jwt", HttpStatus.OK);
    }

    @Log("GET /no-protect-with-jwt")
    @GetMapping("/no-protect-with-jwt")
    public ResponseEntity getSomethingNoProtect() {
        return new ResponseEntity("This resource is NOT protected by jwt", HttpStatus.OK);
    }
}
