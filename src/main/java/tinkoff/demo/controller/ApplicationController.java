package tinkoff.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.service.ApplicationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@RestController
@RequestMapping("tinkoff/")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("{id}")
    public ApplicationModel getLastApplication(@PathVariable @NotNull @Valid BigInteger id) {
        return service.getApplicationModelById(id);

    }

}
