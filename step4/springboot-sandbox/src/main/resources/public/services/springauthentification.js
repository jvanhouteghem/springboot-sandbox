
angular.module('myApp')

.factory('springauthentification', function($window, $http){
	
	var user;
	
    return {
    	getRole: function () {
            return $http
            	.get('/user', { cache: true})
            	.then(function(response){
            		user = response;
            		return user;
            });
        },
        validate: function(requiredRole){

        	try { // user undefined
        		//console.log("requiredRole " + requiredRole + " - " + "user.data.role " + user.data.role)
	        	if (requiredRole == 'admin' && requiredRole != user.data.role){
	        		$window.location.href = 'index.html#!/view1';
	        		return false;
	        	} else {
	        		return true;
	        	}
        	}
        	catch(err) {
        	    //console.log(err.message);
        	}
        }
    };  
});