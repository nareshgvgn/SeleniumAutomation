'use strict';
/**
 * @ngdoc overview
 * @name sbAdminApp
 * @description
 * # sbAdminApp
 *
 * Main module of the application.
 */
angular
  .module('sbAdminApp', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'angular-loading-bar',
    'ui.tree','ngSanitize','toaster','ngAnimate'
  ])
  .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {
    
    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/dashboard/home');

    $stateProvider
      .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'static/views/dashboard/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'sbAdminApp',
                    files:[
                    'static/sb_admin/scripts/directives/header/header.js',
                    'static/sb_admin/scripts/directives/header/header-notification/header-notification.js',
                    'static/sb_admin/scripts/directives/sidebar/sidebar.js',
                    'static/sb_admin/scripts/directives/sidebar/sidebar-search/sidebar-search.js'
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["static/sb_admin/bower_components/angular-toggle-switch/angular-toggle-switch.min.js",
                          "static/sb_admin/bower_components/angular-toggle-switch/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['static/sb_admin/bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['static/sb_admin/bower_components/angular-cookies/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['static/sb_admin/bower_components/angular-resource/angular-resource.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['static/sb_admin/bower_components/angular-sanitize/angular-sanitize.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home',
        controller: 'MainCtrl',
        templateUrl:'static/views/dashboard/home.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'sbAdminApp',
              files:[
              'static/sb_admin/scripts/controllers/main.js',
              'static/sb_admin/scripts/directives/timeline/timeline.js',
              'static/sb_admin/scripts/directives/notifications/notifications.js',
              'static/sb_admin/scripts/directives/chat/chat.js',
              'static/sb_admin/scripts/directives/dashboard/stats/stats.js'
              ]
            })
          }
        }
      })
      .state('dashboard.form',{
        templateUrl:'static/views/form.html',
        url:'/form'
    })
      .state('dashboard.blank',{
        templateUrl:'static/views/pages/blank.html',
        url:'/blank'
    })
      .state('login',{
        templateUrl:'static/views/pages/login.html',
        url:'/login'
    })
      .state('dashboard.chart',{
        templateUrl:'static/views/chart.html',
        url:'/chart',
        controller:'ChartCtrl',
        resolve: {
          loadMyFile:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'chart.js',
              files:[
                'static/sb_admin/bower_components/angular-chart.js/dist/angular-chart.min.js',
                'static/sb_admin/bower_components/angular-chart.js/dist/angular-chart.css'
              ]
            }),
            $ocLazyLoad.load({
                name:'sbAdminApp',
                files:['static/sb_admin/scripts/controllers/chartContoller.js']
            })
          }
        }
    })
      .state('dashboard.test-cases',{
        templateUrl:'static/views/test-cases.html',
        url:'/test-cases',
        controller:'TestCaseController',
        resolve: {
            loadMyFile:function($ocLazyLoad) {
              return $ocLazyLoad.load({
            	name:'sbAdminApp',
                files:[
                  'static/sb_admin/scripts/services/CommonUtilityService.js',
                ]
              }),
              $ocLazyLoad.load({
            	name:'sbAdminApp',
                files:[
                  'static/sb_admin/scripts/controllers/TestCaseController.js',
                ]
              });
            }
          }
    })
     .state('dashboard.test-run',{
          templateUrl:'static/views/test-run.html',
          url:'/test-run',
          controller:'TestCaseRunController',
          resolve: {
              loadMyFile:function($ocLazyLoad) {
                return $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/services/CommonUtilityService.js',
                  ]
                }),
                $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/controllers/TestCaseRunController.js',
                  ]
                });
              }
            }
      })
       .state('dashboard.test-result',{
          templateUrl:'static/views/test-result.html',
          url:'/test-result',
          controller:'TestResultController',
          resolve: {
              loadMyFile:function($ocLazyLoad) {
                return $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/services/CommonUtilityService.js',
                  ]
                }),
                $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/controllers/TestResultController.js',
                  ]
                });
              }
            }
      })
      .state('dashboard.test-case-result',{
          templateUrl:'static/views/test-case-result.html',
          url:'/test-result/:testRunID',
          controller:'TestCaseResultController',
          resolve: {
              loadMyFile:function($ocLazyLoad) {
                return $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/services/CommonUtilityService.js',
                  ]
                }),
                $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/controllers/TestCaseResultController.js',
                  ]
                });
              }
            }
      })
      .state('dashboard.test-case-steps-result',{
          templateUrl:'static/views/test-case-steps-result.html',
          url:'/test-result/:testRunID/:testcaseRecordID',
          controller:'TestCaseStepResultController',
          resolve: {
              loadMyFile:function($ocLazyLoad) {
                return $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/services/CommonUtilityService.js',
                  ]
                }),
                $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/controllers/TestCaseStepResultController.js',
                  ]
                });
              }
            }
      })
      .state('dashboard.property-set',{
          templateUrl:'static/views/property-set.html',
          url:'/property-set',
          controller:'PropertySetListController',
          resolve: {
              loadMyFile:function($ocLazyLoad) {
                return $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/services/CommonUtilityService.js',
                  ]
                }),
                $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/controllers/PropertySetListController.js',
                  ]
                });
              }
            }
      })
      .state('dashboard.property-set-entry',{
          templateUrl:'static/views/property-set-entry.html',
          url:'/property-set-new/:id',
          controller:'PropertySetController',
          resolve: {
              loadMyFile:function($ocLazyLoad) {
                return $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/services/CommonUtilityService.js',
                  ]
                }),
                $ocLazyLoad.load({
              	name:'sbAdminApp',
                  files:[
                    'static/sb_admin/scripts/controllers/PropertySetController.js',
                  ]
                });
              }
            }
      })
      .state('dashboard.panels-wells',{
          templateUrl:'static/views/ui-elements/panels-wells.html',
          url:'/panels-wells'
      })
      .state('dashboard.tabel',{
          templateUrl:'static/views/table.html',
          url:'/table'
      })
      .state('dashboard.buttons',{
        templateUrl:'static/views/ui-elements/buttons.html',
        url:'/buttons'
    })
      .state('dashboard.notifications',{
        templateUrl:'static/views/ui-elements/notifications.html',
        url:'/notifications'
    })
      .state('dashboard.typography',{
       templateUrl:'static/views/ui-elements/typography.html',
       url:'/typography'
   })
      .state('dashboard.icons',{
       templateUrl:'static/views/ui-elements/icons.html',
       url:'/icons'
   })
      .state('dashboard.grid',{
       templateUrl:'static/views/ui-elements/grid.html',
       url:'/grid'
   })
  }]);

    
