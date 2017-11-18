package com.caysever.dockermoon.service.vo.dashboard;

public class DashBoardStatsVo {

    private ContainerStatsVo containerStatsVo;
    private ImageStatsVo imageStatsVo;
    private NetworkStatsVo networkStatsVo;
    private VolumeVo volumeVo;
    private VersionVo versionVo;
    private InfoVo infoVo;

    public ContainerStatsVo getContainerStatsVo() {
        return containerStatsVo;
    }

    public void setContainerStatsVo(ContainerStatsVo containerStatsVo) {
        this.containerStatsVo = containerStatsVo;
    }

    public ImageStatsVo getImageStatsVo() {
        return imageStatsVo;
    }

    public void setImageStatsVo(ImageStatsVo imageStatsVo) {
        this.imageStatsVo = imageStatsVo;
    }

    public NetworkStatsVo getNetworkStatsVo() {
        return networkStatsVo;
    }

    public void setNetworkStatsVo(NetworkStatsVo networkStatsVo) {
        this.networkStatsVo = networkStatsVo;
    }

    public VolumeVo getVolumeVo() {
        return volumeVo;
    }

    public void setVolumeVo(VolumeVo volumeVo) {
        this.volumeVo = volumeVo;
    }

    public VersionVo getVersionVo() {
        return versionVo;
    }

    public void setVersionVo(VersionVo versionVo) {
        this.versionVo = versionVo;
    }

    public InfoVo getInfoVo() {
        return infoVo;
    }

    public void setInfoVo(InfoVo infoVo) {
        this.infoVo = infoVo;
    }
}
