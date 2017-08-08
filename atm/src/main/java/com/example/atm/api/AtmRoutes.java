package com.example.atm.api;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.helpers.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class AtmRoutes extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    // to verify camel integration

    from("direct:home")
      .log("Camel body: ${body}")
      ;

    from("direct:atmList")
      // .log("original ${body}")
      .transform()
        .jsonpath("$[?(@.address.city=='Amsterdam')]")  //$  //$[?(@.city == 'Amsterdam')]
      // .log("filteredData [result:  ${body} ]")
    ;

    // to verify routes
    from("direct:atmListFile")
      .process(new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          Path sampleFile = Paths.get("src/main/resources/static/sample_response.json");
          File file = sampleFile.toFile();
          if (!file.exists()) {
            throw new FileNotFoundException(String.format("File %s not found on %s", sampleFile, sampleFile.getRoot()));
          }
          exchange.getIn().setBody(sampleFile.toFile());
        }
      })
      .streamCaching()
      // .log("original [result:  ${body} ]")
      .to("direct:atmList")
    ;

    /*
      .convertBodyTo(java.lang.String.class)
      .filter().jsonpath("$") //$[?(@.city == 'Amsterdam')]
      .log("fileFilteredData ${body}")
    ;
    */

    // eai: to call service and filter

    from("direct:atmListUri")
      .to("https://www.ing.nl/api/locator/atms/") // cxfrs:// ;; ?bindingStyle=SimpleConsumer
      .process(new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          String jsonWithProtection = exchange.getIn().getBody(String.class);

          Reader initialReader = StripProtectionUtil.stripProtection(new StringReader(jsonWithProtection));
          String targetString = IOUtils.toString(initialReader);

          exchange.getOut().setBody(targetString);

        }
      })
      .streamCaching()
      .to("direct:atmList")
      ;


      /*
      // .log("Orginal data (atmList): ${body}")
      .process(new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          Object body = exchange.getIn().getBody();
          System.out.println("source=" + body);
          exchange.getOut().setBody(body);
        }
      })
      .filter().jsonpath("$") //$[?(@.city == 'Amsterdam')]
      // .convertBodyTo(String.class)
      .process(new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          Object body = exchange.getIn().getBody();
          System.out.println(body);
          exchange.getOut().setBody(body);
        }
      })
      .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
      .log("filteredData ${body}");

      */

    //

  }

}
