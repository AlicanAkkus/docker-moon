package com.caysever.dockermoon.manager;

import com.caysever.dockermoon.service.DashBoardService;
import com.caysever.dockermoon.service.vo.dashboard.DashBoardStatsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardManager {

    @Autowired
    DashBoardService dashBoardService;

    public DashBoardStatsVo retrieveDashBoardStats(){
        return dashBoardService.stats();
    }
}
