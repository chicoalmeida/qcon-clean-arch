package com.chico.cleanarch.app.dataprovider.http;

import com.chico.cleanarch.app.dataprovider.http.model.CharacterModel;
import com.chico.cleanarch.app.dataprovider.http.model.FilmModel;
import com.chico.cleanarch.app.dataprovider.http.model.SpecieModel;
import com.chico.cleanarch.domain.dataprovider.FilmDataProvider;
import com.chico.cleanarch.domain.entity.Character;
import com.chico.cleanarch.domain.entity.Specie;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Named
@RequiredArgsConstructor
public class CharacterHttpProvider{

  private final RestTemplate restTemplate;

  public CharacterModel retrieveCharactersFromUrl(final String characterUrl) {
    return restTemplate
        .exchange(characterUrl,
            HttpMethod.GET,
            new HttpEntity<String>(getHeaders()),
            CharacterModel.class)
        .getBody();
  }

  private List<Specie> retrieveSpecies(final CharacterModel characterModel) {
    return characterModel.getSpeciesURL().stream()
        .map(specieUrl -> restTemplate
            .exchange(specieUrl,
                HttpMethod.GET,
                new HttpEntity<String>(getHeaders()),
                SpecieModel.class)
            .getBody())
        .filter(Objects::nonNull)
        .map(SpecieModel::toDomain)
        .collect(Collectors.toList());
  }


  private HttpHeaders getHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }

}
