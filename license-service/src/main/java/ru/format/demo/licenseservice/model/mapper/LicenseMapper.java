package ru.format.demo.licenseservice.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.format.demo.licenseservice.db.entity.LicenseEntity;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseMapper {

    LicenseMapper INSTANCE = Mappers.getMapper(LicenseMapper.class);

    @Mapping(target = "id", ignore = true)
    LicenseEntity toEntity(License license);
}
