package ru.format.demo.licenseservice.gw.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.format.demo.licenseservice.gw.dao.entity.LicenseEntity;

import javax.transaction.Transactional;

@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {

    LicenseEntity getByIdAndOrganizationId(Long licenseId, Long organizationId);

    @Transactional
    int deleteByIdAndOrganizationId(Long licenseId, Long organizationId);
}
