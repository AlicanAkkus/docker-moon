package com.caysever.dockermoon.controller.response;

public class ContainerActionResponse extends Response {

    private String containerId;
    private String containerState;

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getContainerState() {
        return containerState;
    }

    public void setContainerState(String containerState) {
        this.containerState = containerState;
    }
}
