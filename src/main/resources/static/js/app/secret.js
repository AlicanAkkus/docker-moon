const secretsUrl = "/secrets/";

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$("#secret-nav").addClass("active");


toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var configVue = new Vue({
    el: "#docker-moon-secrets",
    data: {
        secrets: [],
        search: '',
        selectedSecrets: [],
        isSelectedAllSecrets: false
    },
    computed: {
        filteredSecrets(){
            return this.secrets.filter((secret) => {
                return secret.Spec.Name.toLowerCase().includes(this.search.toLowerCase());
            });
        }
    },
    methods: {
        getSecrets: function () {
            this.$http.get(secretsUrl)
                .then(function (response) {
                    this.secrets = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        },
        deleteSecret: function (secret) {
            var parent = this;
            bootbox.confirm({
                message: "Secret will be removed. Are you sure?",
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
                        parent.$http.delete(secretsUrl + secret.ID).then(function (response) {
                            if(response.data.status === 'success'){
                                toastr.success("Secret removed successfuly.");
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
        this.getSecrets();
    }
});