package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.swarm.Config;
import com.spotify.docker.client.messages.swarm.Secret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;

@Component
public class SecretService {

    private final Logger logger = LoggerFactory.getLogger(SecretService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting config info. {}";

    @Autowired
    private DockerClient dockerClient;

    public List<Secret> listOfAllSecret() {
        try {
            return dockerClient.listSecrets();
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response deleteSecret(String secretId) {
        Response response = new Response();

        handle(() -> {
            dockerClient.deleteSecret(secretId);

            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }
}