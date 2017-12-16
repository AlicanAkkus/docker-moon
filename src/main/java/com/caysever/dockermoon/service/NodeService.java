package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.swarm.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;

@Component
public class NodeService {

    private final Logger logger = LoggerFactory.getLogger(NodeService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting node info. {}";

    @Autowired
    private DockerClient dockerClient;

    public List<Node> listOfAllNodes() {
        try {
            return dockerClient.listNodes();
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response deleteNode(String nodeId) {
        Response response = new Response();

        handle(() -> {
            dockerClient.deleteNode(nodeId, true);

            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }
}