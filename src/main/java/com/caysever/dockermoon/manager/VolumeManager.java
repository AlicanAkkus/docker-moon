package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.VolumeService;
import com.spotify.docker.client.messages.Volume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolumeManager {

    @Autowired
    private VolumeService volumeService;

    public List<Volume> listOfAllVolumes() {
        return volumeService.listOfAllVolumes();
    }

    public Response removeVolume(String volumeName) {
        return volumeService.removeVolume(volumeName);
    }
}
