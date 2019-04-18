package com.chico.cleanarch.domain.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class Character {

  private final String name;
  private final String gender;
  private final List<Specie> species;
}
