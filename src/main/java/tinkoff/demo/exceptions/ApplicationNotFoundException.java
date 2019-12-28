package tinkoff.demo.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigInteger;

import static tinkoff.demo.exceptions.Messages.NOT_FOUND_MESSAGE;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ApplicationNotFoundException extends TinkoffBusinessAPIException {

    public ApplicationNotFoundException(String message) {
        super(message);
    }

    public ApplicationNotFoundException(BigInteger id) {
        super(NOT_FOUND_MESSAGE + id);
    }
}


