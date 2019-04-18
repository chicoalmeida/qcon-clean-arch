package com.chico.cleanarch.app.entrypoint;


import static java.util.stream.Collectors.toList;

import com.chico.cleanarch.app.entrypoint.model.CharacterModel;
import com.chico.cleanarch.domain.usecase.CharacterRetrieverUseCase;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharactersController {

  private CharacterRetrieverUseCase usecase;

  public CharactersController(final CharacterRetrieverUseCase usecase) {
    this.usecase = usecase;
  }

  @GetMapping("/film/{filmName}/characters")
  public ResponseEntity<List<CharacterModel>> getCharacterByFilm(@PathVariable String filmName) {
    final List<CharacterModel> characters = this.usecase.retrieveFilmCharacters(filmName)
        .stream()
        .map(CharacterModel::fromDomain)
        .collect(toList());
    return ResponseEntity.ok(characters);
  }
}