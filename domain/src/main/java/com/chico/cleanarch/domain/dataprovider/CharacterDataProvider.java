package com.chico.cleanarch.domain.dataprovider;

import com.chico.cleanarch.domain.entity.Character;
import com.chico.cleanarch.domain.entity.Film;

public interface CharacterDataProvider {

  Character getCharacterByName(String filmName);

}
