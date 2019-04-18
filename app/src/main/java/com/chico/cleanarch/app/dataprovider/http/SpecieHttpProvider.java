package com.chico.cleanarch.app.dataprovider.http;

import com.chico.cleanarch.app.dataprovider.http.model.SpecieModel;
import com.chico.cleanarch.domain.entity.Specie;
import java.util.Collections;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Named
@RequiredArgsConstructor
public class SpecieHttpProvider {

  private final RestTemplate restTemplate;

  public Specie retrieveSpecieByUrl(final String specieUrl) {
    return restTemplate
        .exchange(specieUrl,
            HttpMethod.GET,
            new HttpEntity<String>(getHeaders()),
            SpecieModel.class)
        .getBody()
        .toDomain();
  }

  private HttpHeaders getHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }

}
