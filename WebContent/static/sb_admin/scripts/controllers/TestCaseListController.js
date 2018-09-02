angular.module('sbAdminApp').controller(
		'TestCaseListController',
		[ '$scope', '$location', '$http',
				function($scope, $location, $http) {
			
			$scope.testCaseInfo = {};
			$scope.headers = [
			                  {
			                      label: 'Actions',
			                      width : '10'

			                  },{
			                      label: 'Test Case ID',
			                      width : '13'

			                  },{
			                      label: 'Title',
			                      width : '12'
			                  },{
			                      label: 'Description',
			                      width : '16'
			                  },{
			                      label: 'Project',
			                      width : '10'
			                  },{
			                      label: 'Component',
			                      width : '10'
			                  },{
			                      label: 'Test Steps',
			                      width : '15'
			                  }

			              ];
			
			$scope.listTestCase = function()
			{
			    $http.post("testCaseList.json",$scope.testCaseInfo).success( function(response) {
			        $scope.contents = response.list;
			        $scope.totalItems = response.totalRows;
			    });

			    //$scope.contents = leaveService.searchLeaves($scope.leaveInfo);
			};
			
			$scope.addTestCase = function(){
				$http.post("addTestCase.json",$scope.testCaseInfo).success( function(response) {
			    });
			};
			
			
			$scope.showEditTestCase = function(testCaseObj){
				
			}; 
			$scope.listTestCase();
			
				} ]);