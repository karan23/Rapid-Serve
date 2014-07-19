'use strict';

angular.module('AngularSpringApp').service('AppServices', function() {
	var imageUrl = {url: ''};
	var classLevel = {level:''};
	
	var passage = {obj:''};
	
	this.setPassage = function(passage){
		passage.obj = passage;
	};
	
	this.getPassage = function(){
		return passage;
	}
	
	
	this.setClassLevel = function(classLevel){
		console.log("Services"+classLevel);
		classLevel.level = classLevel;
		};
	this.getClassLevel = function(){return classLevel;};
	this.setImageUrl = function(url){imageUrl.url=url;};
	this.getImageUrl = function(){return imageUrl;};
	
	var mycontent ={};
	var content = {
		current : 'content'
	};
	this.getContentHeader = function() {
		return content;
	};
	this.setContentHeader = function(current) {
		content.current = current;
	};
});
