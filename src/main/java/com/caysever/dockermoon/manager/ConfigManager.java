package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.ConfigService;
import com.spotify.docker.client.messages.swarm.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigManager {

    @Autowired
    private ConfigService configService;

    public List<Config> listOfAllConfig() {
        return configService.listOfAllConfig();
    }

    public Response deleteConfig(String configId) {
        return configService.deleteConfig(configId);
    }
}