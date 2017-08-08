package com.example.atm.api.serviceclient;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("atmResourceClient")
public class AtmResourceClient {

  public List<AtmItem> getItems() {
    return new ArrayList<>();
  }
}
