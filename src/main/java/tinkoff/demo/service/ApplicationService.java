package tinkoff.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.exceptions.ApplicationNotFoundException;
import tinkoff.demo.repository.ApplicationRepository;
import java.math.BigInteger;
import java.util.Optional;



@Service
public class ApplicationService {

    private final ApplicationRepository repo;

    @Autowired
    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public ApplicationModel getApplicationModelById(BigInteger id) throws ApplicationNotFoundException {
        Optional<ApplicationModel> applicationModel = repo.findFirstByContactIdOrderByCrtDateDesc(id);
        return applicationModel.orElseThrow(ApplicationNotFoundException::new);
    }


}
