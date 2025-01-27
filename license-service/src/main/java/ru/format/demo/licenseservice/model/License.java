package ru.format.demo.licenseservice.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class License {

    protected Long id;
    protected String description;
    protected Long organizationId;
    protected String productName;
    protected String licenseType;
    protected String comment;
}
