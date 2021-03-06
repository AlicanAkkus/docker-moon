package com.caysever.dockermoon.service;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.enumtype.ResponseStatusType;
import com.caysever.dockermoon.exception.ContainerException;
import com.caysever.dockermoon.handler.ExceptionHandler;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.caysever.dockermoon.handler.ExceptionHandler.handle;

@Component
public class ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final String ERROR_TEMPLATE = "An error occured while getting image info. {}";

    @Autowired
    private DockerClient dockerClient;

    @Autowired
    private ExceptionHandler exceptionHandler;

    public List<ImageInfo> listOfAllImages() {
        try {
            return dockerClient.listImages().stream().map(this::inspect).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }

    public Response removeImage(String imageId) {
        Response response = new Response();

        handle(() -> {
            dockerClient.removeImage(imageId);

            response.setStatus(ResponseStatusType.SUCCESS.getValue());
            return response;
        });

        return response;
    }

    private ImageInfo inspect(Image image) {
        try {
            return dockerClient.inspectImage(image.id());
        } catch (Exception e) {
            logger.error(ERROR_TEMPLATE, e);
            throw new ContainerException(e);
        }
    }
}
