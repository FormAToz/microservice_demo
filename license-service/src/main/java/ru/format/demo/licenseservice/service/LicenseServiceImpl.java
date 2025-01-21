package ru.format.demo.licenseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.format.demo.licenseservice.config.ServiceConfig;
import ru.format.demo.licenseservice.service.api.output.LicenseDao;
import ru.format.demo.licenseservice.model.License;
import ru.format.demo.licenseservice.service.api.input.LicenseService;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class LicenseServiceImpl implements LicenseService {

    private final MessageSource messageSource;
    private final LicenseDao licenseDao;
    private final ServiceConfig serviceConfig;

    @Override
    public License createLicense(License license, Locale locale) {
        var savedLicense = licenseDao.createLicense(license);
        if (savedLicense == null) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("license.create.error.message", null, locale)
                            .formatted(license.getOrganizationId(), license));
        } else {
            savedLicense.setComment(serviceConfig.getProperty());
            return savedLicense;
        }
    }

    @Override
    public License getLicense(Long licenseId, Long organizationId, Locale locale) {
        var license = licenseDao.getLicense(licenseId, organizationId);
        if (license == null) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("license.get.error.message", null, locale)
                            .formatted(licenseId, organizationId));
        }
        license.setComment(serviceConfig.getProperty());
        return license;
    }

    @Override
    public License updateLicense(License license, Long organizationId, Locale locale) {
        var updatedLicense = licenseDao.updateLicense(license, organizationId);
        if (updatedLicense == null) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("license.update.error.message", null, locale)
                            .formatted(license.getId(), organizationId));
        }
        updatedLicense.setComment(serviceConfig.getProperty());
        return updatedLicense;
    }

    @Override
    public String deleteLicense(Long licenseId, Long organizationId, Locale locale) {
        if (licenseDao.deleteLicense(licenseId, organizationId) == 0) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("license.delete.error.message", null, locale)
                            .formatted(licenseId, organizationId));
        }
        return messageSource.getMessage("license.delete.message", null, locale)
                .formatted(licenseId, organizationId);
    }
}
