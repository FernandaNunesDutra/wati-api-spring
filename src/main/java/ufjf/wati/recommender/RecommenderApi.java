package ufjf.wati.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import ufjf.wati.dto.RecommendedTipsDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecommenderApi {

    private static String BASE_URL = "http://localhost:5002";

    public static RecommendedTipsDto GetTipsFromRecommender(int userId){

        RecommendedTipsDto recommended = new RecommendedTipsDto();

        try {

            URL url = new URL(BASE_URL + "/tips/" + userId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String output;
                if ((output = br.readLine()) != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    recommended = mapper.readValue(output, RecommendedTipsDto.class);
                    System.out.println(output);
                }
            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return recommended;
    }
}
