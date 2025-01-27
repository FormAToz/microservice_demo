package ru.format.demo.licenseservice.gw.output;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import ru.format.demo.licenseservice.gw.output.client.OrganizationDiscoveryClient;
import ru.format.demo.licenseservice.gw.output.client.OrganizationFeignClient;
import ru.format.demo.licenseservice.gw.output.client.OrganizationRestTemplateClient;
import ru.format.demo.licenseservice.model.Organization;
import ru.format.demo.licenseservice.service.api.output.OrganizationClient;

@AllArgsConstructor
@Component
@Slf4j
public class OrganizationClientResolver {

    private final OrganizationFeignClient organizationFeignClient;
    private final OrganizationRestTemplateClient organizationRestClient;
    private final OrganizationDiscoveryClient organizationDiscoveryClient;

    public Organization getOrganization(String clientType, Long organizationId) {
        return resolveClient(clientType).getOrganization(organizationId);
    }

    private OrganizationClient resolveClient(String clientType) {
        var defaultClient = organizationRestClient;
        if (StringUtils.isEmpty(clientType)) {
            return defaultClient;
        }
        return switch (clientType) {
            case "feign" -> {
                log.info("I am using the feign client");
                yield organizationFeignClient;
            }
            case "rest" -> {
                log.info("I am using the rest client");
                yield organizationRestClient;
            }
            case "discovery" -> {
                log.info("I am using the discovery client");
                yield organizationDiscoveryClient;
            }
            default -> defaultClient;
        };
    }
}
