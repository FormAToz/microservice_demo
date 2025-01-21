package ru.format.demo.organizationservice.service.output;

import org.springframework.lang.Nullable;
import ru.format.demo.organizationservice.model.Organization;

public interface OrganizationDao {

    Organization create(Organization organization);

    Organization update(Organization organization);

    @Nullable
    Organization get(Long id);

    int delete(Long id);
}
