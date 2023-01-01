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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
