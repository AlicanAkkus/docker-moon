package com.caysever.dockermoon.service.vo.dashboard;

public class ImageStatsVo {

    private int totalImagesCount;
    private int totalDanglingImagesCount;

    public int getTotalImagesCount() {
        return totalImagesCount;
    }

    public void setTotalImagesCount(int totalImagesCount) {
        this.totalImagesCount = totalImagesCount;
    }

    public int getTotalDanglingImagesCount() {
        return totalDanglingImagesCount;
    }

    public void setTotalDanglingImagesCount(int totalDanglingImagesCount) {
        this.totalDanglingImagesCount = totalDanglingImagesCount;
    }
}
