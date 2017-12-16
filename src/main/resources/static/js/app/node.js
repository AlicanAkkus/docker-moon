const nodesUrl = "/nodes/";

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$("#node-nav").addClass("active");


toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var nodesVue = new Vue({
    el: "#docker-moon-nodes",
    data: {
        nodes: [],
        search: '',
        selectedNodes: [],
        isSelectedAllNodes: false
    },
    computed: {
        filteredNodes(){
            return this.nodes.filter((node) => {
                return node.ID.toLowerCase().includes(this.search.toLowerCase());
        });
        }
    },
    methods: {
        getNodes: function () {
            this.$http.get(nodesUrl)
                .then(function (response) {
                    this.nodes = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        },
        deleteNode: function (node) {
            var parent = this;
            bootbox.confirm({
                message: "Node will be removed(forced). Are you sure?",
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
                        parent.$http.delete(nodesUrl + node.ID).then(function (response) {
                            toastr.success("Node removed successfuly.");
                            parent.getNodes();
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        }
    },
    created: function () {
        this.getNodes();
    }
});