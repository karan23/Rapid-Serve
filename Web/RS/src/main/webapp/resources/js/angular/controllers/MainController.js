'use strict';

/**
 * AnswerController
 * 
 * @constructor
 */
var MainCtrl = function($scope, toaster, $location,
		AppServices, $filter, $route, $routeParams,$http ) {
	
	$scope.fa = {};
	
	$scope.saveFA = function(){
		console.log($scope.fa);
		var responsePromise = $http.post("fa/addFieldAgent", $scope.fa);

        responsePromise.success(function(data, status, headers, config) {
            $scope.myData.fromServer = data.title;
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
	};
	
};