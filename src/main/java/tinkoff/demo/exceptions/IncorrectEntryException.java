package tinkoff.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect entry")
public class IncorrectEntryException extends TinkoffBusinessAPIException {
}
