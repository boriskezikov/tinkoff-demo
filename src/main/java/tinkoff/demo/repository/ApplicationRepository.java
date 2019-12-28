package tinkoff.demo.repository;

import org.h2.jdbc.JdbcSQLException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tinkoff.demo.domain.ApplicationModel;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, BigInteger> {

    Optional<ApplicationModel> findFirstByContactIdOrderByCrtDateDesc(BigInteger id) throws IOException, JdbcSQLException;

    List<ApplicationModel> findAll();


}
