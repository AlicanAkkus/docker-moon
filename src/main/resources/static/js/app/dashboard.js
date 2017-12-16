const dashBoardUrl = "/dashboard/stats";

$("#dashboard-nav").addClass("active");

var dashboardVue = new Vue({
   el: "#docker-moon-dashboard",
    data: {
         dashboard : {}
    },
    methods:{
        getDashBoardStats: function(){
            this.$http.get(dashBoardUrl).then(function(response){
                this.dashboard = response.data;
            }, function(error){
                console.log(error.statusText);
            });
        }
    },
    created: function () {
        this.getDashBoardStats();
    }
});