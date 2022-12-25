package ru.format.demo.licenseservice.service.api;

import ru.format.demo.licenseservice.model.License;

import java.util.Locale;

public interface LicenseService {

    String createLicense(License license, String organizationId, Locale locale);

    License getLicense(String licenseId, String organizationId);

    String updateLicense(License license, String organizationId, Locale locale);

    String deleteLicense(String licenseId, String organizationId, Locale locale);
}