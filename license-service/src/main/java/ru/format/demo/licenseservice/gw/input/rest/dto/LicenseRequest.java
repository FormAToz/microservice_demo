package ru.format.demo.licenseservice.gw.input.rest.dto;

public record LicenseRequest(
        Long id,
        String description,
        String productName,
        String licenseType) {

}
