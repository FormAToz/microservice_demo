package ru.format.demo.licenseservice.gw.output.client;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import ru.format.demo.licenseservice.model.Organization;
import ru.format.demo.licenseservice.service.api.output.OrganizationClient;

import java.util.List;

@AllArgsConstructor
@Component
public class OrganizationDiscoveryClient implements OrganizationClient {

    private final DiscoveryClient discoveryClient;

    @Override
    public Organization getOrganization(Long organizationId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }

        return new RestTemplate()
                .exchange(
                        "%s/v1/organization/%s".formatted(instances.get(0).getUri().toString(), organizationId),
                        HttpMethod.GET,
                        null,
                        Organization.class,
                        organizationId)
                .getBody();
    }
}
