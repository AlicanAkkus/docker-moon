package com.caysever.dockermoon.service.vo.dashboard;

public class ContainerStatsVo {

    private int totalContainersCount;
    private int totalCreatedContainersCount;
    private int totalRunningContainersCount;
    private int totalPausedContainersCount;
    private int totalRestartingContainersCount;
    private int totalExitedContainersCount;

    public int getTotalContainersCount() {
        return totalContainersCount;
    }

    public void setTotalContainersCount(int totalContainersCount) {
        this.totalContainersCount = totalContainersCount;
    }

    public int getTotalCreatedContainersCount() {
        return totalCreatedContainersCount;
    }

    public void setTotalCreatedContainersCount(int totalCreatedContainersCount) {
        this.totalCreatedContainersCount = totalCreatedContainersCount;
    }

    public int getTotalRunningContainersCount() {
        return totalRunningContainersCount;
    }

    public void setTotalRunningContainersCount(int totalRunningContainersCount) {
        this.totalRunningContainersCount = totalRunningContainersCount;
    }

    public int getTotalPausedContainersCount() {
        return totalPausedContainersCount;
    }

    public void setTotalPausedContainersCount(int totalPausedContainersCount) {
        this.totalPausedContainersCount = totalPausedContainersCount;
    }

    public int getTotalRestartingContainersCount() {
        return totalRestartingContainersCount;
    }

    public void setTotalRestartingContainersCount(int totalRestartingContainersCount) {
        this.totalRestartingContainersCount = totalRestartingContainersCount;
    }

    public int getTotalExitedContainersCount() {
        return totalExitedContainersCount;
    }

    public void setTotalExitedContainersCount(int totalExitedContainersCount) {
        this.totalExitedContainersCount = totalExitedContainersCount;
    }
}
