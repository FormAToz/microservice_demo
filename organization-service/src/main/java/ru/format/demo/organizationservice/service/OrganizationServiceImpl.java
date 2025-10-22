package ru.format.demo.organizationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.format.demo.organizationservice.gw.output.event.OrganizationPublisher;
import ru.format.demo.organizationservice.gw.output.event.model.Action;
import ru.format.demo.organizationservice.model.Organization;
import ru.format.demo.organizationservice.service.input.OrganizationService;
import ru.format.demo.organizationservice.service.output.OrganizationDao;

import java.util.Locale;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final MessageSource messageSource;
    private final OrganizationDao organizationDao;
    private final OrganizationPublisher orgPublisher;

    @Override
    public Organization create(Organization organization, Locale locale) {
        Organization org = Optional.ofNullable(organizationDao.create(organization))
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("organization.create.error.message", null, locale)
                                .formatted(organization.getName())
                ));
        orgPublisher.publishOrganizationChange(Action.CREATED, org.getId());

        return org;
    }

    @Override
    public Organization get(Long organizationId, Locale locale) {
        return Optional.ofNullable(organizationDao.get(organizationId))
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("organization.get.error.message", null, locale)
                                .formatted(organizationId)
                ));
    }

    @Override
    public Organization update(Organization organization, Locale locale) {
        return Optional.ofNullable(organizationDao.update(organization))
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("organization.update.error.message", null, locale)
                                .formatted(organization.getId())
                ));
    }

    @Override
    public String delete(Long organizationId, Locale locale) {
        if (organizationDao.delete(organizationId) == 0) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("organization.delete.error.message", null, locale)
                            .formatted(organizationId)
            );
        }
        return messageSource.getMessage("organization.delete.message", null, locale)
                .formatted(organizationId);
    }
}
