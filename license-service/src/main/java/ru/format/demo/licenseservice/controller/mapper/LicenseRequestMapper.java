package ru.format.demo.licenseservice.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.format.demo.licenseservice.controller.dto.LicenseRequest;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseRequestMapper {

    LicenseRequestMapper INSTANCE = Mappers.getMapper(LicenseRequestMapper.class);

    @Mapping(target = "comment", ignore = true)
    License toModel(LicenseRequest license, Long organizationId);

    LicenseRequest toRequest(License license);
}
