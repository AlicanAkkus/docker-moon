package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.ContainerService;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Response restartContainer(String containerId) {
        return containerService.restartContainer(containerId);
    }

    public Response restartContainers(ArrayList<String> containers) {
        return containerService.restartContainers(containers);
    }

    public Response stopContainer(String containerId) {
        return containerService.stopContainer(containerId);
    }

    public Response stopContainers(ArrayList<String> containers) {
        return containerService.stopContainers(containers);
    }

    public void startContainer(String containerId) {
        containerService.startContainer(containerId);
    }

    public Response startContainers(ArrayList<String> containers) {
        return containerService.startContainers(containers);
    }

    public void pauseContainer(String containerId) {
        containerService.pauseContainer(containerId);
    }

    public Response pauseContainers(ArrayList<String> containers) {
        return containerService.pauseContainers(containers);
    }

    public void unpauseContainer(String containerId) {
        containerService.unpauseContainer(containerId);
    }

    public Response unpauseContainers(ArrayList<String> containers) {
        return containerService.unpauseContainers(containers);
    }

    public Response killContainer(String containerId) {
        return containerService.killContainer(containerId);
    }

    public Response killContainers(ArrayList<String> containers) {
        return containerService.killContainers(containers);
    }

    public Response logContainer(String containerId) {
        return containerService.logContainer(containerId);
    }

    public Response removeContainer(String containerId) {
        return containerService.removeContainer(containerId);
    }

    public Response removeContainers(ArrayList<String> containers) {
        return containerService.removeContainers(containers);
    }
}
