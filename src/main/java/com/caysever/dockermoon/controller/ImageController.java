package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.ImageManager;
import com.spotify.docker.client.messages.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageManager imageManager;

    @GetMapping
    public String page() {
        return "images";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<ImageInfo>> listOfAllImages() {
        List<ImageInfo> images = imageManager.listOfAllImages();
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }

    @ResponseBody
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Response> deleteImage(@PathVariable String imageId) {
        Response response = imageManager.removeImage(imageId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
