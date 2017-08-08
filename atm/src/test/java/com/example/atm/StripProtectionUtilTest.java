package com.example.atm;

import com.example.atm.api.StripProtectionUtil;
import org.apache.cxf.helpers.IOUtils;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

public class StripProtectionUtilTest {

  @Test
  public void testStripProtection() throws Exception {
    String jsonWithProtection = ")]}',\n" +
      "[{\"address\":{\"street\":\"Beverhof\",\"housenumber\":\"37B\",\"postalcode\":\"1941 EC\",\"city\":\"Beverwijk\",\"geoLocation\":";

    // JStylerObj.StringProtector

    Reader initialReader = StripProtectionUtil.stripProtection(new StringReader(jsonWithProtection));
    String result = IOUtils.toString(initialReader);
    System.out.println("without proteciton" +  result);

    Assert.assertThat(result, not( containsString( ")]}',\n" ) ));



  }

}
