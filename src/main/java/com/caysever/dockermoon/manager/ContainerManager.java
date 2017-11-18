package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.ContainerService;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerManager {

    @Autowired
    private ContainerService containerService;

    public List<Container> listOfAllContainers() {
        return containerService.listOfAllContainers();
    }

    public ContainerInfo getContainerInfo(String containerId) {
        return containerService.getContainerInfo(containerId);
    }

    public Response restartContainer(String containerId){
        return containerService.restartContainer(containerId);
    }

    public Response stopContainer(String containerId) {
        return containerService.stopContainer(containerId);
    }

    public void startContainer(String containerId) {
       containerService.startContainer(containerId);
    }

    public void pauseContainer(String containerId) {
        containerService.pauseContainer(containerId);
    }

    public void unpauseContainer(String containerId) {
        containerService.unpauseContainer(containerId);
    }

    public Response killContainer(String containerId) {
        return containerService.killContainer(containerId);
    }
}
