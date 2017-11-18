package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.ContainerManager;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/containers")
public class ContainerController {

    @Autowired
    private ContainerManager containerManager;

    @GetMapping
    public String page() {
        return "containers";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Container>> listOfAllContainers() {
        List<Container> containers = containerManager.listOfAllContainers();
        return ResponseEntity.status(HttpStatus.OK).body(containers);
    }

    @ResponseBody
    @GetMapping("/{containerId}")
    public ResponseEntity<ContainerInfo> getContainerInfo(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/restart")
    public ResponseEntity<ContainerInfo> restartContainers(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/restart/{containerId}")
    public ResponseEntity<Response> restartContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.restartContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/pause")
    public ResponseEntity<ContainerInfo> pauseContainers(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/pause/{containerId}")
    public ResponseEntity<ContainerInfo> pauseContainer(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/unpause")
    public ResponseEntity<ContainerInfo> unpauseContainers(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/unpause/{containerId}")
    public ResponseEntity<ContainerInfo> unpauseContainer(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/stop")
    public ResponseEntity<ContainerInfo> stopContainers(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/stop/{containerId}")
    public ResponseEntity<Response> stopContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.stopContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/start")
    public ResponseEntity<ContainerInfo> startContainers(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/start/{containerId}")
    public ResponseEntity<ContainerInfo> startContainer(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/kişş")
    public ResponseEntity<ContainerInfo> killContainers(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @GetMapping("/kill/{containerId}")
    public ResponseEntity<Response> killContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.killContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
