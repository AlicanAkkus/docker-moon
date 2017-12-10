package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.caysever.dockermoon.handler.ExceptionHandler;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;
import static com.caysever.dockermoon.helper.ObjectHelper.isPresent;

@Component
public class ContainerService {

    private final Logger logger = LoggerFactory.getLogger(ContainerService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting container info. {}";
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
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response restartContainer(String containerId) {
        Response response = new Response();
        handle(() -> {
            ContainerInfo containerInfo = dockerClient.inspectContainer(containerId);
            if (isPresent(containerInfo)) {
                dockerClient.restartContainer(containerId);
                response.setStatus(ResponseStatusType.SUCCESS.getValue());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getValue());
            }
            return response;
        });

        return response;
    }

    public Response restartContainers(ArrayList<String> containers) {
        Response response = new Response();

        handle(() -> {
            containers.stream().forEach(this::restartContainer);
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
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
                response.setStatus(ResponseStatusType.SUCCESS.getValue());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getValue());
            }
            return response;
        });

        return response;
    }

    public Response stopContainers(ArrayList<String> containers) {
        Response response = new Response();

        handle(() -> {
            containers.stream().forEach(this::stopContainer);
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    public void startContainer(String containerId) {
        try {
            dockerClient.startContainer(containerId);
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response startContainers(ArrayList<String> containers) {
        Response response = new Response();

        handle(() -> {
            containers.stream().forEach(this::startContainer);
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    public void pauseContainer(String containerId) {
        try {
            dockerClient.pauseContainer(containerId);
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response pauseContainers(ArrayList<String> containers) {
        Response response = new Response();

        handle(() -> {
            containers.stream().forEach(this::pauseContainer);
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    public void unpauseContainer(String containerId) {
        try {
            dockerClient.unpauseContainer(containerId);
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response unpauseContainers(ArrayList<String> containers) {
        Response response = new Response();

        handle(() -> {
            containers.stream().forEach(this::unpauseContainer);
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    public Response killContainer(String containerId) {
        Response response = new Response();
        handle(() -> {
            ContainerInfo containerInfo = dockerClient.inspectContainer(containerId);
            if (isPresent(containerInfo)) {
                dockerClient.killContainer(containerId);
                response.setStatus(ResponseStatusType.SUCCESS.getValue());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getValue());
            }
            return response;
        });

        return response;
    }

    public Response killContainers(ArrayList<String> containers) {
        Response response = new Response();

        handle(() -> {
            containers.stream().forEach(this::killContainer);
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    public Response logContainer(String containerId) {
        Response response = new Response();
        handle(() -> {
            try (LogStream stream = dockerClient.logs(containerId, DockerClient.LogsParam.stdout(), DockerClient.LogsParam.stderr())) {
                response.setData(stream.readFully());
            }
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    public Response removeContainer(String containerId) {
        Response response = new Response();
        handle(() -> {
            ContainerInfo containerInfo = dockerClient.inspectContainer(containerId);
            if (isPresent(containerInfo)) {
                dockerClient.removeContainer(containerId);
                response.setStatus(ResponseStatusType.SUCCESS.getValue());
            } else {
                response.setStatus(ResponseStatusType.FAILURE.getValue());
            }
            return response;
        });

        return response;
    }

    public Response removeContainers(ArrayList<String> containers) {
        Response response = new Response();

        handle(() -> {
            containers.stream().forEach(this::removeContainer);
            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }
}
