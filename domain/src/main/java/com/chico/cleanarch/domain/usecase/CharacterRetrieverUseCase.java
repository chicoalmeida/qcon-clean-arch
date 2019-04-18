package com.chico.cleanarch.domain.usecase;

import com.chico.cleanarch.domain.dataprovider.FilmDataProvider;
import com.chico.cleanarch.domain.entity.Character;
import com.chico.cleanarch.domain.usecase.expection.BusinessException;
import java.util.List;
import java.util.Map;
import javax.inject.Named;

@Named
public class CharacterRetrieverUseCase {

  private final FilmDataProvider filmDataProvider;
  private final Map<String, Integer> filmTitleManager;

  public CharacterRetrieverUseCase(
      @Named("filmMongoProvider") FilmDataProvider filmDataProvider,
      Map<String, Integer> filmTitleManager) {
    this.filmDataProvider = filmDataProvider;
    this.filmTitleManager = filmTitleManager;
  }

  public List<Character> retrieveFilmCharacters(final String filmTitle) {
    final Integer filmId = filmTitleManager.get(filmTitle.toLowerCase());

    if (filmId == null) {
      throw new BusinessException("Invalid Film Name");
    }
    return filmDataProvider.getCharacterFromFilm(filmId);
  }
}
