package ru.format.demo.licenseservice.service.api.output;

import ru.format.demo.licenseservice.model.Organization;

public interface OrganizationClient {

    Organization getOrganization(Long organizationId);
}
