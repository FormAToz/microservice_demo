package ru.format.demo.licenseservice.db.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.format.demo.licenseservice.db.entity.LicenseEntity;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseEntityMapper {

    @Mapping(target = "comment", ignore = true)
    License toModel(LicenseEntity licenseEntity);
}
