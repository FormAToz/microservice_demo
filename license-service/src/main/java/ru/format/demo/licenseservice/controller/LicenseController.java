package ru.format.demo.licenseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.format.demo.licenseservice.controller.dto.LicenseRequest;
import ru.format.demo.licenseservice.controller.dto.LicenseResponse;
import ru.format.demo.licenseservice.controller.mapper.LicenseRequestMapper;
import ru.format.demo.licenseservice.controller.mapper.LicenseResponseMapper;
import ru.format.demo.licenseservice.service.api.LicenseService;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService licenseService;

    @PostMapping
    public ResponseEntity<LicenseResponse> createLicense(@PathVariable Long organizationId, // TODO Validation
                                                         @RequestBody LicenseRequest licenseRequest,
                                                         @RequestHeader(value = "Accept-Language", required = false)
                                                         Locale locale) {
        log.info("[POST] createLicense(): organizationId={}, licenseRequest={}, locale={}",
                organizationId, licenseRequest, locale);

        var license = LicenseRequestMapper.INSTANCE.toModel(licenseRequest, organizationId);
        var licenseResponse = LicenseResponseMapper.INSTANCE.toResponse(
                licenseService.createLicense(license, locale));
        return ResponseEntity.ok(licenseResponse);
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<LicenseResponse> getLicense(@PathVariable Long organizationId,
                                                      @PathVariable Long licenseId,
                                                      @RequestHeader(value = "Accept-Language", required = false)
                                                      Locale locale) {
        log.info("[GET] getLicense(): organizationId={}, licenseId={}, locale={}", organizationId, licenseId, locale);

        var license = licenseService.getLicense(licenseId, organizationId, locale);
        var licenseRequest = LicenseRequestMapper.INSTANCE.toRequest(license);
        var licenseResponse = LicenseResponseMapper.INSTANCE.toResponse(license);
        licenseResponse.add(
                linkTo(methodOn(LicenseController.class)
                        .getLicense(organizationId, licenseResponse.getId(), null))
                        .withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .updateLicense(organizationId, licenseRequest, null))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(organizationId, licenseRequest, null))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class)
                        .deleteLicense(organizationId, license.getId(), null))
                        .withRel("deleteLicense"));
        return ResponseEntity.ok(licenseResponse);
    }

    @PutMapping
    public ResponseEntity<LicenseResponse> updateLicense(@PathVariable Long organizationId,
                                                @RequestBody LicenseRequest licenseRequest,
                                                @RequestHeader(value = "Accept-Language", required = false)
                                                Locale locale) {
        var license = LicenseRequestMapper.INSTANCE.toModel(licenseRequest, organizationId);
        var licenseResponse = LicenseResponseMapper.INSTANCE.toResponse(
                licenseService.updateLicense(license, organizationId, locale));
        return ResponseEntity.ok(licenseResponse);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable Long organizationId,
                                                @PathVariable Long licenseId,
                                                @RequestHeader(value = "Accept-Language", required = false)
                                                Locale locale) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }
}
