package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.Volume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;

@Component
public class VolumeService {

    private final Logger logger = LoggerFactory.getLogger(VolumeService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting volume info. {}";

    @Autowired
    private DockerClient dockerClient;

    public List<Volume> listOfAllVolumes() {
        try {
            return dockerClient.listVolumes().volumes().stream().map(this::inspect).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response removeVolume(String volumeName) {
        Response response = new Response();

        handle(() -> {
            dockerClient.removeVolume(volumeName);

            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    private Volume inspect(Volume volume) {
        try {
            return dockerClient.inspectVolume(volume.name());
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }
}
