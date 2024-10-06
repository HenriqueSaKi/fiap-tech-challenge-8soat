package br.com.fiap.tech_challenge.adapters.driver.webhook.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebhookRequestDTO {

  private Long id;
  private Boolean liveMode;
  private String type;
  private String dateCreated;
  private Long userId;
  private String apiVersion;
  private String action;
  private DataDTO data;

}
