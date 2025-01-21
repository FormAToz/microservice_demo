package ru.format.demo.organizationservice.gw.rest.dto;

public record OrganizationRequest(
        String name,
        String contactName,
        String contactEmail,
        String contactPhone
) {
}
