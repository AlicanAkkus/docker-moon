package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.ImageService;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageManager {

    @Autowired
    private ImageService imageService;

    public List<ImageInfo> listOfAllImages() {
        return imageService.listOfAllImages();
    }

    public Response removeImage(String imageId) {
        return imageService.removeImage(imageId);
    }
}
