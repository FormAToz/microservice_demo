package ru.format.demo.organizationservice.gw.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.format.demo.organizationservice.gw.rest.dto.OrganizationRequest;
import ru.format.demo.organizationservice.gw.rest.dto.OrganizationResponse;
import ru.format.demo.organizationservice.gw.rest.mapper.OrganizationRequestMapper;
import ru.format.demo.organizationservice.gw.rest.mapper.OrganizationResponseMapper;
import ru.format.demo.organizationservice.service.input.OrganizationService;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationRequestMapper organizationRequestMapper;
    private final OrganizationResponseMapper organizationResponseMapper;

    @PostMapping
    public ResponseEntity<OrganizationResponse> saveOrganization(
            @RequestBody
            OrganizationRequest organizationRequest,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("saveOrganization(): organizationRequest={}, locale={}", organizationRequest, locale);

        var organization = organizationRequestMapper.toModel(organizationRequest);
        var response = organizationResponseMapper.toDto(organizationService.create(organization, locale));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponse> getOrganization(
            @PathVariable
            Long organizationId,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("getOrganization(): organizationId={}, locale={}", organizationId, locale);

        var response = organizationResponseMapper.toDto(organizationService.get(organizationId, locale));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponse> updateOrganization(
            @PathVariable
            Long organizationId,
            @RequestBody
            OrganizationRequest organizationRequest,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("updateOrganization(): organizationId={}, organizationRequest={}, locale={}",
                organizationId, organizationRequest, locale);

        var organization = organizationRequestMapper.toModel(organizationId, organizationRequest);
        var response = organizationResponseMapper.toDto(organizationService.update(organization, locale));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<String> deleteOrganization(
            @PathVariable
            Long organizationId,
            @RequestHeader(value = "Accept-Language", required = false)
            Locale locale
    ) {
        log.info("deleteOrganization(): organizationId={}, locale={}", organizationId, locale);

        return ResponseEntity.ok(organizationService.delete(organizationId, locale));
    }
}
