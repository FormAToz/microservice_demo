package ru.format.demo.licenseservice.gw.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.format.demo.licenseservice.gw.rest.dto.LicenseRequest;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseRequestMapper {

    @Mapping(target = "comment", ignore = true)
    License toModel(LicenseRequest license, Long organizationId);

    LicenseRequest toRequest(License license);
}
