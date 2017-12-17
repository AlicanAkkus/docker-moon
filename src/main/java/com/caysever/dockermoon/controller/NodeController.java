package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.manager.NodeManager;
import com.spotify.docker.client.messages.swarm.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NodeManager nodeManager;

    @GetMapping
    public String page() {
        return "nodes";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Node>> listOfAllNodes() {
        List<Node> nodes = nodeManager.listOfAllNodes();
        return ResponseEntity.status(HttpStatus.OK).body(nodes);
    }

    @ResponseBody
    @DeleteMapping("/{nodeId}")
    public ResponseEntity<Response> deleteNode(@PathVariable String nodeId) {
        Response response = nodeManager.deleteNode(nodeId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @PutMapping("/{nodeId}/status")
    public ResponseEntity<Response> updateStatusNode(@PathVariable String nodeId, @RequestParam("q") String status) {
        Response response = nodeManager.updateStatusNode(nodeId, status);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseBody
    @PutMapping("/{nodeId}/role")
    public ResponseEntity<Response> updateRoleNode(@PathVariable String nodeId, @RequestParam("q") String role) {
        Response response = nodeManager.updateRoleNode(nodeId, role);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}