package com.chico.cleanarch.app.dataprovider.mongo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("films")
public class FilmModel {

  public static final FilmModel DEFAULT = FilmModel.builder()
      .characters(Collections.emptyList())
      .build();
  @Id
  private Integer id;
  private String title;
  @JsonProperty("episode_id")
  private Integer episodeId;
  private List<CharacterModel> characters;

}
