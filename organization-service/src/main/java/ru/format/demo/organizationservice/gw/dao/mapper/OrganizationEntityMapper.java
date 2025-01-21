package ru.format.demo.organizationservice.gw.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.format.demo.organizationservice.gw.dao.entity.OrganizationEntity;
import ru.format.demo.organizationservice.model.Organization;

@Mapper
public interface OrganizationEntityMapper {

    @Mapping(target = "id", ignore = true)
    OrganizationEntity toEntity(Organization organization);

    Organization toModel(OrganizationEntity organizationEntity);
}
