package ru.format.demo.organizationservice.gw.output.event.model;

public record OrganizationChange(

        String type,
        String action,
        Long organizationId,
        String correlationId
) {
}
