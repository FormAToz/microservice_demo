package ru.format.demo.licenseservice.controller.mapper;

import org.mapstruct.Mapper;
import ru.format.demo.licenseservice.controller.dto.LicenseResponse;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseResponseMapper {

    LicenseResponse toResponse(License license);
}