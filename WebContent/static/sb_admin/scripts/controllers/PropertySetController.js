angular.module('sbAdminApp').controller(
		'PropertySetController',
		[
				'$scope',
				'$location',
				'$http',
				'CommonUtilityService',
				'toaster','$stateParams','$modal',
				function($scope, $location, $http, CommonUtilityService,
						toaster, $stateParams, $modal) {
					var modalInstance = null;
					$scope.toaster = {};
					$scope.propertySetInfo = {};
					$scope.propertySetInfo.id = $stateParams.id;
					if($scope.propertySetInfo.id == ''){
						$scope.propertySetInfo.id = null;
					}
					$scope.headers = [
					                  {
					                      label: 'Actions',
					                      width : '5',
					                      id : 'actions'
					                  },{
					                      label: 'Property Name',
					                      width : '13',
					                      id : 'propertyName'
					                  },{
					                      label: 'Input Data/Expression',
					                      width : '12',
					                      id : 'propertyValue'
					                  }
					              ];
					
					$scope.listTestCase = function()
					{
					    $http.post("propertyList/"+$scope.propertySetInfo.id+".json", $scope.propertySetInfo).success( function(response) {
					        $scope.contents = response.list;
					        $scope.totalItems = response.totalRows;
					    });
					};
					
					$scope.fetchPropertySetInfo = function(){
						$http.post("propertySet/"+$scope.propertySetInfo.id+".json", $scope.propertySetInfo).success( function(response) {
							$scope.propertySetInfo = response;
					    });
					};
					
					$scope.savePropertySet = function(){
						$http.post("addPropertySet.json",$scope.propertySetInfo).success( function(response) {
							if(response.status=='SUCCESS'){
								$scope.propertySetInfo.id = response.infoMessage;
								$scope.listTestCase();
							}
							else
								$scope.showErrorMsg(response.errors);
					    });
					};
					
					$scope.updatePropertySet = function(){
						$http.post("updatePropertySet.json",$scope.propertySetInfo).success( function(response) {
							if(response.status=='SUCCESS'){
								$scope.propertySetInfo.id = response.infoMessage;
								$scope.listTestCase();
							}
							else
								$scope.showErrorMsg(response.errors);
					    });
					};
					
					$scope.openPropertyEntryPopup = function (propertyName, propertyValue) {
						$scope.propertyInfo = {};
						$scope.propertyInfo.parentRecordKey = $scope.propertySetInfo.id;
						$scope.propertyInfo.isNew = false;
						$scope.propertyInfo.propertyName = propertyName;
						$scope.propertyInfo.propertyValue = propertyValue;
						
						if(null == propertyName){
							$scope.propertyInfo.isNew = true;
						}
						modalInstance = $modal.open({
							templateUrl: "static/templates/propertyEntry.html",
							scope : $scope,
							windowClass : 'small'
						});
					};
					
					$scope.saveProperty = function(){
						$scope.entryInfo = {};
						$scope.entryInfo.parentRecordKey = $scope.propertyInfo.parentRecordKey;
						$scope.entryInfo.propertyName = $scope.propertyInfo.propertyName;
						$scope.entryInfo.propertyValue = $scope.propertyInfo.propertyValue;
						
						$http.post("addProperty.json",$scope.entryInfo).success( function(response) {
							if(response.status=='SUCCESS'){
								$scope.listTestCase();
								modalInstance.close();
							}
							else
								$scope.showErrorMsg(response.errors);
					    });
					};
					
					$scope.updateProperty = function(){
						$scope.entryInfo = {};
						$scope.entryInfo.parentRecordKey = $scope.propertyInfo.parentRecordKey;
						$scope.entryInfo.propertyName = $scope.propertyInfo.propertyName;
						$scope.entryInfo.propertyValue = $scope.propertyInfo.propertyValue;
						
						$http.post("updateProperty.json",$scope.entryInfo).success( function(response) {
							if(response.status=='SUCCESS'){
								$scope.listTestCase();
								modalInstance.close();
							}
							else
								$scope.showErrorMsg(response.errors);
					    });
					};
					
					$scope.showErrorMsg = function(errors){

			            toaster.clear();
			            $scope.toaster.errors = {"errorList" : errors};

			            toaster.pop('error', "Errors ", "{template: 'static/templates/errors.html', data: toaster.errors}", 15000, 'templateWithData');
			            //  toaster.pop('error', "Errors ", "{template: 'assets/partials/errors.html', data: bar}", 0, 'templateWithData');

			        };
			        
					$scope.init = function(){
						if($scope.propertySetInfo.id != null && $scope.propertySetInfo.id != ''){
							$scope.fetchPropertySetInfo();
							$scope.listTestCase();
						}
					};
					
					$scope.init();
					
				} ]);