angular.module('sbAdminApp').controller(
		'TestCaseResultController',
		[ '$scope', '$location','$http','CommonUtilityService','$sce','$filter', '$stateParams', '$state',
				function($scope, $location, $http, CommonUtilityService, $sce, $filter, $stateParams, $state) {
			
			$scope.testRunInfo = {};
			$scope.testRunInfo.testRunID = $stateParams.testRunID;
			$scope.headers = [
			                  {
			                      label: 'Actions',
			                      width : '5',
			                      id : 'title'
			                  },{
			                	  label: 'Test Case ID',
			                      width : '13',
			                      id : 'testCaseId'
			                  },{
			                      label: 'Title',
			                      width : '12',
			                      id : 'testCaseTitle',
			                  },{
			                      label: 'Description',
			                      width : '16',
			                      id : 'testcaseDescription'
			                  },{
			                      label: 'Component',
			                      width : '10',
			                      id : 'component',
			                  },
			                  {
			                      label: 'Status',
			                      width : '15',
			                      id : 'status'
			                  }

			              ];
			
			$scope.filterText = function(value, filter) {
				if(filter == null || filter == undefined)
					return value;
				  return $filter(filter)(value);
			};
			
			$scope.viewTestCaseSteps = function(testCaseRecordID){
				 $state.go('dashboard.test-case-steps-result', {"testcaseRecordID": testCaseRecordID, "testRunID" : $scope.testRunInfo.testRunID});
			};
			
			$scope.listTestCase = function()
			{
				 $http.post("testCaseResultList.json",$scope.testRunInfo).success( function(response) {
				        $scope.contents = response.list;
				        $scope.totalItems = response.totalRows;
				    });
			};
			
			$scope.selectDeselectCheckbox = function(content){
				$scope.selectedList[content.recordId] = content.isSelected;
			};
			
			$scope.isSelected = function(content){
				return $scope.selectedList[content.testCaseId] ? $scope.selectedList[content.testCaseId] : false;
			};
			
			$scope.filterArray = function(){
				$scope.selectedArray = [];
				 angular.forEach($scope.selectedList, function(isSelected,testcaseId){
		                if(isSelected){
		                	$scope.selectedArray.push(testcaseId);
		                }
		            });
				 return $scope.selectedArray;
			};
			
			$scope.init = function(){
				$scope.keywords = [ {
					"value" : "CLICK",
					"description" : "Click"
				}, {
					"value" : "SETTEXT",
					"description" : "Set Text"
				}, {
					"value" : "GOTOURL",
					"description" : "Go To URL"
				}, {
					"value" : "GETTEXT",
					"description" : "Get Text"
				} ];
				
				$scope.objectTypes = [ {
					"value" : "XPATH",
					"description" : "Xpath"
				}, {
					"value" : "CLASSNAME",
					"description" : "Class Name"
				}, {
					"value" : "NAME",
					"description" : "By Name"
				}, {
					"value" : "LINK",
					"description" : "Link"
				},{
					"value" : "ID",
					"description" : "Id"
				},{
					"value" : "PARTIALLINK",
					"description" : "Partial Link"
				} ];
				$scope.listTestCase();
			};
			
			$scope.startTestRun = function(){
				$scope.testRunInfo.testCaseIds = $scope.filterArray($scope.selectedList);
				 $http.post("startTestRun.json",$scope.testRunInfo).success( function(response) {
				 });
			};
			
			$scope.init();
			
				} ]);