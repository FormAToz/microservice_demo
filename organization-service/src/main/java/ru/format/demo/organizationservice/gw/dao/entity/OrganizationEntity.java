package ru.format.demo.organizationservice.gw.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "organizations")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="organizations_id_seq")
    @SequenceGenerator(name = "organizations_id_seq", sequenceName = "organizations_id_seq", allocationSize = 1)
    @Column(name = "organization_id")
    private Long id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
}
