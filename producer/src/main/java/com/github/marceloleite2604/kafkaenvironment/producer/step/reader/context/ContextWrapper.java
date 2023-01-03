package com.github.marceloleite2604.kafkaenvironment.producer.step.reader.context;

import com.github.marceloleite2604.kafkaenvironment.producer.properties.ItemsRetrievalStepProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.batch.item.ExecutionContext;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ContextWrapper {

  private final ItemsRetrievalStepProperties itemsRetrievalStepProperties;

  private final Context context;

  private final ExecutionContext executionContext;

  public void save() {
    executionContext.put(Context.Properties.ITEMS_READ, context.getItemsRead());
    executionContext.put(Context.Properties.TOTAL_ITEMS, context.getTotalItems());
  }

  public Context restore() {
    final var itemsRead = executionContext.getInt(Context.Properties.ITEMS_READ, 0);
    final var totalItems = executionContext.getInt(Context.Properties.TOTAL_ITEMS,
        itemsRetrievalStepProperties.getTotalItems());

    return Context.builder()
        .itemsRead(itemsRead)
        .totalItems(totalItems)
        .build();
  }
}
