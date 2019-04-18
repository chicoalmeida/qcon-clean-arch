package com.chico.cleanarch.app.dataprovider.http;

import com.chico.cleanarch.app.dataprovider.http.model.CharacterModel;
import com.chico.cleanarch.app.dataprovider.http.model.FilmModel;
import com.chico.cleanarch.domain.dataprovider.FilmDataProvider;
import com.chico.cleanarch.domain.entity.Character;
import com.chico.cleanarch.domain.entity.Specie;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Named
@RequiredArgsConstructor
public class FilmHttpProvider implements FilmDataProvider {

  private final RestTemplate restTemplate;
  private final SpecieHttpProvider specieHttpProvider;
  private final CharacterHttpProvider characterHttpProvider;

  @Override
  public List<Character> getCharacterFromFilm(final Integer filmId) {
    final FilmModel filmModel = this.retrieveFilmDetails(filmId);

    return filmModel.getCharactersURL()
        .stream()
        .limit(3)
        .map(characterHttpProvider::retrieveCharactersFromUrl)
        .map(this::createDomainCharacter)
        .collect(Collectors.toList());
  }

  private FilmModel retrieveFilmDetails(final Integer filmId) {

    final String url = "https://swapi.co/api/films/";
    return restTemplate
        .getForEntity(url + filmId + "?format=json",
            FilmModel.class)
        .getBody();
  }

  private Character createDomainCharacter(final CharacterModel characterModel) {
    final List<Specie> species = characterModel.getSpeciesURL()
        .stream()
        .map(specieHttpProvider::retrieveSpecieByUrl)
        .collect(Collectors.toList());

    return Character.builder()
        .name(characterModel.getName())
        .gender(characterModel.getGender())
        .species(species)
        .build();
  }

  private HttpHeaders getHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }

}
