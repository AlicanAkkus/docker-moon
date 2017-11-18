package com.caysever.dockermoon.service;

import com.caysever.dockermoon.service.vo.dashboard.*;
import com.google.common.collect.ImmutableList;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DashBoardService {

    private Logger logger = LoggerFactory.getLogger(DashBoardService.class);

    @Autowired
    private DockerClient dockerClient;

    public DashBoardStatsVo stats() {
        try {
            DashBoardStatsVo dashBoardStatsVo = new DashBoardStatsVo();

            ContainerStatsVo containerStatsVo = retrieveContainerStats();
            ImageStatsVo imageStatsVo = retrieveImagesStats();
            NetworkStatsVo networkStatsVo = retrieveNetworkStats();
            VersionVo versionVo = retrieveVersionStats();
            InfoVo infoVo = retrieveInfoStats();
            VolumeVo volumeVo = retrieveVolumeStats();


            dashBoardStatsVo.setContainerStatsVo(containerStatsVo);
            dashBoardStatsVo.setImageStatsVo(imageStatsVo);
            dashBoardStatsVo.setNetworkStatsVo(networkStatsVo);
            dashBoardStatsVo.setVolumeVo(volumeVo);
            dashBoardStatsVo.setVersionVo(versionVo);
            dashBoardStatsVo.setInfoVo(infoVo);

            return dashBoardStatsVo;
        } catch (Exception e) {
            logger.error("An error occured while getting dashboard stats. {}", e);
            throw new RuntimeException(e);
        }
    }

    private ContainerStatsVo retrieveContainerStats() throws Exception {


        List<Container> allContainers = dockerClient.listContainers(DockerClient.ListContainersParam.allContainers());
        List<Container> allCreatedContainers = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusCreated());
        List<Container> allRunningContainers = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusRunning());
        List<Container> allPausedContainers = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusPaused());
        List<Container> allRestartingContainers = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusRestarting());
        List<Container> allExitedContainers = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusExited());

        ContainerStatsVo containerStatsVo = new ContainerStatsVo();
        containerStatsVo.setTotalContainersCount(allContainers.size());
        containerStatsVo.setTotalCreatedContainersCount(allCreatedContainers.size());
        containerStatsVo.setTotalRunningContainersCount(allRunningContainers.size());
        containerStatsVo.setTotalPausedContainersCount(allPausedContainers.size());
        containerStatsVo.setTotalRestartingContainersCount(allRestartingContainers.size());
        containerStatsVo.setTotalExitedContainersCount(allExitedContainers.size());

        return containerStatsVo;
    }

    private ImageStatsVo retrieveImagesStats() throws Exception {
        List<Image> allImages = dockerClient.listImages(DockerClient.ListImagesParam.allImages());
        List<Image> danglingImages = dockerClient.listImages(DockerClient.ListImagesParam.danglingImages());

        ImageStatsVo imageStatsVo = new ImageStatsVo();
        imageStatsVo.setTotalImagesCount(allImages.size());
        imageStatsVo.setTotalDanglingImagesCount(danglingImages.size());

        return imageStatsVo;
    }

    private NetworkStatsVo retrieveNetworkStats() throws Exception {
        List<Network> allNetworks = dockerClient.listNetworks();
        List<Network> buildInNetworks = dockerClient.listNetworks(DockerClient.ListNetworksParam.builtInNetworks());
        List<Network> customNetworks = dockerClient.listNetworks(DockerClient.ListNetworksParam.customNetworks());

        NetworkStatsVo networkStatsVo = new NetworkStatsVo();
        networkStatsVo.setTotalNetworksCount(allNetworks.size());
        networkStatsVo.setTotalBuildInNetworksCount(buildInNetworks.size());
        networkStatsVo.setTotalCustomNetworksCount(customNetworks.size());

        return networkStatsVo;
    }

    private VersionVo retrieveVersionStats() throws Exception {
        Version version = dockerClient.version();

        VersionVo versionVo = new VersionVo();
        versionVo.setApiVersion(version.apiVersion());
        versionVo.setDockerVersion(version.version());
        versionVo.setGitCommit(version.gitCommit());
        versionVo.setOs(version.os());

        return versionVo;
    }

    private InfoVo retrieveInfoStats() throws Exception {
        Info info = dockerClient.info();

        InfoVo infoVo = new InfoVo();
        infoVo.setId(info.id());
        infoVo.setCpus(info.cpus());
        infoVo.setName(info.name());
        infoVo.setImages(info.images());
        infoVo.setContainers(info.containers());
        infoVo.setMemory(info.memTotal() * Math.pow(10, -9));
        infoVo.setDockerRootDir(info.dockerRootDir());
        infoVo.setStorageDriver(info.storageDriver());
        infoVo.setKernelVersion(info.kernelVersion());
        infoVo.setServerVersion(info.serverVersion());

        return infoVo;
    }

    private VolumeVo retrieveVolumeStats() throws Exception {
        VolumeList volumeList = dockerClient.listVolumes();
        Optional<ImmutableList<Volume>> volumeImmutableList = Optional.ofNullable(volumeList.volumes());
        Optional<ImmutableList<String>> warningsImmutableList = Optional.ofNullable(volumeList.warnings());

        VolumeVo volumeVo = new VolumeVo();

        volumeImmutableList.ifPresent(volumes -> volumeVo.setTotalVolumesCount(volumes.size()));
        warningsImmutableList.ifPresent(warnings -> volumeVo.setTotalWarningsCount(warnings.size()));

        return volumeVo;
    }
}
