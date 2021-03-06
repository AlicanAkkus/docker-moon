const imagesUrl = "/images/";

$("#image-nav").addClass("active");

toastr.options = {
    "progressBar": true
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

var imagesVue = new Vue({
    el: "#docker-moon-images",
    data: {
        images: [],
        search: '',
        selectedImages: [],
        isSelectedAllImages: false
    },
    methods: {
        getImages: function () {
            this.$http.get(imagesUrl)
                .then(function (response) {
                    this.images = response.data;
                }, function (error) {
                    console.log(error.statusText);
                });
        },
        removeImage: function (image) {
            var parent = this;
            bootbox.confirm({
                message: "Image will be removed. Are you sure?",
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
                        parent.$http.delete(imagesUrl + image.Id).then(function (response) {
                            toastr.success("Image removed successfuly.");
                            parent.getImages();
                        }, function (error) {
                            toastr.warning("An error occured.");
                        });
                    }
                }
            });
        }
    },
    created: function () {
        this.getImages();
    }
});