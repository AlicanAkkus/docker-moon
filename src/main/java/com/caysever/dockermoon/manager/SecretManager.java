package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.SecretService;
import com.spotify.docker.client.messages.swarm.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretManager {

    @Autowired
    private SecretService secretService;

    public List<Secret> listOfAllSecret() {
        return secretService.listOfAllSecret();
    }

    public Response deleteSecret(String secretId) {
        return secretService.deleteSecret(secretId);
    }
}