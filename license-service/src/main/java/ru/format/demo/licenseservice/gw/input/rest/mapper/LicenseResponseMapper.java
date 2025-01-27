package ru.format.demo.licenseservice.gw.input.rest.mapper;

import org.mapstruct.Mapper;
import ru.format.demo.licenseservice.gw.input.rest.dto.LicenseResponse;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseResponseMapper {

    LicenseResponse toResponse(License license);
}