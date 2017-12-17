package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.swarm.Config;
import com.spotify.docker.client.messages.swarm.ConfigSpec;
import com.spotify.docker.client.messages.swarm.NodeInfo;
import com.spotify.docker.client.messages.swarm.NodeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;

@Component
public class ConfigService {

    private final Logger logger = LoggerFactory.getLogger(ConfigService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting config info. {}";

    @Autowired
    private DockerClient dockerClient;

    public List<Config> listOfAllConfig() {
        try {
            return dockerClient.listConfigs();
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response deleteConfig(String configId) {
        Response response = new Response();

        handle(() -> {
            dockerClient.deleteConfig(configId);

            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }
}