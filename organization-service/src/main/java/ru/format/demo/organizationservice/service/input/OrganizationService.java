package ru.format.demo.organizationservice.service.input;

import ru.format.demo.organizationservice.model.Organization;

import java.util.Locale;

public interface OrganizationService {

    Organization create(Organization organization, Locale locale);

    Organization get(Long organizationId, Locale locale);

    Organization update(Organization organization, Locale locale);

    String delete(Long organizationId, Locale locale);
}
