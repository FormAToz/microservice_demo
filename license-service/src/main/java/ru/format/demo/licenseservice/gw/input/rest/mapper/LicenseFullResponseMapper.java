package ru.format.demo.licenseservice.gw.input.rest.mapper;

import org.mapstruct.Mapper;
import ru.format.demo.licenseservice.gw.input.rest.dto.LicenseFullResponse;
import ru.format.demo.licenseservice.model.LicenseFull;

@Mapper
public interface LicenseFullResponseMapper {

    LicenseFullResponse toResponse(LicenseFull licenseFull);
}
