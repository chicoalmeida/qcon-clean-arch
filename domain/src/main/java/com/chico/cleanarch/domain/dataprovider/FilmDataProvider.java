package com.chico.cleanarch.domain.dataprovider;

import com.chico.cleanarch.domain.entity.Character;
import java.util.List;

public interface FilmDataProvider {

  List<Character> getCharacterFromFilm(Integer filmTitle);
}
