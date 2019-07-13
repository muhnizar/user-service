package id.co.homecredit.service;

import id.co.homecredit.entity.User;
import id.co.homecredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.nizar01 on 7/13/2019.
 */
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        List scopes = new ArrayList<String>(2);
        scopes.add("write");
        scopes.add("read");

        resourceDetails.setClientId("spring-security-oauth2-read-write-client");
        resourceDetails.setClientSecret("spring-security-oauth2-read-write-client-password1234");
        resourceDetails.setGrantType("password");
        resourceDetails.setAccessTokenUri("http://localhost:7001/oauth/token");
        resourceDetails.setScope(scopes);


        final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
        final OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();

        final String accessTokenAsString = accessToken.getValue();

        System.out.println(accessTokenAsString);

        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
