package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.SecretManager;
import com.spotify.docker.client.messages.swarm.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/secrets")
public class SecretController {

    @Autowired
    private SecretManager secretManager;

    @GetMapping
    public String page() {
        return "secrets";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Secret>> listOfAllSecret() {
        List<Secret> secrets = secretManager.listOfAllSecret();
        return ResponseEntity.status(HttpStatus.OK).body(secrets);
    }

    @ResponseBody
    @DeleteMapping("/{secretId}")
    public ResponseEntity<Response> deleteSecret(@PathVariable String secretId) {
        Response response = secretManager.deleteSecret(secretId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}