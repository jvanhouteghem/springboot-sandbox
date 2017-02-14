
angular.module('myApp')

.factory('sharedProperties', function($http){

    var property = 'Value of varFromService1';

    return {
        getProperty: function () {
            return property;
        },
        setProperty: function(value) {
            console.log("call setProperty");
            property = value;
            //$scope.$apply();
        },
        getPropertyFromJson: function(){
        	return $http.get('/automaj_client/user', { cache: true});
            //return $http.get('https://jsonplaceholder.typicode.com/users', { cache: true});
        }
    };  
});