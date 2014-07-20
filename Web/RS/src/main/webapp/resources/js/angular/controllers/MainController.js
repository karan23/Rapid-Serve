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
					$scope.cust = {};
				}).error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Fetching Customers.");
		});
	};
	$scope.getComplaints = function() {
		$http.get("crud/getAllComplaints").success(
				function(data, status, headers, config) {
					$scope.complaintList = data;
				}).error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Fetching Customers.");
		});
	};
	
	$scope.selectedAddress = '';
	$scope.address = [];
	  $scope.getAddress = function(viewValue) {
	    var params = {address: viewValue, sensor: false};
	    return $http.get('http://maps.googleapis.com/maps/api/geocode/json', {params: params})
	    .then(function(res) {
	    	$scope.address = res.data.results;
	      return res.data.results;
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
		console.log($scope.address[0].geometry.location);
		$scope.cust.address = $scope.cust.address +" "+ $scope.address[0].formatted_address;
		$scope.cust.longitude = $scope.address[0].geometry.location.lng;
		$scope.cust.latitude = $scope.address[0].geometry.location.lat;
		var responsePromise = $http.post("crud/addCustomer", $scope.cust);

		responsePromise.success(function(data, status, headers, config) {
			$scope.custList = data;
			$scope.cust = {};
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
			$scope.complaint = {};
			toaster.pop('success', "success", "Complaint Added.");
		});
		responsePromise.error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Saving Complaint.");
		});
	};
	
	$scope.getNearBy = function(id){
		$http.get("crud/getNearByFA/"+id).success(
				function(data, status, headers, config) {
					//$scope.complaintList = data;
				}).error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Fetching Customers.");
		});
	};
	
	$scope.saveFA = function() {
		console.log($scope.fa);
		var responsePromise = $http.post("crud/addFieldAgent", $scope.fa);

		responsePromise.success(function(data, status, headers, config) {
			$scope.fa = {};
			toaster.pop('success', "success", "Field Agent Added.");
		});
		responsePromise.error(function(data, status, headers, config) {
			toaster.pop('error', "error", "Some error in Saving Field Agent.");
		});
	};

};