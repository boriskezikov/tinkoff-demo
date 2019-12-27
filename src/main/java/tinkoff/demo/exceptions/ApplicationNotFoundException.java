package tinkoff.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No applications found for id")
public class ApplicationNotFoundException extends TinkoffBusinessAPIException {

}


