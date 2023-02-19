package dev.fdifrison.cassaincloudbirgo.service;

import dev.fdifrison.cassaincloudbirgo.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    private final String CASSAINCLOUD_API_URL = "https://api.cassanova.com/apikey/token";
    private final RestTemplate restTemplate;

    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Requested-With","*");
        return httpHeaders;
    }

    public Token auth() {
        String requestJson = "{\"apiKey\":\"4ded20f6-1de4-44c1-a357-d9ddbe3fac3a\"}";
        ResponseEntity<Token> login = restTemplate.exchange(CASSAINCLOUD_API_URL, HttpMethod.POST, new HttpEntity<>(requestJson, httpHeaders()), Token.class);
        if (login.getStatusCode().is2xxSuccessful()) {
            LOG.info("Login successful!");
        }
        return login.getBody();
    }

    public void getRestaurants(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Version","1.0.0");
        httpHeaders.add("Authorization","Bearer " + token);
        ResponseEntity<String> response = restTemplate.exchange("https://api.cassanova.com/salespoint?hasActiveLicense=true", HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
        LOG.info(response.getBody());
    }

//    def Ristoranti(token):
//    header = {  'Content-Type' :'application/json',
//            'X-Version': '1.0.0' ,
//            'Authorization': f'Bearer {token}'}
//
//    r = requests.get('https://api.cassanova.com/salespoint?hasActiveLicense=true',
//    headers=header)
//
//    res = r.json()
//            return res


}
