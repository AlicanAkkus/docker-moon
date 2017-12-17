const configsUrl = "/configs/";

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$("#config-nav").addClass("active");


toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var configVue = new Vue({
    el: "#docker-moon-configs",
    data: {
        configs: [],
        search: '',
        selectedConfigs: [],
        isSelectedAllConfigs: false
    },
    computed: {
        filteredConfigs(){
            return this.configs.filter((config) => {
                return config.Spec.Name.toLowerCase().includes(this.search.toLowerCase());
            });
        }
    },
    methods: {
        getConfigs: function () {
            this.$http.get(configsUrl)
                .then(function (response) {
                    this.configs = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        },
        deleteConfig: function (config) {
            var parent = this;
            bootbox.confirm({
                message: "Config will be removed. Are you sure?",
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
                        parent.$http.delete(configsUrl + config.ID).then(function (response) {
                            if(response.data.status === 'success'){
                                toastr.success("Config removed successfuly.");
                                parent.getConfigs();
                            }else{
                                toastr.warning(response.data.errorMessage);
                            }
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        }
    },
    created: function () {
        this.getConfigs();
    }
});