package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.NetworkManager;
import com.spotify.docker.client.messages.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/networks")
public class NetworkController {

    @Autowired
    private NetworkManager networkManager;

    @GetMapping
    public String page() {
        return "networks";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Network>> listOfAllNetworks() {
        List<Network> networks = networkManager.listOfAllNetworks();
        return ResponseEntity.status(HttpStatus.OK).body(networks);
    }

    @ResponseBody
    @DeleteMapping("/{networkId}")
    public ResponseEntity<Response> removeNetwork(@PathVariable String networkId) {
        Response response = networkManager.removeNetwork(networkId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}