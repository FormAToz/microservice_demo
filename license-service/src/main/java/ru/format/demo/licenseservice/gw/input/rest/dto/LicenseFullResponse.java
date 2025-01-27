package ru.format.demo.licenseservice.gw.input.rest.dto;

public record LicenseFullResponse(
        Long id,
        String description,
        String productName,
        String licenseType,
        String comment,
        Long organizationId,
        String organizationName,
        String contactName,
        String contactPhone,
        String contactEmail
) {
}
