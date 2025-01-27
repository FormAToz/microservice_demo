package ru.format.demo.licenseservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Organization {

    private Long id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
}
