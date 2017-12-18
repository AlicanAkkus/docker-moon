package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.ContainerManager;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @PostMapping("/restart")
    public ResponseEntity<Response> restartContainers(@RequestBody ArrayList<String> containers) {
        Response response = containerManager.restartContainers(containers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/restart/{containerId}")
    public ResponseEntity<Response> restartContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.restartContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @PostMapping("/pause")
    public ResponseEntity<Response> pauseContainers(@RequestBody ArrayList<String> containers) {
        Response response = containerManager.pauseContainers(containers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/pause/{containerId}")
    public ResponseEntity<ContainerInfo> pauseContainer(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @PostMapping("/unpause")
    public ResponseEntity<Response> unpauseContainers(@RequestBody ArrayList<String> containers) {
        Response response = containerManager.unpauseContainers(containers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/unpause/{containerId}")
    public ResponseEntity<ContainerInfo> unpauseContainer(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @PostMapping("/stop")
    public ResponseEntity<Response> stopContainers(@RequestBody ArrayList<String> containers) {
        Response response = containerManager.stopContainers(containers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/stop/{containerId}")
    public ResponseEntity<Response> stopContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.stopContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @PostMapping("/start")
    public ResponseEntity<Response> startContainers(@RequestBody ArrayList<String> containers) {
        Response response = containerManager.startContainers(containers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/start/{containerId}")
    public ResponseEntity<ContainerInfo> startContainer(@PathVariable("containerId") String containerId) {
        ContainerInfo containerInfo = containerManager.getContainerInfo(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(containerInfo);
    }

    @ResponseBody
    @PostMapping("/kill")
    public ResponseEntity<Response> killContainers(@RequestBody ArrayList<String> containers) {
        Response response = containerManager.killContainers(containers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/kill/{containerId}")
    public ResponseEntity<Response> killContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.killContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/log/{containerId}")
    public ResponseEntity<Response> logContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.logContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @PostMapping("/remove")
    public ResponseEntity<Response> removeContainers(@RequestBody ArrayList<String> containers) {
        Response response = containerManager.removeContainers(containers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @GetMapping("/remove/{containerId}")
    public ResponseEntity<Response> removeContainer(@PathVariable("containerId") String containerId) {
        Response response = containerManager.removeContainer(containerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @PutMapping("/rename/{containerId}")
    public ResponseEntity<Response> renameContainer(@PathVariable String containerId, @RequestParam("name") String name) {
        Response response = containerManager.renameContainer(containerId, name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}