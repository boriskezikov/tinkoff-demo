package tinkoff.demo.domain;


import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "application")
public class ApplicationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger applicationId;

    @Column(name = "crt_date")
    private Timestamp crtDate;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "contact_id")
    private BigInteger contactId;

}
