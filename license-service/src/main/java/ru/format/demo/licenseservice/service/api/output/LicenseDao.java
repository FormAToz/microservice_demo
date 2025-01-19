package ru.format.demo.licenseservice.service.api.output;

import ru.format.demo.licenseservice.model.License;

public interface LicenseDao {

    License createLicense(License license);

    License getLicense(Long licenseId, Long organizationId);

    License updateLicense(License license, Long organizationId);

    int deleteLicense(Long licenseId, Long organizationId);
}
