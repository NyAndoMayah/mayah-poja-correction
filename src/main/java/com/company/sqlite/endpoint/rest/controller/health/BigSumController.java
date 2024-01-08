package com.company.sqlite.endpoint.rest.controller.health;

import com.company.sqlite.repository.DummyRepository;
import com.company.sqlite.repository.DummyUuidRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

import static com.company.sqlite.endpoint.rest.controller.health.PingController.KO;

@RestController
@AllArgsConstructor
public class BigSumController {

  DummyRepository dummyRepository;
  DummyUuidRepository dummyUuidRepository;

  @GetMapping("/big-sum")
  public ResponseEntity<String> calculateBigSum(
          @RequestParam("a") String paramA,
          @RequestParam("b") String paramB
  ) {
    try {
      BigInteger a = new BigInteger(paramA);
      BigInteger b = new BigInteger(paramB);
      BigInteger result = a.add(b);
      return ResponseEntity.ok("The sum is : " + result.toString());
    } catch (NumberFormatException e) {
      return KO;
    }
  }
}
