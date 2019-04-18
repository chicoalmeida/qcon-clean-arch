package com.chico.cleanarch.app.entrypoint;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.yaml.snakeyaml.util.UriEncoder;

public class CharactersControllerTest {

  private TestRestTemplate apiClient;

  @Test
  public void given_valid_film_name_should_return_the_characters() {
    //given
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    final String filmName = UriEncoder.encode("The Empire Strikes Back");
    //when
    final ResponseEntity<List<FilmCharacter>> result = apiClient
        .exchange("/characters?name=" + filmName, HttpMethod.GET, new HttpEntity<String>(headers),
            new ParameterizedTypeReference<List<FilmCharacter>>() {
            });

    //then
    assertThat(result)
        .isNotNull();
    assertThat(result.getStatusCode())
        .isEqualTo(HttpStatus.OK);
    assertThat(result.getBody())
        .asList()
        .isNotEmpty()
        .first()
        .extracting("name", "gender", "specie")
        .doesNotContainNull();
  }

  @Test
  public void given_invalid_film_name_should_return_the_characters() {
    //given
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    //when
    final ResponseEntity<List<ResponseMessage>> result = apiClient
        .exchange("/characters", HttpMethod.GET, new HttpEntity<String>(headers),
            ResponseMessage.class);

    //then
    assertThat(result)
        .isNotNull();
    assertThat(result.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(result.getBody())
        .isNotNull()
        .extracting("message", "filme")
        .containsOnly(
            "the given film is not valid, check the available movides",
            List.of(
                "The Force Awakens",
                "Return of the Jedi",
                "The Empire Strikes Back",
                "A New Hope",
                "Revenge of the Sith",
                "Attack of the Clones",
                "The Phantom Menace"
            ));
  }

}
