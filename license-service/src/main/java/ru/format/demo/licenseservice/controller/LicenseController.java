package ru.format.demo.licenseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.format.demo.licenseservice.controller.dto.LicenseRequest;
import ru.format.demo.licenseservice.controller.dto.LicenseResponse;
import ru.format.demo.licenseservice.controller.mapper.LicenseRequestMapper;
import ru.format.demo.licenseservice.controller.mapper.LicenseResponseMapper;
import ru.format.demo.licenseservice.service.api.LicenseService;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService licenseService;

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable String organizationId,
                                                @RequestBody LicenseRequest licenseRequest,
                                                @RequestHeader(value = "Accept-Language", required = false)
                                                        Locale locale) {
        var license = LicenseRequestMapper.INSTANCE.toModel(licenseRequest);
        return ResponseEntity.ok(licenseService.createLicense(license, organizationId, locale));
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<LicenseResponse> getLicense(@PathVariable String organizationId,
                                                      @PathVariable String licenseId) {
        var license = licenseService.getLicense(licenseId, organizationId);
        var licenseRequest = LicenseRequestMapper.INSTANCE.toRequest(license);
        var licenseResponse =
                LicenseResponseMapper.INSTANCE.toResponse(license);
        licenseResponse.add(
                linkTo(methodOn(LicenseController.class)
                        .getLicense(organizationId, licenseResponse.getLicenseId()))
                        .withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .updateLicense(organizationId, licenseRequest, null))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(organizationId, licenseRequest, null))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class)
                        .deleteLicense(organizationId, license.getLicenseId(), null))
                        .withRel("deleteLicense"));
        return ResponseEntity.ok(licenseResponse);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable String organizationId,
                                                @RequestBody LicenseRequest licenseRequest,
                                                @RequestHeader(value = "Accept-Language", required = false)
                                                Locale locale) {
        var license = LicenseRequestMapper.INSTANCE.toModel(licenseRequest);
        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId, locale));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable String organizationId,
                                                @PathVariable String licenseId,
                                                @RequestHeader(value = "Accept-Language", required = false)
                                                            Locale locale) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }
}