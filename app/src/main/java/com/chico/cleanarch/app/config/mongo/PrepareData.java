package com.chico.cleanarch.app.config.mongo;

import static java.util.stream.Collectors.toList;

import com.chico.cleanarch.app.dataprovider.mongo.model.CharacterModel;
import com.chico.cleanarch.app.dataprovider.mongo.model.FilmModel;
import com.chico.cleanarch.app.dataprovider.mongo.repository.FilmModelRepository;
import com.chico.cleanarch.domain.dataprovider.FilmDataProvider;
import com.chico.cleanarch.domain.entity.Character;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("mongo")
public class PrepareData implements ApplicationRunner {

  private final FilmDataProvider filmHttpProvider;
  private final FilmModelRepository repository;
  private final Map<String, Integer> filmTitleManager;

  public PrepareData(
      @Qualifier("filmHttpProvider") FilmDataProvider filmHttpProvider,
      FilmModelRepository repository,
      Map<String, Integer> filmTitleManager) {
    this.filmHttpProvider = filmHttpProvider;
    this.repository = repository;
    this.filmTitleManager = filmTitleManager;
  }

  @Override
  public void run(ApplicationArguments args) {
    if (repository.count() > 0) {
      return;
    }

    filmTitleManager.entrySet().stream()
        .map(this::getFilmsModel)
        .forEach(repository::save);

    log.info("finished prepare data");
  }

  private FilmModel getFilmsModel(Entry<String, Integer> filmManager) {
    final List<Character> characters = filmHttpProvider
        .getCharacterFromFilm(filmManager.getValue());

    return FilmModel.builder()
        .id(filmManager.getValue())
        .title(filmManager.getKey())
        .episodeId(filmManager.getValue())
        .characters(getCharactersModel(characters))
        .build();
  }

  private List<CharacterModel> getCharactersModel(List<Character> characters) {
    return characters.stream()
        .map(CharacterModel::fromDomain)
        .collect(toList());
  }
}
