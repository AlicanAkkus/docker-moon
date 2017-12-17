package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.NodeAvailabilityStatus;
import com.caysever.dockermoon.enumtype.NodeRole;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.swarm.Node;
import com.spotify.docker.client.messages.swarm.NodeInfo;
import com.spotify.docker.client.messages.swarm.NodeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

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

    public Response updateStatusNode(String nodeId, String status) {
        Response response = new Response();

        handle(() -> {
            if (isValidStatus(status)) {
                NodeInfo nodeInfo = dockerClient.inspectNode(nodeId);
                NodeSpec drainSpec = NodeSpec.builder(nodeInfo.spec())
                        .availability(status)
                        .build();

                dockerClient.updateNode(nodeId, nodeInfo.version().index(), drainSpec);
                response.setStatus(ResponseStatusType.SUCCESS.getValue());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getValue());
                response.setErrorMessage("Unknow status for update node!");
            }
            return response;
        });

        return response;
    }

    public Response updateRoleNode(String nodeId, String role) {
        Response response = new Response();

        handle(() -> {
            if (isValidRole(role)) {
                NodeInfo nodeInfo = dockerClient.inspectNode(nodeId);
                NodeSpec drainSpec = NodeSpec.builder(nodeInfo.spec())
                        .role(role)
                        .build();

                dockerClient.updateNode(nodeId, nodeInfo.version().index(), drainSpec);
                response.setStatus(ResponseStatusType.SUCCESS.getValue());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getValue());
                response.setErrorMessage("Unknow status for update node!");
            }
            return response;
        });

        return response;
    }

    private boolean isValidStatus(String status) {
        return Stream.of(NodeAvailabilityStatus.values()).anyMatch(nodeStatus -> nodeStatus.getValue().equalsIgnoreCase(status));
    }

    private boolean isValidRole(String role) {
        return Stream.of(NodeRole.values()).anyMatch(nodeRole -> nodeRole.getValue().equalsIgnoreCase(role));
    }
}