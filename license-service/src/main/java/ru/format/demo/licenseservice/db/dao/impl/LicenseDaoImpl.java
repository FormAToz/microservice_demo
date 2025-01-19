package ru.format.demo.licenseservice.db.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.format.demo.licenseservice.db.dao.api.LicenseDao;
import ru.format.demo.licenseservice.db.entity.mapper.LicenseEntityMapper;
import ru.format.demo.licenseservice.db.repository.LicenseRepository;
import ru.format.demo.licenseservice.model.License;
import ru.format.demo.licenseservice.model.mapper.LicenseMapper;

@RequiredArgsConstructor
@Component
public class LicenseDaoImpl implements LicenseDao {

    private final LicenseRepository licenseRepository;
    private final LicenseMapper licenseMapper;
    private final LicenseEntityMapper licenseEntityMapper;

    @Override
    public License createLicense(License license) {
        var licenseEntity = licenseMapper.toEntity(license);
        return licenseEntityMapper.toModel(licenseRepository.save(licenseEntity));
    }

    @Override
    public License getLicense(Long licenseId, Long organizationId) {
        var licenseEntity = licenseRepository.getByIdAndOrganizationId(licenseId, organizationId);
        return licenseEntityMapper.toModel(licenseEntity);
    }

    @Override
    public License updateLicense(License license, Long organizationId) {
        var licenseEntity = licenseRepository.getByIdAndOrganizationId(license.getId(), organizationId);
        if (licenseEntity != null) {
            licenseEntity.setDescription(license.getDescription());
            licenseEntity.setProductName(license.getProductName());
            licenseEntity.setLicenseType(license.getLicenseType());
            return licenseEntityMapper.toModel(licenseRepository.save(licenseEntity));
        } else {
            return null;
        }
    }

    @Override
    public int deleteLicense(Long licenseId, Long organizationId) {
        return licenseRepository.deleteByIdAndOrganizationId(licenseId, organizationId);
    }
}
