package tinkoff.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tinkoff.demo.domain.ApplicationModel;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, BigInteger> {

    Optional<ApplicationModel> findByApplicationId(BigInteger id);

    List<ApplicationModel> findByContactId(BigInteger id);

    Optional<ApplicationModel> findFirstByContactIdOrderByCrtDateDesc(BigInteger id);

}
