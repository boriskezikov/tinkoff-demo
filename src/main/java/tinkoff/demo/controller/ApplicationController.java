package tinkoff.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.service.ApplicationService;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("tinkoff/")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    /*
    For response type change need to send 'Accept' header.
    If  Accept = 'application/xml' header sent, then xml representation returns;
    Else json as default.
     */

    @GetMapping(value = "{id}")
    public ApplicationModel getLastApplication(@PathVariable BigInteger id) {
        return service.getApplicationModelById(id);
    }


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<ApplicationModel> getAllApplications() {
        return service.getAll();
    }

}
