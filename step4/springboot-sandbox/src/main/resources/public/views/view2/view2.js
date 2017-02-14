'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/view2', {
      templateUrl: 'views/view2/view2.html', // (better way to redirect? No, The parameters coming to templateUrl are fixed. See http://stackoverflow.com/a/33840304 and use templateProvider)
      controller: 'View2Ctrl'
    });
}])

.controller('View2Ctrl', ['$window', '$scope', 'sharedProperties', 'authentification', 'springauthentification', function($window, $scope, sharedProperties, authentification, springauthentification) {

  // $ httpget
  springauthentification.getRole().then(function(d){
  	$scope.userRole = d.data.role;
  });

  // Redirect if not admin
  //$scope.authentification = springauthentification.validate('admin');

  $scope.sharedVar = sharedProperties.getProperty();

}]);