package ru.format.demo.organizationservice.gw.rest.dto;

public record OrganizationResponse(
        Long id,
        String name,
        String contactName,
        String contactEmail,
        String contactPhone
) {
}
