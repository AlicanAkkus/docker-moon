package com.caysever.dockermoon.service.vo.dashboard;

public class NetworkStatsVo {

    private int totalNetworksCount;
    private int totalBuildInNetworksCount;
    private int totalCustomNetworksCount;

    public int getTotalNetworksCount() {
        return totalNetworksCount;
    }

    public void setTotalNetworksCount(int totalNetworksCount) {
        this.totalNetworksCount = totalNetworksCount;
    }

    public int getTotalBuildInNetworksCount() {
        return totalBuildInNetworksCount;
    }

    public void setTotalBuildInNetworksCount(int totalBuildInNetworksCount) {
        this.totalBuildInNetworksCount = totalBuildInNetworksCount;
    }

    public int getTotalCustomNetworksCount() {
        return totalCustomNetworksCount;
    }

    public void setTotalCustomNetworksCount(int totalCustomNetworksCount) {
        this.totalCustomNetworksCount = totalCustomNetworksCount;
    }
}
