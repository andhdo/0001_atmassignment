(function() {
  'use strict';

  angular
    .module('atmApp')
    .controller('AtmController', AtmController);


  AtmController.$inject = ['$scope', '$http'];

  function AtmController($scope, $http) {
    var vm = this;

    $http.get('/api/atms').then(function(data) {
      console.log('data=', data);
      $scope.wrapper = data;
      vm.error = null;
      vm.success = 'OK';


      let markers = []
      angular.forEach(data.data, function(value, key){
        console.log('to marker=', value);

        markers.push({
          lat: parseFloat(value.address.geoLocation.lat),
          lng: parseFloat(value.address.geoLocation.lng),
          message: value.address.street,
          dragable: false
        })
      })

      angular.extend($scope, {
        markers: markers
      });

    }).catch(function () {
        vm.success = null;
        vm.error = 'ERROR';
    });

  }



})();

