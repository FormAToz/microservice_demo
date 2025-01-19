package ru.format.demo.licenseservice.gw.rest.mapper;

import org.mapstruct.Mapper;
import ru.format.demo.licenseservice.gw.rest.dto.LicenseResponse;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseResponseMapper {

    LicenseResponse toResponse(License license);
}