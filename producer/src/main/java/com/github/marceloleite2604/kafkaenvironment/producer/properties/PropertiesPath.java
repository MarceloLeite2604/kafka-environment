package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertiesPath {

  private static final String BASE_PATH = "producer";

  public static final String KAFKA = BASE_PATH + ".kafka";

  @UtilityClass
  public static class Jobs {

    private static final String JOBS_PATH = BASE_PATH + ".jobs";

    public static final String USERS_RETRIEVAL = JOBS_PATH + ".users-retrieval";

    public static final String ADDRESSES_RETRIEVAL = JOBS_PATH + ".addresses-retrieval";
  }
}
