package tinkoff.demo.utils;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect entry")
public class TinkoffBusinessAPIException extends RuntimeException {

    public TinkoffBusinessAPIException(String message) {
        super(message);
    }

}
