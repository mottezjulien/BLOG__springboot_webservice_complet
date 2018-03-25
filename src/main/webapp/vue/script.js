
const URL_USER = "/webService/user/";

/*function httpGet (url, error) {
  return axios.get(url)
    .then(function (response) {
        //return response.data;
        console.log(response.data);
        return response.data;
    }).catch(error)
}*/

var userListWebVue = null;
var userFormVue = null;


userListWebVue = new Vue({
    el: '#userListWebVue',
    data: {
        list : []
    },
    created: function () {
        this.update();
    },
    methods: {
        update: function () {
            axios.get(URL_USER).then(function (response) {
                userListWebVue.list = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        deleteUser: function (user) {
            axios.delete(URL_USER + user.id).then(function (response) {
                userListWebVue.update();
            }).catch(function (error) {
                console.log(error);
            });
        },
        showCreateForm: function () {
            userFormVue.show({});
        },
        showUpdateForm: function (user) {
            userFormVue.show(user);
        }
      }
});

userFormVue = new Vue({
    el: '#userFormVue',
    data: {
        isShow : false,
        current : {}
    },
    methods: {
        show: function(parameterCurrent) {
            this.current = parameterCurrent;
            this.isShow = true;
        },
        edit: function() {
            axios.post(URL_USER, this.current)
            .then(function (response) {
                userFormVue.isShow = false;
                userListWebVue.update();
            }).catch(function (error) {
                console.log(error);
                userFormVue.isShow = false;
            });
        },
        cancel: function() {
            this.isShow = false;
        }
    }
})

