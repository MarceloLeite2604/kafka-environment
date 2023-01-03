package com.github.marceloleite2604.kafkaenvironment.producer.reader.address;

import java.util.Iterator;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.AddressesRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.reader.ItemBuffer;
import com.github.marceloleite2604.kafkaenvironment.producer.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressBuffer extends ItemBuffer<Address> {

  private final AddressesRetrievalJobProperties addressRetrievalJobProperties;

  private final AddressService addressService;

  @Override
  protected Iterator<Address> retrieveIterator() {
    return addressService.retrieve(addressRetrievalJobProperties.getBufferedItems())
        .iterator();
  }
}
