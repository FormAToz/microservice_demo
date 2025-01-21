package ru.format.demo.organizationservice.gw.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.format.demo.organizationservice.gw.rest.dto.OrganizationRequest;
import ru.format.demo.organizationservice.model.Organization;

@Mapper
public interface OrganizationRequestMapper {

    @Mapping(target = "id", ignore = true)
    Organization toModel(OrganizationRequest organizationRequest);

    @Mapping(target = "id", source = "organizationId")
    Organization toModel(Long organizationId, OrganizationRequest organizationRequest);
}
