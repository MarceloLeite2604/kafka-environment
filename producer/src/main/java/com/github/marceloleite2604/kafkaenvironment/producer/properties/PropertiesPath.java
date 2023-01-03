package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertiesPath {

  private static final String BASE_PATH = "producer";

  public static final String KAFKA = BASE_PATH + ".kafka";

  @UtilityClass
  public static class Steps {

    private static final String STEPS_PATH = BASE_PATH + ".job.steps";

    public static final String USERS_RETRIEVAL = STEPS_PATH + ".users-retrieval";

    public static final String ADDRESSES_RETRIEVAL = STEPS_PATH + ".addresses-retrieval";
  }
}
