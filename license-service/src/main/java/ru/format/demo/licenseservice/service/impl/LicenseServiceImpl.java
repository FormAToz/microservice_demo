package ru.format.demo.licenseservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.format.demo.licenseservice.model.License;
import ru.format.demo.licenseservice.service.api.LicenseService;

import java.util.Locale;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class LicenseServiceImpl implements LicenseService {

    private final MessageSource messages;

    @Override
    public License getLicense(String licenseId, String organizationId) {
        var license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Fstock");
        license.setLicenseType("full");
        return license;
    }

    @Override
    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage =
                    String.format(messages.getMessage("license.create.message", null, locale), license);
        }
        return responseMessage;
    }

    @Override
    public String updateLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage =
                    String.format(messages.getMessage("license.update.message", null, locale), license);
        }
        return responseMessage;
    }

    @Override
    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        return String.format(messages.getMessage("license.delete.message", null, locale),
                licenseId, organizationId);
    }
}