package ru.format.demo.organizationservice.gw.output.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.format.demo.organizationservice.gw.input.rest.UserContext;
import ru.format.demo.organizationservice.gw.output.event.model.Action;
import ru.format.demo.organizationservice.gw.output.event.model.OrganizationChange;

@AllArgsConstructor
@Component
@Slf4j
public class OrganizationPublisher {

    private final StreamBridge streamBridge;

    public void publishOrganizationChange(Action action, Long organizationId) {
        log.info("Sending Kafka message {} for organization ID: {}", action, organizationId);

        OrganizationChange organizationChange = new OrganizationChange(
                OrganizationChange.class.getTypeName(),
                action.name(),
                organizationId,
                UserContext.getCorrelationId()
        );

        streamBridge.send(
                "output-org-0",
                MessageBuilder.withPayload(organizationChange).build());
    }
}
