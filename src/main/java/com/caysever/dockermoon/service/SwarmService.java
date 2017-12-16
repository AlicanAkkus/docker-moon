package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.Volume;
import com.spotify.docker.client.messages.swarm.Swarm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;

@Component
public class SwarmService {

    private final Logger logger = LoggerFactory.getLogger(SwarmService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting volume info. {}";

    @Autowired
    private DockerClient dockerClient;

    public Swarm inspectSwarm() {
        try {
            return dockerClient.inspectSwarm();
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }
}
