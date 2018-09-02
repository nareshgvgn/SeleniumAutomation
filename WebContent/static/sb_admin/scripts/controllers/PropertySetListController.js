angular.module('sbAdminApp').controller(
		'PropertySetListController',
		[
				'$scope',
				'$location',
				'$http',
				'CommonUtilityService',
				'toaster','$state','$modal',
				function($scope, $location, $http, CommonUtilityService,
						toaster, $state, $modal) {
					
					$scope.propertySetInfo = {};
					$scope.headers = [
					                  {
					                      label: 'Actions',
					                      width : '5',
					                      id : 'actions'
					                  },{
					                      label: 'Set Name',
					                      width : '13',
					                      id : 'name'
					                  },{
					                      label: 'Modified By',
					                      width : '12',
					                      id : 'modifiedBy'
					                  }
					              ];
					
					$scope.listTestCase = function()
					{
					    $http.post("propertySetList.json",$scope.propertySetInfo).success( function(response) {
					        $scope.contents = response.list;
					        $scope.totalItems = response.totalRows;
					    });
					};
					
					
					$scope.createNewSet = function(){
						 $state.go('dashboard.property-set-entry',{id:null});
					};
					
					$scope.editPropertySet = function(id){
						 $state.go('dashboard.property-set-entry',{id: id});
					};
					
					$scope.clonePropertySet = function(id){
						$scope.propertySetInfo = {};
						$scope.propertySetInfo.id = id;
						modalInstance = $modal.open({
							templateUrl: "static/templates/clone-property-set.html",
							scope : $scope,
							windowClass : 'small'
						});
					};
					
					$scope.doClone = function(){
						$http.post("clonePropertySet.json",$scope.propertySetInfo).success( function(response) {
							$scope.listTestCase();
					    });
					};
					
					$scope.init = function(){
						$scope.listTestCase();
					};
					
					$scope.init();
				} ]);