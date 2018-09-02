angular.module('sbAdminApp').controller(
		'TestResultController',
		[ '$scope', '$location','$http','CommonUtilityService','$sce','$filter','$state',
				function($scope, $location, $http, CommonUtilityService, $sce, $filter, $state) {
			
			$scope.treeId = 'testCaseTreeView';
			$scope.testCaseView = true;
			$scope.selectedList = {};
			$scope.testRunInfo = {};
			$scope.headers = [
			                  {
			                      label: 'Actions',
			                      width : '5',
			                      id : ''
			                  },{
			                      label: 'Title',
			                      width : '12',
			                      id : 'title',
			                  },{
			                      label: 'Start Time',
			                      width : '17',
			                      id : 'startTime',
			                      filter : 'date',
			                      filterArgs : 'medium'
			                  },{
			                      label: 'End Time',
			                      width : '17',
			                      id : 'endTime',
			                      filter : 'date',
			                      filterArgs : 'medium'
			                  },{
			                      label: 'Browser',
			                      width : '10',
			                      id : 'browser'
			                  },{
			                      label: 'Executed By',
			                      width : '12',
			                      id : 'testRunBy'
			                  },
			                  {
			                      label: 'Status',
			                      width : '12',
			                      id : 'status'
			                  }

			              ];
			
			$scope.filterText = function(value, filter, args) {
				if(filter == null || filter == undefined)
					return value;
				  return $filter(filter)(value, args);
			};
			
			$scope.viewTestCases = function(testRunID){
				 $state.go('dashboard.test-case-result', {"testRunID": testRunID});
			};
			
			$scope.listTestCase = function()
			{
			    $http.post("testResultList.json",$scope.testRunInfo).success( function(response) {
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