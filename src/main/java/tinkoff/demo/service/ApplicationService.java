package tinkoff.demo.service;


import org.h2.jdbc.JdbcSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.exceptions.ApplicationNotFoundException;
import tinkoff.demo.exceptions.TinkoffBusinessAPIException;
import tinkoff.demo.repository.ApplicationRepository;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Service
public class ApplicationService {

    private final ApplicationRepository repo;

    @Autowired
    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public ApplicationModel getApplicationModelById(BigInteger id) {
        try {
            Optional<ApplicationModel> applicationModel = repo.findFirstByContactIdOrderByCrtDateDesc(id);
            return applicationModel.orElseThrow(()->{
                throw new ApplicationNotFoundException(id);
            });
        }
        catch (JdbcSQLException | IOException ex) {
            throw new TinkoffBusinessAPIException("Error connecting to H2" + ex.getMessage());
        }

    }


    public List<ApplicationModel> getAll(){
        List<ApplicationModel> apps =  repo.findAll();
        if (apps.isEmpty()){
            throw new ApplicationNotFoundException("Dataset is empty");
        }
        return apps;
    }


}
