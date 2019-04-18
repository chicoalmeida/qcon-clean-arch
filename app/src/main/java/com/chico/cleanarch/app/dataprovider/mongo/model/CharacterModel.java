package com.chico.cleanarch.app.dataprovider.mongo.model;

import static java.util.stream.Collectors.toList;

import com.chico.cleanarch.domain.entity.Character;
import com.chico.cleanarch.domain.entity.Specie;
import java.util.List;
import lombok.Builder;

@Builder
public class CharacterModel {

  private String name;
  private String gender;
  private List<SpecieModel> species;

  public static CharacterModel fromDomain(final Character character) {
    return CharacterModel.builder()
        .name(character.getName())
        .gender(character.getGender())
        .species(getSpeciesModel(character))
        .build();
  }

  private static List<SpecieModel> getSpeciesModel(Character character) {
    return character.getSpecies().stream()
        .map(SpecieModel::fromDomain)
        .collect(toList());
  }

  public Character toDomain() {
    return Character.builder()
        .name(name)
        .gender(gender)
        .species(getSpecies())
        .build();
  }

  private List<Specie> getSpecies() {
    return species.stream().map(SpecieModel::toDomain).collect(toList());
  }
}
