package ru.format.demo.licenseservice.service.api;

import ru.format.demo.licenseservice.model.License;

import java.util.Locale;

public interface LicenseService {

    License createLicense(License license, Locale locale);

    License getLicense(Long licenseId, Long organizationId, Locale locale);

    License updateLicense(License license, Long organizationId, Locale locale);

    String deleteLicense(Long licenseId, Long organizationId, Locale locale);
}
