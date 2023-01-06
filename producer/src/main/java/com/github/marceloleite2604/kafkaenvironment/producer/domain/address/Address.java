package com.github.marceloleite2604.kafkaenvironment.producer.domain.address;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

/* It seems that Jackson has a bug on its implicit constructor detection. It does not recognize them when they only
have a single parameter ("location", in this case). As a workaround, we can use Lombok "@Jacksonized" to
annotate the builder class with @JsonDeserialize annotation. */
@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Address {
  Location location;
}
