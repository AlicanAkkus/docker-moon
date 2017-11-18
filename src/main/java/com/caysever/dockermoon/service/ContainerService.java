package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.caysever.dockermoon.handler.ExceptionHandler;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;
import static com.caysever.dockermoon.helper.ObjectHelper.isPresent;

@Component
public class ContainerService {

    private final Logger logger = LoggerFactory.getLogger(ContainerService.class);
    private final int AFTER_TEN_SECONDS = 10;

    @Autowired
    private DockerClient dockerClient;

    @Autowired
    private ExceptionHandler exceptionHandler;


    public List<Container> listOfAllContainers() {
        try {
            return dockerClient.listContainers(DockerClient.ListContainersParam.allContainers());
        } catch (Exception e) {
            logger.error("An error occured while getting containers. {}", e);
            throw new ContainerException(e);
        }
    }

    public ContainerInfo getContainerInfo(String containerId) {
        try {
            return dockerClient.inspectContainer(containerId);
        } catch (Exception e) {
            logger.error("An error occured while getting container info. {}", e);
            throw new ContainerException(e);
        }
    }

    public Response restartContainer(String containerId) {
        Response response = new Response();
        handle(() -> {
            ContainerInfo containerInfo = dockerClient.inspectContainer(containerId);
            if (isPresent(containerInfo)) {
                dockerClient.restartContainer(containerId);
                response.setStatus(ResponseStatusType.SUCCESS.getType());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getType());
            }
            return response;
        });

        return response;
    }

    public Response stopContainer(String containerId) {
        Response response = new Response();
        handle(() -> {
            ContainerInfo containerInfo = dockerClient.inspectContainer(containerId);
            if (isPresent(containerInfo)) {
                dockerClient.stopContainer(containerId, AFTER_TEN_SECONDS);
                response.setStatus(ResponseStatusType.SUCCESS.getType());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getType());
            }
            return response;
        });

        return response;
    }

    public void startContainer(String containerId) {
        try {
            dockerClient.startContainer(containerId);
        } catch (Exception e) {
            logger.error("An error occured while getting container info. {}", e);
            throw new ContainerException(e);
        }
    }

    public void pauseContainer(String containerId) {
        try {
            dockerClient.pauseContainer(containerId);
        } catch (Exception e) {
            logger.error("An error occured while getting container info. {}", e);
            throw new ContainerException(e);
        }
    }

    public void unpauseContainer(String containerId) {
        try {
            dockerClient.unpauseContainer(containerId);
        } catch (Exception e) {
            logger.error("An error occured while getting container info. {}", e);
            throw new ContainerException(e);
        }
    }

    public Response killContainer(String containerId) {
        Response response = new Response();
        handle(() -> {
            ContainerInfo containerInfo = dockerClient.inspectContainer(containerId);
            if (isPresent(containerInfo)) {
                dockerClient.killContainer(containerId);
                response.setStatus(ResponseStatusType.SUCCESS.getType());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getType());
            }
            return response;
        });

        return response;
    }
}
