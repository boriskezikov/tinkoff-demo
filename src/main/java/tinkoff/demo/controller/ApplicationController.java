package tinkoff.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.exceptions.EntryDataConstraint;
import tinkoff.demo.service.ApplicationService;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("tinkoff/")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("{id}")
    @EntryDataConstraint
    public ApplicationModel getLastApplication(@PathVariable @Valid BigInteger id) {
        return service.getApplicationModelById(id);

    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<ApplicationModel> getAllApplications(){
        return service.getAll();
    }

}
