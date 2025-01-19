package ru.format.demo.licenseservice.gw.dao.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.format.demo.licenseservice.gw.dao.entity.LicenseEntity;
import ru.format.demo.licenseservice.model.License;

@Mapper
public interface LicenseEntityMapper {

    @Mapping(target = "comment", ignore = true)
    License toModel(LicenseEntity licenseEntity);

    @Mapping(target = "id", ignore = true)
    LicenseEntity toEntity(License license);
}
