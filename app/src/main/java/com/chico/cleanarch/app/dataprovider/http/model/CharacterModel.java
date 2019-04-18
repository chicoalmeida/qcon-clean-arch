package com.chico.cleanarch.app.dataprovider.http.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharacterModel {
  private String name;
  private String gender;
  @JsonProperty("species")
  private List<String> speciesURL;
}
