const networksUrl = "/networks/";

$("#network-nav").addClass("active");

toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var networksVue = new Vue({
    el: "#docker-moon-networks",
    data: {
        networks: [],
        search: '',
        selectedNetworks: [],
        isSelectedAllNetworks: false
    },
    computed: {
        filteredNetworks(){
            return this.networks.filter((network) => {
                return network.Name.toLowerCase().includes(this.search.toLowerCase());
        });
        }
    },
    methods: {
        getNetworks: function () {
            this.$http.get(networksUrl)
                .then(function (response) {
                    this.networks = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        },
        removeNetwork: function (network) {
            var parent = this;
            bootbox.confirm({
                message: "Network will be removed. Are you sure?",
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
                        parent.$http.delete(networksUrl + network.Id).then(function (response) {
                            toastr.success("Network removed successfuly.");
                            parent.getNetworks();
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        }
    },
    created: function () {
        this.getNetworks();
    }
});