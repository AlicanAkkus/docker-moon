package com.caysever.dockermoon.controller;

import com.caysever.dockermoon.manager.DashBoardManager;
import com.caysever.dockermoon.service.vo.dashboard.DashBoardStatsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashBoardController {

    @Autowired
    private DashBoardManager dashBoardManager;

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/dashboard/stats")
    public ResponseEntity<DashBoardStatsVo> stats() {
        DashBoardStatsVo dashBoardStatsVo = dashBoardManager.retrieveDashBoardStats();
        return ResponseEntity.status(HttpStatus.OK).body(dashBoardStatsVo);
    }
}
