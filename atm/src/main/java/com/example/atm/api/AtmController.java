package com.example.atm.api;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/api")
public class AtmController {


  // FluentProducerTemplate producerTemplate (seems to be disabled yet for camel-spring-boot plugin);
  // <code>
  //  producerTemplate
  //    .withBody("via Spring Boot Rest Controller")
  //    .to("direct:home")
  //    .request();
  // </code>

  @Autowired
  private ProducerTemplate producerTemplate;

  @Autowired
  private CamelContext camelContext;

  /**
   * Let test camel behavior in logs
   */
  @RequestMapping(value="/api")
  public ResponseEntity home() {
    producerTemplate.sendBody("direct:home", "via Spring Boot Rest Controller");
    return new ResponseEntity<String>("OK", HttpStatus.OK);
  }

  /**
   * To test the json but with an internal file
   * @return
   */
  @RequestMapping(value="/api/file")
  public ResponseEntity atmListFile() {
    // producerTemplate.setDefaultEndpointUri("direct:atmList");
    producerTemplate.sendBody("direct:atmListFile", "");
    //producerTemplate.setDefaultEndpointUri("direct:atmList");
    // producerTemplate.sendBody("file:src/main/resources/static/sample_response.json?noop=true");
    return new ResponseEntity<String>("OK", HttpStatus.OK);
  }


  /**
   * To invoke the original api
   * @return
   */
  @RequestMapping(value="/api/atms")
  public ResponseEntity<String> atmList() {
    final Exchange requestExchange = ExchangeBuilder.anExchange(camelContext).withBody("").build();
    final Exchange responseExchange = producerTemplate.send("direct:atmListFile", requestExchange);
    final String responseBody = responseExchange.getOut().getBody(String.class);
    System.out.print("responseBodyAtmList=" + responseBody);
//    final int responseCode = responseExchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class).intValue();
    final int responseCode = 200;

    return ResponseEntity.status(responseCode).body(responseBody);
  }


}
