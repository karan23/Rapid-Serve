'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', [ 'ngRoute', 'ngAnimate',
		'toaster','mgcrea.ngStrap']);

//Declare app level module which depends on filters, and services
App.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/dashboard', {
		templateUrl : 'dashboard/layout'
	});
	
	$routeProvider.when('/fa/manage', {
		templateUrl : 'fa/manage'
	});
	
	$routeProvider.when('/customer/manage', {
		templateUrl : 'customer/manage'
	});	
	$routeProvider.when('/complaint/manage', {
		templateUrl : 'complaint/manage'
	});
	
	$routeProvider.otherwise({
		redirectTo : '/dashboard'
	});
}]);
