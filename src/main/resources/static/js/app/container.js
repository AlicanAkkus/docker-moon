Vue.config.devtools = true;

const containersUrl = "/containers/";

toastr.options = {
    "progressBar": true
}

var containersVue = new Vue({
    el: "#docker-moon-containers",
    data: {
        containers: [],
        selectedContainers: [],
        inspectedContainer: null
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
                this.selectedContainers.splice(containerId);
            }
        },
        selectAllContainers: function () {
            if (this.selectedContainers.length > 0) {
                this.selectedContainers = [];
            }
            this.containers.forEach(function (container) {
                this.selectedContainers.push(container.Id);
            }, this);
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
                        var dialog = bootbox.dialog({
                            message: '<p><i class="fa fa-spin fa-spinner"></i>Please wait...</p>',
                            closeButton: false
                        });
                        parent.$http.get(containersUrl + 'restart/' + container.Id).then(function (response) {
                            toastr.success("Restarted container successfuly.");
                            dialog.modal("hide");
                            container.State = 'running';
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
                        var dialog = bootbox.dialog({
                            message: '<p><i class="fa fa-spin fa-spinner"></i>Please wait...</p>',
                            closeButton: false
                        });
                        parent.$http.get(containersUrl + 'stop/' + container.Id).then(function (response) {
                            toastr.success("Stopped container successfuly");
                            dialog.modal("hide");
                            container.State = 'exited';
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
                        var dialog = bootbox.dialog({
                            message: '<p><i class="fa fa-spin fa-spinner"></i>Please wait...</p>',
                            closeButton: false
                        });
                        parent.$http.get(containersUrl + 'kill/' + container.Id).then(function (response) {
                            dialog.modal('hide');
                            toastr.success("Killed container successfuly");
                            container.State = 'exited';
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
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
        }
    },
    created: function () {
        this.getContainers();
    }
});