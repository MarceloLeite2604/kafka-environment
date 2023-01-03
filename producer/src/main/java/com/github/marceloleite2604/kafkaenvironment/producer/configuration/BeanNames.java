package com.github.marceloleite2604.kafkaenvironment.producer.configuration;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BeanNames {

  public static final String RANDOM_USER_WEB_CLIENT = "randomUserWebClient";
  public static final String TASK_EXECUTOR = "taskExecutor";
  public static final String JOB_REGISTRY_BEAN_POST_PROCESSOR = "jobRegistryBeanPostProcessor";

  @UtilityClass
  public static class Job {

    public static final String NAME = "dataRetrievalJob";

    @UtilityClass
    public static class Steps {
      public static final String USERS_RETRIEVAL = "usersRetrievalStep";
      public static final String ADDRESSES_RETRIEVAL = "addressesRetrievalStep";
    }
  }
}
