package ru.format.demo.organizationservice.gw.rest.mapper;

import org.mapstruct.Mapper;
import ru.format.demo.organizationservice.gw.rest.dto.OrganizationResponse;
import ru.format.demo.organizationservice.model.Organization;

@Mapper
public interface OrganizationResponseMapper {

    OrganizationResponse toDto(Organization organization);
}
