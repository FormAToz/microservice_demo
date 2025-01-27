package ru.format.demo.licenseservice.gw.output.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.format.demo.licenseservice.model.Organization;
import ru.format.demo.licenseservice.service.api.output.OrganizationClient;

@AllArgsConstructor
@Component
public class OrganizationRestTemplateClient implements OrganizationClient {

    private final RestTemplate restTemplate;

    @Override
    public Organization getOrganization(Long organizationId) {
        return restTemplate
                .exchange(
                        "http://organization-service/v1/organization/{organizationId}",
                        HttpMethod.GET,
                        null,
                        Organization.class,
                        organizationId)
                .getBody();
    }
}
