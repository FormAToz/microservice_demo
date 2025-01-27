package ru.format.demo.licenseservice.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Value
public class LicenseFull extends License {

    String organizationName;
    String contactName;
    String contactPhone;
    String contactEmail;
}
