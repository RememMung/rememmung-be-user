package rememmung.be_user.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestService {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public Map postRequest(String APIURL, String accessToken) {
        Object res = webClient.post().uri(APIURL).header("authorization","Bearer " + accessToken)
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Map.class);
                    }else {
                        return Mono.just(
                                ImmutableMap.builder()
                                        .put("statusCode", response.statusCode().value())
                                        .build()
                        );
                    }
                });
        return objectMapper.convertValue(res, Map.class);
    }

    public Map getRequest(String APIURL, String accessToken) {
        Object res = webClient.get().uri(APIURL).header("authorization","Bearer " + accessToken)
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Map.class);
                    }else {
                        return Mono.just(
                                ImmutableMap.builder()
                                        .put("statusCode", response.statusCode().value())
                                        .build()
                        );
                    }
                });
        return objectMapper.convertValue(res, Map.class);
    }
}
