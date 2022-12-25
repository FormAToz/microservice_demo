package ru.format.demo.licenseservice.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.format.demo.licenseservice.controller.dto.LicenseResponse;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseResponseMapper {

    LicenseResponseMapper INSTANCE = Mappers.getMapper(LicenseResponseMapper.class);

    LicenseResponse toResponse(License license);
}