package ru.format.demo.licenseservice.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@RequiredArgsConstructor
@Getter
@ToString
public class LicenseResponse extends RepresentationModel<LicenseResponse> {

    private final Long id;
    private final String description;
    private final Long organizationId;
    private final String productName;
    private final String licenseType;
    private final String comment;
}
