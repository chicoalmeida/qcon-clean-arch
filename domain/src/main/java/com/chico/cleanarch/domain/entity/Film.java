package com.chico.cleanarch.domain.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class Film {

  private final Long id;
  private final Long episode;
  private final String name;
  private final List<Character> characters;

  public List<Character> getCharacters() {
    return characters;
  }
}
