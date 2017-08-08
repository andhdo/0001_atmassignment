package com.example.atm;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmRoutesTest extends CamelTestSupport {
  @Test
  public void should_home_route_active() throws Exception {

    assertTrue(context().getRouteStatus("direct:home").isStarted());
  }
}
