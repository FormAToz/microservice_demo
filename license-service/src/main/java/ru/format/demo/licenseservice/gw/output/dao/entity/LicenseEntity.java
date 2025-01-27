package ru.format.demo.licenseservice.gw.output.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "licenses")
public class LicenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="licenses_id_seq")
    @SequenceGenerator(name = "licenses_id_seq", sequenceName = "licenses_id_seq", allocationSize = 1)
    @Column(name = "license_id")
    private Long id;
    private String description;
    private Long organizationId;
    private String productName;
    private String licenseType;
}
