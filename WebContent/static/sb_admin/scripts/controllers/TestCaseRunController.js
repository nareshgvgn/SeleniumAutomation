angular.module('sbAdminApp').controller(
		'TestCaseRunController',
		[ '$scope', '$location','$http','CommonUtilityService','$sce','$modal',
				function($scope, $location, $http, CommonUtilityService, $sce, $modal) {
			$scope.filterInfo = {currentPage : 1};
			$scope.treeId = 'testCaseTreeView';
			$scope.testCaseView = true;
			$scope.selectedList = {};
			$scope.testRunInfo = {};
			$scope.isTestRunButtonDisable = true;
			$scope.headers = [
			                  {
			                      label: 'Actions',
			                      width : '5',
			                      id : 'selectCheckBox'
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
			                  },{
			                      label: 'Test Steps',
			                      width : '15',
			                      id : ''
			                  }

			              ];
			
			$scope.listTestCase = function()
			{
			    $http.post("testCaseList.json",$scope.filterInfo).success( function(response) {
			        $scope.contents = response.list;
			        $scope.totalItems = response.totalRows;
			    });
			};
			
			$scope.selectDeselectCheckbox = function(content){
				$scope.selectedList[content.recordId] = content.isSelected;
				$scope.isTestRunButtonDisable= $scope.invokeTestRunButtonEnableDisable();
			};
			
			$scope.isSelected = function(content){
				return $scope.selectedList[content.testCaseId] ? $scope.selectedList[content.testCaseId] : false;
			};
			
			$scope.invokeTestRunButtonEnableDisable = function(){
				var disabled = true;
				angular.forEach($scope.selectedList, function(isSelected,testcaseId){
	                if(isSelected){
	                	disabled = false;
	                }
	            });
				return disabled;
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
				$scope.listPropertySets();
			};
			
			$scope.openTestRunModal = function () {
				$scope.testRunInfo.machineName = remoteIP;
				$modal.open({
					templateUrl: "static/templates/testRun.html",
					scope : $scope
				});
			};
			
			$scope.startTestRun = function(){
				$scope.testRunInfo.testCaseIds = $scope.filterArray($scope.selectedList);
				 $http.post("startTestRun.json",$scope.testRunInfo).success( function(response) {
				 });
			};
			
			$scope.listPropertySets = function(){
				 $http.post("valuelist/PROPERTYSET.json",$scope.testCaseInfo).success( function(response) {
				        $scope.propertySetList = response;
				    });
			};
			
			$scope.init();
			
				} ]);