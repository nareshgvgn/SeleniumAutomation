angular.module('sbAdminApp').controller(
		'TestCaseStepResultController',
		[ '$scope', '$location','$http','CommonUtilityService','$sce','$stateParams', '$modal',
				function($scope, $location, $http, CommonUtilityService, $sce, $stateParams, $modal) {
			
			$scope.testRunInfo = {};
			
			$scope.testRunInfo = {};
			$scope.testRunInfo.testRunID = $stateParams.testRunID;
			$scope.testRunInfo.testCaseId = $stateParams.testcaseRecordID;
			$scope.headers = [
			                  {
			                      label: 'Id',
			                      width : '5',
			                      id : 'testStepId'
			                  },{
			                      label: 'Description',
			                      width : '16',
			                      id : 'description'
			                  },{
			                      label: 'Details',
			                      width : '12',
			                      id : 'details',
			                  },{
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
			
			$scope.listTestCase = function()
			{
			    $http.post("testStepResultList.json",$scope.testRunInfo).success( function(response) {
			        $scope.contents = response.list;
			        $scope.totalItems = response.totalRows;
			    });
			};
			
			$scope.getTestCaseInfo = function(){
				$http.post("testcase/"+$scope.testRunInfo.testCaseId+".json",$scope.testRunInfo).success( function(response) {
					$scope.testCaseInfo = response;
			    });
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
				$scope.getTestCaseInfo();
			};
			
			$scope.openModalImage = function (imageSrc, imageDescription) {
				$modal.open({
					templateUrl: "static/templates/modalImage.html",
					resolve: {
						imageSrcToUse: function () {
							return imageSrc;
						},
						imageDescriptionToUse: function () {
							return imageDescription;
						}
					},
					controller: [
					  "$scope", "imageSrcToUse", "imageDescriptionToUse",
						function ($scope, imageSrcToUse, imageDescriptionToUse) {
							$scope.ImageSrc = imageSrcToUse;
							return $scope.ImageDescription = imageDescriptionToUse;
					  }
					]
				});
			};
			
			$scope.init();
			
				} ]);