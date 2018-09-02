angular.module('sbAdminApp').service('CommonUtilityService', [ function() {

	this.getTreeModelOfTestCaseList = function(testCaseList) {
		var treeModel = [];
		angular.forEach(testCaseList, function(item){
			var obj = {};
			obj.isTestCase = true;
			obj.label = item.testCaseId + ' - '+(item.testCaseTitle != null ? item.testCaseTitle : '') ;
			obj.nodeId = item.testCaseId;
			obj.detailObj = item;
			obj.children = [];
			angular.forEach(item.lstTestSteps, function(innerItem){
				var innerObj = {};
				innerObj.label = innerItem.testStepId + ' - '+(innerItem.description != null ? innerItem.description : '');
				innerObj.nodeId = innerItem.testStepId;
				innerObj.detailObj = innerItem;
				innerObj.isTestCase = false;
				obj.children.push(innerObj);
			});
			treeModel.push(obj);
		});
		return treeModel;
	};

} ]);