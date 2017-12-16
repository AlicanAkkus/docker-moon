package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.service.SwarmService;
import com.spotify.docker.client.messages.swarm.Swarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwarmManager {

    @Autowired
    private SwarmService swarmService;

    public Swarm inspectSwarm() {
        return swarmService.inspectSwarm();
    }
}
