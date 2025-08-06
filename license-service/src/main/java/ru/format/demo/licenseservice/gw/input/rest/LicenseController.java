package ru.format.demo.licenseservice.gw.input.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.format.demo.licenseservice.gw.input.rest.dto.LicenseFullResponse;
import ru.format.demo.licenseservice.gw.input.rest.dto.LicenseRequest;
import ru.format.demo.licenseservice.gw.input.rest.dto.LicenseResponse;
import ru.format.demo.licenseservice.gw.input.rest.filter.UserContextHolder;
import ru.format.demo.licenseservice.gw.input.rest.mapper.LicenseFullResponseMapper;
import ru.format.demo.licenseservice.gw.input.rest.mapper.LicenseRequestMapper;
import ru.format.demo.licenseservice.gw.input.rest.mapper.LicenseResponseMapper;
import ru.format.demo.licenseservice.service.api.input.LicenseService;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService licenseService;
    private final LicenseRequestMapper licenseRequestMapper;
    private final LicenseResponseMapper licenseResponseMapper;
    private final LicenseFullResponseMapper licenseFullResponseMapper;

    @PostMapping
    public ResponseEntity<LicenseResponse> createLicense(
            @PathVariable
            Long organizationId,
            @RequestBody
            LicenseRequest licenseRequest,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("createLicense(): organizationId={}, licenseRequest={}, locale={}",
                organizationId, licenseRequest, locale);

        var license = licenseRequestMapper.toModel(licenseRequest, organizationId);
        var licenseResponse = licenseResponseMapper.toResponse(
                licenseService.createLicense(license, locale));
        return ResponseEntity.ok(licenseResponse);
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<LicenseResponse> getLicense(
            @PathVariable
            Long organizationId,
            @PathVariable
            Long licenseId,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("getLicense(): correlationId={}, organizationId={}, licenseId={}, locale={}",
                UserContextHolder.getContext().getCorrelationId(), organizationId, licenseId, locale);

        var license = licenseService.getLicense(organizationId, licenseId, locale);
        var licenseRequest = licenseRequestMapper.toRequest(license);
        var licenseResponse = licenseResponseMapper.toResponse(license);
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
    public ResponseEntity<LicenseResponse> updateLicense(
            @PathVariable
            Long organizationId,
            @RequestBody
            LicenseRequest licenseRequest,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("updateLicense(): organizationId={}, licenseRequest={}, locale={}",
                organizationId, licenseRequest, locale);

        var license = licenseRequestMapper.toModel(licenseRequest, organizationId);
        var licenseResponse = licenseResponseMapper.toResponse(
                licenseService.updateLicense(license, organizationId, locale));
        return ResponseEntity.ok(licenseResponse);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable
            Long organizationId,
            @PathVariable
            Long licenseId,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("deleteLicense(): organizationId={}, licenseId={}, locale={}", organizationId, licenseId, locale);

        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }

    @GetMapping("/{licenseId}/{clientType}")
    public ResponseEntity<LicenseFullResponse> getLicenseWithClient(
            @PathVariable
            Long organizationId,
            @PathVariable
            Long licenseId,
            @PathVariable
            String clientType,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("getLicenseWithClient(): organizationId={}, licenseId={}, clientType={}, locale={}",
                organizationId, licenseId, clientType, locale);

        var license = licenseService.getLicense(organizationId, licenseId, clientType, locale);
        return ResponseEntity.ok(licenseFullResponseMapper.toResponse(license));
    }
}
