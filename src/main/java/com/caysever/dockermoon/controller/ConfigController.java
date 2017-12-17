package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.ConfigManager;
import com.spotify.docker.client.messages.swarm.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/configs")
public class ConfigController {

    @Autowired
    private ConfigManager configManager;

    @GetMapping
    public String page() {
        return "configs";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Config>> listOfAllConfig() {
        List<Config> configs = configManager.listOfAllConfig();
        return ResponseEntity.status(HttpStatus.OK).body(configs);
    }

    @ResponseBody
    @DeleteMapping("/{configId}")
    public ResponseEntity<Response> deleteConfig(@PathVariable String configId) {
        Response response = configManager.deleteConfig(configId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}