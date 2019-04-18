package com.chico.cleanarch.app.dataprovider.http.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class FilmModel {

  private String title;
  @JsonProperty("episode_id")
  private Integer episodeId;
  @JsonProperty("characters")
  private List<String> charactersURL;
}
