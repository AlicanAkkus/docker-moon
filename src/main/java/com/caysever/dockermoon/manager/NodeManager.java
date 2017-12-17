package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.controller.response.Response;
import com.caysever.dockermoon.service.NodeService;
import com.spotify.docker.client.messages.swarm.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeManager {

    @Autowired
    private NodeService nodeService;

    public List<Node> listOfAllNodes() {
        return nodeService.listOfAllNodes();
    }

    public Response deleteNode(String nodeId) {
        return nodeService.deleteNode(nodeId);
    }

    public Response updateStatusNode(String nodeId, String status) {
        return nodeService.updateStatusNode(nodeId, status);
    }

    public Response updateRoleNode(String nodeId, String role) {
        return nodeService.updateRoleNode(nodeId, role);
    }
}