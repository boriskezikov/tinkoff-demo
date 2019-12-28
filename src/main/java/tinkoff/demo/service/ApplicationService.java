package tinkoff.demo.service;


import lombok.RequiredArgsConstructor;
import org.h2.jdbc.JdbcSQLException;
import org.springframework.stereotype.Service;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.utils.ApplicationNotFoundException;
import tinkoff.demo.utils.TinkoffBusinessAPIException;
import tinkoff.demo.repository.ApplicationRepository;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static tinkoff.demo.utils.Messages.EMPTY_RESULT;
import static tinkoff.demo.utils.Messages.H2_ERROR_MESSAGE;


@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repo;

    public ApplicationModel getApplicationModelById(BigInteger id) {
        try {
            Optional<ApplicationModel> applicationModel = repo.findFirstByContactIdOrderByCrtDateDesc(id);
            return applicationModel
                    .orElseThrow(()->new ApplicationNotFoundException(id));
        }
        catch (JdbcSQLException | IOException ex) {
            throw new TinkoffBusinessAPIException(H2_ERROR_MESSAGE + ex.getMessage());
        }
    }


    public List<ApplicationModel> getAll(){
        List<ApplicationModel> apps =  repo.findAll();
        if (apps.isEmpty()){
            throw new ApplicationNotFoundException(EMPTY_RESULT);
        }
        return apps;
    }


}
