package com.chico.cleanarch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class Specie {

  private final String name;
  private final String language;

}
