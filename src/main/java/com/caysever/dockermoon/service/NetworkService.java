package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;

@Component
public class NetworkService {

    private final Logger logger = LoggerFactory.getLogger(NetworkService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting network info. {}";

    @Autowired
    private DockerClient dockerClient;

    public List<Network> listOfAllNetworks() {
        try {
            return dockerClient.listNetworks().stream().map(this::inspect).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response removeNetwork(String networkId) {
        Response response = new Response();

        handle(() -> {
            dockerClient.removeNetwork(networkId);

            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    private Network inspect(Network network) {
        try {
            return dockerClient.inspectNetwork(network.id());
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }
}
