const containersUrl = "/containers/";

$("#container-nav").addClass("active");

toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var containersVue = new Vue({
    el: "#docker-moon-containers",
    data: {
        containers: [],
        selectedContainers: [],
        isSelectedAllContainers: false,
        search: '',
        containerLogs: {
            command: '',
            logs : []
        }
    },
    computed: {
        filteredContainers(){
            return this.containers.filter((container) => {
                return container.Names[0].toLowerCase().includes(this.search.toLowerCase());
            });
        }
    },
    methods: {
        getContainers: function () {
            this.$http.get(containersUrl)
                .then(function (response) {
                    this.containers = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        },
        getContainerState: function (state) {
            switch (state) {
                case 'running':
                    return 'badge-success';
                case 'paused':
                    return 'badge-warning';
                case 'exited':
                    return 'badge-danger';
            }
        },
        getContainerPorts: function (container) {
            var ports = [];
            if (container.Ports != null && container.Ports.length > 0) {
                for (port in container.Ports) {
                    ports.push(container.Ports[port].Type + ' ' + container.Ports[port].PrivatePort + ':' + container.Ports[port].PublicPort);
                }
            }

            return ports.toString();
        },
        containerSelect: function (containerId) {
            var indexOfContainer = this.selectedContainers.indexOf(containerId);

            if (indexOfContainer === -1) {
                this.selectedContainers.push(containerId);
            } else {
                this.selectedContainers.splice(indexOfContainer, 1);
            }
        },
        doActionsForContainers: function (type, action) {
            var parent = this;
            var message = "Container(s) will be " + type + ". Are you sure?";
            bootbox.confirm({
                message: message,
                buttons: {
                    confirm: {
                        label: 'Yes, I\'m sure!',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: 'Not yet :(',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result) {
                        parent.$http
                            .post(containersUrl + action, parent.selectedContainers)
                            .then(function (response) {
                                console.log(response);
                                toastr.success(type.capitalize() + " finished successfuly.");
                                parent.getContainers();
                            }, function (error) {
                                console.log(error);
                                toastr.warning("An error occured.");
                            });
                    }
                }
            });
        },
        inspectContainer: function (container) {
            this.$http.get(containersUrl + container.Id).then(function (response) {
                this.inspectedContainer = response.data;
            }, function (error) {
                console.log(error.statusText);
            });
        },
        restartContainer: function (container) {
            var parent = this;
            bootbox.confirm({
                message: "Container will be restarted. Are you sure?",
                buttons: {
                    confirm: {
                        label: 'Yes, I\'m sure!',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: 'Not yet :(',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result) {
                        parent.$http.get(containersUrl + 'restart/' + container.Id).then(function (response) {
                            toastr.success("Restarted container successfuly.");
                            parent.getContainers();
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        },
        stopContainer: function (container) {
            var parent = this;
            bootbox.confirm({
                message: "Container will be stopped. Are you sure?",
                buttons: {
                    confirm: {
                        label: 'Yes, I\'m sure!',
                        className: 'btn-success',
                    },
                    cancel: {
                        label: 'Not yet :(',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result) {
                        parent.$http.get(containersUrl + 'stop/' + container.Id).then(function (response) {
                            toastr.success("Stopped container successfuly");
                            parent.getContainers();
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        },
        killContainer: function (container) {
            var parent = this;
            bootbox.confirm({
                message: "Container will be killed after 10 seconds. Are you sure?",
                buttons: {
                    confirm: {
                        label: 'Yes, I\'m sure!',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: 'Not yet :(',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result) {
                        parent.$http.get(containersUrl + 'kill/' + container.Id).then(function (response) {
                            toastr.success("Killed container successfuly");
                            parent.getContainers();
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        },
        logContainer: function (container) {
            this.$http.get(containersUrl + 'log/' + container.Id).then(function (response) {
                this.containerLogs.command = "docker container logs " + container.Id.substr(0,8);
                this.containerLogs.logs = response.data.data.split("\n");
                $('#container-log-modal').modal('show');
            }, function (error) {
            });
        },
        isStopableContainer: function (container) {
            switch (container.State) {
                case 'running':
                    return true;
                case 'paused':
                    return false;
                case 'exited':
                    return false;
            }
        },
        isPausableContainer: function (container) {
            switch (container.State) {
                case 'running':
                    return true;
                case 'paused':
                    return false;
                case 'exited':
                    return false;
            }
        },
        isRestartableContainer: function (container) {
            switch (container.State) {
                case 'running':
                    return true;
                case 'paused':
                    return true;
                case 'exited':
                    return true;
            }
        },
        isKillableContainer: function (container) {
            if(container.State !== 'exited')
                return true;
        },
        isStatsViewable: function (container) {
            if(container.State !== 'exited')
                return true;
        }
    },
    created: function () {
        this.getContainers();
    },
    watch: {
        'isSelectedAllContainers': function (newVal, oldVal) {
            if(newVal){
                // select all container
                containersVue.selectedContainers = [];
                containersVue.containers.forEach(function (container) {
                    this.selectedContainers.push(container.Id);
                }, containersVue);
            }else{
                // deselect all container
                containersVue.selectedContainers = [];
            }
        }
    }
});