package tinkoff.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.exceptions.TinkoffBusinessAPIException;
import tinkoff.demo.service.ApplicationService;

import java.math.BigInteger;
import java.util.List;

import static tinkoff.demo.exceptions.Messages.WRONG_HEADER_MESSAGE;

@RestController
@RequestMapping("tinkoff/")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }


    /*
    Manual implementation of xml/json selection.
    This method makes user for strict manual choice.
    If no header found than error occurs.
     */

    @GetMapping(value = "manual/{id}")
    @ResponseBody
    public ResponseEntity getLastApplicationStrict(@PathVariable BigInteger id,
                                                   @RequestHeader String Accept)
            throws JsonProcessingException {

        ApplicationModel response = service.getApplicationModelById(id);
        ObjectMapper mapper;

        if (Accept.equals(MediaType.APPLICATION_XML_VALUE)) {
            mapper = new XmlMapper();
            String xmlResp = mapper.writeValueAsString(response);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                    .body(xmlResp);

        } else if (Accept.equals(MediaType.APPLICATION_JSON_VALUE)) {
            mapper = new ObjectMapper();
            String jsonResp = mapper.writeValueAsString(response);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(jsonResp);
        } else {
            throw new TinkoffBusinessAPIException(WRONG_HEADER_MESSAGE);
        }
    }

    /*
    Standard method. Response type equals to 'json' by default.
    If  Accepting = 'application/xml' header sent, then xml representation returns;
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
