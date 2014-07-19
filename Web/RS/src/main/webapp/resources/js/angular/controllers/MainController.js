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
		var responsePromise = $http.post("crud/addFieldAgent", $scope.fa);

        responsePromise.success(function(data, status, headers, config) {
        	toaster.pop('success', "success", "Field Agent Added.");
        });
        responsePromise.error(function(data, status, headers, config) {
        	toaster.pop('error', "error", "Some error in Saving Field Agent.");
        });
	};
	
};