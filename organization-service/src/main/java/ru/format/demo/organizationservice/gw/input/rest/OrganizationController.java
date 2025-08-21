package ru.format.demo.organizationservice.gw.input.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.format.demo.organizationservice.gw.input.rest.dto.OrganizationRequest;
import ru.format.demo.organizationservice.gw.input.rest.dto.OrganizationResponse;
import ru.format.demo.organizationservice.gw.input.rest.mapper.OrganizationRequestMapper;
import ru.format.demo.organizationservice.gw.input.rest.mapper.OrganizationResponseMapper;
import ru.format.demo.organizationservice.service.input.OrganizationService;

import javax.annotation.security.RolesAllowed;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationRequestMapper organizationRequestMapper;
    private final OrganizationResponseMapper organizationResponseMapper;

    @RolesAllowed({"ADMIN", "USER"})
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

    @RolesAllowed({"ADMIN", "USER"})
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

    @RolesAllowed({"ADMIN", "USER"})
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

    @RolesAllowed("ADMIN")
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
