package ru.format.demo.licenseservice.db.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.format.demo.licenseservice.db.entity.LicenseEntity;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseEntityMapper {

    LicenseEntityMapper INSTANCE = Mappers.getMapper(LicenseEntityMapper.class);

    @Mapping(target = "comment", ignore = true)
    License toModel(LicenseEntity licenseEntity);
}
