package ufjf.wati.recommender;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ufjf.wati.dto.RecommendedTipsDto;

import java.util.Arrays;

public class RecommenderApi {

    private static String BASE_URL = "http://localhost:5002";
    private static String URL_GET_TIPS = BASE_URL + "/tips/";

    public static RecommendedTipsDto GetTipsFromRecommender(int userId){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RecommendedTipsDto> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RecommendedTipsDto> response = restTemplate.exchange(URL_GET_TIPS + userId, HttpMethod.GET, entity, RecommendedTipsDto.class);

        return response.getBody();
    }

}
