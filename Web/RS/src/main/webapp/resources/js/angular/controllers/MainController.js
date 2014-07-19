'use strict';

/**
 * AnswerController
 * 
 * @constructor
 */
var MainCtrl = function($scope, toaster, $location, AppServices, $filter,
		$route, $routeParams, $http) {

	$scope.fa = {};
	$scope.agentList = [];
	$scope.cust = {};
	$scope.custList = {};
	$scope.complaintList =[];
	$scope.complaint = {};
	
	
	$scope.getCustomers = function() {
		$http.get("crud/getAllCustomers").success(
				function(data, status, headers, config) {
					$scope.custList = data;
				}).error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Fetching Customers.");
		});
	};
	
	
	$scope.getAgents = function() {

		$http.get("crud/getAllFieldAgent").success(
				function(data, status, headers, config) {
					$scope.agentList = data;
				}).error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Fetching Agents.");
		});
	};
	
	
	$scope.addCust = function() {
		console.log($scope.fa);
		var responsePromise = $http.post("crud/addCustomer", $scope.cust);

		responsePromise.success(function(data, status, headers, config) {
			$scope.custList = data;
			toaster.pop('success', "success", "Customer Added.");
		});
		responsePromise.error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Saving Customer.");
		});
	};	
	
	
	$scope.saveComplaint = function() {
		console.log($scope.fa);
		var responsePromise = $http.post("crud/addComplaint", $scope.complaint);

		responsePromise.success(function(data, status, headers, config) {
			$scope.complaintList = data;
			toaster.pop('success', "success", "Complaint Added.");
		});
		responsePromise.error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Saving Complaint.");
		});
	};
	
	$scope.saveFA = function() {
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