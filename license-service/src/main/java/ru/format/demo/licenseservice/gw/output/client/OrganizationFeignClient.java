package ru.format.demo.licenseservice.gw.output.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.format.demo.licenseservice.model.Organization;
import ru.format.demo.licenseservice.service.api.output.OrganizationClient;

@FeignClient("organization-service")
public interface OrganizationFeignClient extends OrganizationClient {

    @GetMapping(value = "/v1/organization/{organizationId}", consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") Long organizationId);
}
