package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.NetworkService;
import com.spotify.docker.client.messages.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkManager {

    @Autowired
    private NetworkService networkService;

    public List<Network> listOfAllNetworks() {
        return networkService.listOfAllNetworks();
    }

    public Response removeNetwork(String networkId) {
        return networkService.removeNetwork(networkId);
    }
}
