package tinkoff.demo.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "application")
public class ApplicationModel {

    @Id
    @Column(name="application_id")
    private BigInteger applicationId;

    @Column(name="crt_date")
    private Timestamp crtDate;

    @Column(name="product_name")
    private String productName;

    @Column(name="contact_id")
    private BigInteger contactId;

}
