angular.module('sbAdminApp').controller(
		'TestCaseController',
		[ '$scope', '$location','$http','CommonUtilityService','toaster',
				function($scope, $location, $http, CommonUtilityService, toaster) {
			$scope.toaster ={};
			$scope.testCaseInfo = {};
			$scope.filterInfo = {currentPage : 1};
			$scope.treeId = 'testCaseTreeView';
			$scope.testCaseView = true;
			$scope[$scope.treeId] = {};
			
			$scope[$scope.treeId].selectNodeLabel = function(node){
				if( $scope[$scope.treeId].currentNode && $scope[$scope.treeId].currentNode.selected ) {
					$scope[$scope.treeId].currentNode.selected = undefined;
				}
				node.selected = 'selected';
				$scope[$scope.treeId].currentNode = node;
				
				if(node.detailObj['testStepId'] != undefined && node.detailObj['testStepId'] != null){
					$scope.testCaseView = false;
				}
				else{
					$scope.testCaseView = true;
					$scope.testCaseInfo = node.detailObj;
				}
			};
			
			$scope.selectNode = function(node, $event){
				if($event.target.className.indexOf('tree-icon') > -1)
					return false;
				
				$scope.testCaseInfo = {};
				$scope.testStepInfo = {};
				if(node.detailObj['testStepId'] != undefined && node.detailObj['testStepId'] != null){
					$scope.testCaseView = false;
					$scope.testStepInfo = node.detailObj;
				}
				else{
					$scope.testCaseView = true;
					$scope.testCaseInfo = node.detailObj;
				}
			};
			
			$scope.removeTreeNode = function(node){
				var nodeType = node['isTestCase'];
				var detailObj = node['detailObj'];
				if(nodeType){
					$scope.deleteTestCase(detailObj.recordId);
				}
				else{
					$scope.deleteTestCaseStep(detailObj.recordId);
				}
			};
			
			$scope.deleteTestCase = function(recordID){
				$http.post("deleteTestCase/"+recordID+".json").success( function(response) {
					$scope.listTestCase();
			    });
			};
			
			$scope.deleteTestCaseStep = function(recordID){
				$http.post("deleteTestCaseStep/"+recordID+".json").success( function(response) {
					$scope.listTestCase();
			    });
			};
			
			$scope.addNewNode = function(node){
				$scope.testCaseInfo = {};
				$scope.testStepInfo = {};
				$scope.testStepInfo.keyword = '';
				$scope.testStepInfo.parentRecordKey = node.detailObj['recordId'];
				$scope.testCaseView = false;
			};
			
			$scope.showAddTestCaseForm = function(){
				$scope.testCaseInfo = {};
				$scope.testStepInfo = {};
				$scope.testCaseView = true;
			};
			
			$scope.listTestCase = function()
			{
			    $http.post("testCaseList.json",$scope.filterInfo).success( function(response) {
			        $scope.contents = response.list;
			        $scope.totalItems = response.totalRows;
			        $scope.treeModel = CommonUtilityService.getTreeModelOfTestCaseList($scope.contents);
			    });
			};
			
			$scope.fetchKeywordList = function()
			{
			    $http.post("keywordList.json").success( function(response) {
			        $scope.keywords = response;
			    });
			};
			
			$scope.addTestCase = function(){
				$http.post("addTestCase.json",$scope.testCaseInfo).success( function(response) {
					if(response.status=='SUCCESS')
						$scope.listTestCase();
					else
						$scope.showErrorMsg(response.errors);
			    });
			};
			
			$scope.updateTestCase = function(){
				$http.post("updateTestCase.json",$scope.testCaseInfo).success( function(response) {
					if(response.status=='SUCCESS')
						$scope.listTestCase();
					else
						$scope.showErrorMsg(response.errors);
			    });
			};
			
			$scope.deleteTestCase = function(id){
				$http.post("deleteTestCase/"+id+".json").success( function(response) {
					if(response.status=='SUCCESS')
						$scope.listTestCase();
					else
						$scope.showErrorMsg(response.errors);
			    });
			};
			
			$scope.addTestCaseStep = function() {
				$http.post("addTestCaseStep.json", $scope.testStepInfo)
						.success(function(response) {
							if(response.status=='SUCCESS')
								$scope.listTestCase();
							else
								$scope.showErrorMsg(response.errors);
						});
			};
			
			$scope.updateTestCaseStep = function() {
				$http.post("updateTestCaseStep.json", $scope.testStepInfo)
						.success(function(response) {
							if(response.status=='SUCCESS')
								$scope.listTestCase();
							else
								$scope.showErrorMsg(response.errors);
						});
			};
			
			$scope.deleteTestCaseStep = function(id){
				$http.post("deleteTestCaseStep/"+id+".json").success( function(response) {
					if(response.status=='SUCCESS')
						$scope.listTestCase();
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
	        
			$scope.treeOptions = {
					    accept: function(sourceNode, destNodes, destIndex) {
					    	if(destNodes.$modelValue.length == 0)
					    		return false;
					    	 var data = sourceNode.$modelValue.detailObj; 
					    	 var destType = destNodes.$modelValue[0].detailObj.type; 
					    	 return (data.type == destType); // only accept the same type }
					    }
					  };
			
			$scope.collapseAllTreeNodes = function () {
		        $scope.$broadcast('angular-ui-tree:collapse-all');
		      };

		      $scope.expandAllTreeNodes = function () {
		        $scope.$broadcast('angular-ui-tree:expand-all');
		      };
		      
			$scope.fetchProjectList = function(){
				$http.post("valuelist/PROJECT.json")
				.success(function(response) {
					$scope.projectList = response;
				});
			};
			$scope.toggleTreeNode = function (scope) {
				if(scope.collapsed){
					$scope.collapseAllTreeNodes();
					scope.expand();
				}
				else
					scope.collapse();
		      };
		      
		      $scope.pageChange = function(){
		            $scope.listTestCase();
		       };
			$scope.init = function(){
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
				}];
				
				$scope.fetchProjectList();
				$scope.fetchKeywordList();
				$scope.listTestCase();
			};
			
			$scope.init();
			
				} ]);