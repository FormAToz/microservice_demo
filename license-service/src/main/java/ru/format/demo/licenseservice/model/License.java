package ru.format.demo.licenseservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class License {

    private Long id;
    private String description;
    private Long organizationId;
    private String productName;
    private String licenseType;
    private String comment;
}
