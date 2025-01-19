package ru.format.demo.licenseservice.db.entity;

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
    @Column(name = "license_id", nullable = false)
    private Long id;

    private String description;

    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;
}
