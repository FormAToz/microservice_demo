package ru.format.demo.licenseservice.service.api.input;

import org.springframework.lang.NonNull;
import ru.format.demo.licenseservice.model.License;
import ru.format.demo.licenseservice.model.LicenseFull;

import java.util.Locale;

public interface LicenseService {

    License createLicense(License license, Locale locale);

    @NonNull
    License getLicense(Long organizationId, Long licenseId, Locale locale);

    @NonNull
    LicenseFull getLicense(Long organizationId, Long licenseId, String clientType, Locale locale);

    License updateLicense(License license, Long organizationId, Locale locale);

    String deleteLicense(Long licenseId, Long organizationId, Locale locale);
}
