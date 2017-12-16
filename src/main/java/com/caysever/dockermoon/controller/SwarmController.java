package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.manager.SwarmManager;
import com.spotify.docker.client.messages.swarm.Swarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/swarm")
public class SwarmController {

    @Autowired
    private SwarmManager swarmManager;

    @GetMapping
    public String page() {
        return "swarm";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<Swarm> inspectSwarm() {
        Swarm swarm = swarmManager.inspectSwarm();
        return ResponseEntity.status(HttpStatus.OK).body(swarm);
    }
}