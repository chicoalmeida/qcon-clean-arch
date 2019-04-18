package com.chico.cleanarch.app.dataprovider.mongo;

import static java.util.stream.Collectors.toList;

import com.chico.cleanarch.app.dataprovider.mongo.model.CharacterModel;
import com.chico.cleanarch.app.dataprovider.mongo.model.FilmModel;
import com.chico.cleanarch.app.dataprovider.mongo.repository.FilmModelRepository;
import com.chico.cleanarch.domain.dataprovider.FilmDataProvider;
import com.chico.cleanarch.domain.entity.Character;
import java.util.List;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;

@Named
@Primary
@RequiredArgsConstructor
public class FilmMongoProvider implements FilmDataProvider {

  private final FilmModelRepository repository;

  @Override
  public List<Character> getCharacterFromFilm(Integer filmId) {
    return repository.findById(filmId)
        .orElse(FilmModel.DEFAULT)
        .getCharacters()
        .stream()
        .map(CharacterModel::toDomain)
        .collect(toList());
  }
}
