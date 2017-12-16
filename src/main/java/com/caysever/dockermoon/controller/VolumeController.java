package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.VolumeManager;
import com.spotify.docker.client.messages.Volume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/volumes")
public class VolumeController {

    @Autowired
    private VolumeManager volumeManager;

    @GetMapping
    public String page() {
        return "volumes";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Volume>> listOfAllVolumes() {
        List<Volume> networks = volumeManager.listOfAllVolumes();
        return ResponseEntity.status(HttpStatus.OK).body(networks);
    }

    @ResponseBody
    @DeleteMapping("/{volumeName}")
    public ResponseEntity<Response> removeNetwork(@PathVariable String volumeName) {
        Response response = volumeManager.removeVolume(volumeName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}