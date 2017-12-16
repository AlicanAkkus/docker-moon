const volumesUrl = "/swarm/";

$("#swarm-nav").addClass("active");

toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var swarmVue = new Vue({
    el: "#docker-moon-swarm",
    data: {
        swarm: null
    },
    methods: {
        getSwarm: function () {
            this.$http.get(volumesUrl)
                .then(function (response) {
                    this.swarm = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        }
    },
    created: function () {
        this.getSwarm();
    }
});