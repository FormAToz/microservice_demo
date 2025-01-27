package ru.format.demo.organizationservice.gw.output.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.format.demo.organizationservice.gw.output.dao.mapper.OrganizationEntityMapper;
import ru.format.demo.organizationservice.gw.output.dao.repository.OrganizationRepository;
import ru.format.demo.organizationservice.model.Organization;
import ru.format.demo.organizationservice.service.output.OrganizationDao;

@Component
@RequiredArgsConstructor
public class OrganizationDaoImpl implements OrganizationDao {

    private final OrganizationRepository organizationRepository;
    private final OrganizationEntityMapper organizationEntityMapper;

    @Override
    public Organization create(Organization organization) {
        var organizationEntity = organizationEntityMapper.toEntity(organization);
        return organizationEntityMapper.toModel(organizationRepository.save(organizationEntity));
    }

    @Override
    public Organization update(Organization organization) {
        var organizationEntity = organizationRepository.findById(organization.getId()).orElse(null);
        if (organizationEntity != null) {
            organizationEntity.setName(organization.getName());
            organizationEntity.setContactName(organization.getContactName());
            organizationEntity.setContactEmail(organization.getContactEmail());
            organizationEntity.setContactPhone(organization.getContactPhone());
            return organizationEntityMapper.toModel(organizationRepository.save(organizationEntity));
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public Organization get(Long id) {
        var organizationEntity = organizationRepository.findById(id).orElse(null);
        return organizationEntityMapper.toModel(organizationEntity);
    }

    @Override
    public int delete(Long id) {
        try {
            organizationRepository.deleteById(id);
        } catch (DataAccessException e) {
            return 0;
        }
        return 1;
    }
}
