'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', [ 'ngRoute',
		'AngularSpringApp.filters', 'AngularSpringApp.directives', 'ngAnimate',
		'toaster']);

// Declare app level module which depends on filters, and services
App.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/dashboard', {
		templateUrl : 'dashboard/layout'
	});

	

	$routeProvider.otherwise({
		redirectTo : '/dashboard'
	});
} ]);
