package com.company.sqlite.endpoint.rest.controller.health;

import static com.company.sqlite.endpoint.rest.controller.health.PingController.KO;
import static com.company.sqlite.endpoint.rest.controller.health.PingController.OK;
import static java.util.UUID.randomUUID;

import com.company.sqlite.PojaGenerated;
import com.company.sqlite.endpoint.event.EventProducer;
import com.company.sqlite.endpoint.event.gen.UuidCreated;
import com.company.sqlite.repository.DummyUuidRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@AllArgsConstructor
public class HealthEventController {

  DummyUuidRepository dummyUuidRepository;
  EventProducer eventProducer;

  @GetMapping(value = "/health/event")
  public ResponseEntity<String> random_uuid_is_fired_then_created() throws InterruptedException {
    var randomUuid = randomUUID().toString();
    var event = new UuidCreated().toBuilder().uuid(randomUuid).build();

    eventProducer.accept(List.of(event));

    Thread.sleep(20_000);
    return dummyUuidRepository.findById(randomUuid).map(dummyUuid -> OK).orElse(KO);
  }
}
