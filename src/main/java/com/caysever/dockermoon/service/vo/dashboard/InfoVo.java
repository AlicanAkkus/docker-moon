package com.caysever.dockermoon.service.vo.dashboard;

public class InfoVo {

    private String id;
    private String name;
    private String storageDriver;
    private String kernelVersion;
    private String serverVersion;
    private String dockerRootDir;
    private int cpus;
    private double memory;
    private int images;
    private int containers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorageDriver() {
        return storageDriver;
    }

    public void setStorageDriver(String storageDriver) {
        this.storageDriver = storageDriver;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        this.kernelVersion = kernelVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getDockerRootDir() {
        return dockerRootDir;
    }

    public void setDockerRootDir(String dockerRootDir) {
        this.dockerRootDir = dockerRootDir;
    }

    public int getCpus() {
        return cpus;
    }

    public void setCpus(int cpus) {
        this.cpus = cpus;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getContainers() {
        return containers;
    }

    public void setContainers(int containers) {
        this.containers = containers;
    }
}
