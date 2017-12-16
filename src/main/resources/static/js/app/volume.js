const volumesUrl = "/volumes/";

$("#volume-nav").addClass("active");

toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var volumesVue = new Vue({
    el: "#docker-moon-volumes",
    data: {
        volumes: [],
        search: '',
        selectedVolumes: [],
        isSelectedAllVolumes: false
    },
    computed: {
        filteredVolumes(){
            return this.volumes.filter((volume) => {
                return volume.Name.toLowerCase().includes(this.search.toLowerCase());
        });
        }
    },
    methods: {
        getVolumes: function () {
            this.$http.get(volumesUrl)
                .then(function (response) {
                    this.volumes = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        },
        removeVolume: function (volume) {
            var parent = this;
            bootbox.confirm({
                message: "Volume will be removed. Are you sure?",
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
                        parent.$http.delete(volumesUrl + volume.Name).then(function (response) {
                            toastr.success("Volume removed successfuly.");
                            parent.getVolumes();
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        }
    },
    created: function () {
        this.getVolumes();
    }
});