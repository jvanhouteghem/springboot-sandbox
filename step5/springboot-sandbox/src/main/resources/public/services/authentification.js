
angular.module('myApp')

.factory('authentification', function($window, $http){

    return {
        readToken: function () {
            return $window.localStorage.getItem('Token');
        },
        setToken: function() {
            var fakeToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ';
            $window.localStorage.setItem('Token', fakeToken);
        },
        deleteToken: function() {
            $window.localStorage.removeItem('Token');
        },
        verrifyToken: function(){
            return this.readToken() == null ? false : true;
        },
        validate: function(requiredToken){
            if (this.verrifyToken() == false){
                if (requiredToken == 'admin'){
                    $window.location.href = 'index.html#!/view1';
                }
                return false;
            } 
            return true;
        }
    };  
});