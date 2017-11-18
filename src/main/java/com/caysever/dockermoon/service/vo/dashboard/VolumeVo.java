package com.caysever.dockermoon.service.vo.dashboard;

public class VolumeVo {

    private int totalVolumesCount;
    private int totalWarningsCount;

    public int getTotalVolumesCount() {
        return totalVolumesCount;
    }

    public void setTotalVolumesCount(int totalVolumesCount) {
        this.totalVolumesCount = totalVolumesCount;
    }

    public int getTotalWarningsCount() {
        return totalWarningsCount;
    }

    public void setTotalWarningsCount(int totalWarningsCount) {
        this.totalWarningsCount = totalWarningsCount;
    }
}
