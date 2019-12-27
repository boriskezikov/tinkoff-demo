package tinkoff.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "contact")
public class ContactModel {

    @Id
    @Column(name="contact_id")
    private String id;


}
