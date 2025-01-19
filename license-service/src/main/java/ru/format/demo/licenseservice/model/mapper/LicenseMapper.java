package ru.format.demo.licenseservice.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.format.demo.licenseservice.db.entity.LicenseEntity;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseMapper {

    @Mapping(target = "id", ignore = true)
    LicenseEntity toEntity(License license);
}
