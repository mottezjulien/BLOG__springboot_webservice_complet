'use strict';

const URL_USER = "/webService/user/";

const MESSAGE_ID_SHOW_EDIT_USER = 'SHOW_EDIT_USER'
const MESSAGE_ID_UPDATE_LIST = 'UPDATE_LIST'

var userApp = angular.module('userApp', []);

userApp.controller('userListWebController', [ '$scope', '$rootScope', '$http', function($scope, $rootScope, $http) {
	$scope.list = [];

	$scope.updateList = function(){
        $http.get(URL_USER).success(function(response) {
            $scope.list = response;
        }).error(function(error){
            console.log("error:" + error);
        });
    }

    $scope.updateList();

    $rootScope.$on(MESSAGE_ID_UPDATE_LIST, function(event, user){
        $scope.updateList();
    });

    $scope.showCreateUser = function(){
        $scope.$emit(MESSAGE_ID_SHOW_EDIT_USER, {});
    }

    $scope.updateUser = function(user){
        $scope.$emit(MESSAGE_ID_SHOW_EDIT_USER, user);
    }

    $scope.deleteUser = function(user){
        $http({
            method: 'DELETE',
            url: URL_USER + user.id,
        }).success(function (data) {
            $scope.updateList();
        }).error(function (error) {
            console.log("error:" + error);
        });
    }

} ]);

userApp.controller('userEditFormController', [ '$scope', '$rootScope', '$http', function($scope, $rootScope, $http) {

    $scope.show = false;
    $scope.current = {};

    $rootScope.$on(MESSAGE_ID_SHOW_EDIT_USER, function(event, user){
        $scope.current = user;
        $scope.show = true;
    });

    $scope.edit = function(){
        $http({
            method: 'POST',
            headers: [{'Content-Type': 'application/json'}],
            url: URL_USER,
            data: $scope.current,
        }).success(function (data) {
            $scope.show = false;
            $scope.$emit(MESSAGE_ID_UPDATE_LIST);
        }).error(function (error) {
            console.log("error:" + error);
        });
    }

    $scope.cancel = function(){
        $scope.show = false;
    }

} ]);
