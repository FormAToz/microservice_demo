package ru.format.demo.licenseservice.controller.dto;

public record LicenseRequest(
        Long id,
        String description,
        String productName,
        String licenseType) {

}
