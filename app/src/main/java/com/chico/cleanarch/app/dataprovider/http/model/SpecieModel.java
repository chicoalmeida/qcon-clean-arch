package com.chico.cleanarch.app.dataprovider.http.model;

import com.chico.cleanarch.domain.entity.Specie;
import lombok.Data;

@Data
public class SpecieModel {

  private String name;
  private String language;

  public Specie toDomain() {
    return Specie.builder()
        .language(language)
        .name(name)
        .build();
  }
}
