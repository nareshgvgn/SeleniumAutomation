<!doctype html>

<html class="no-js">
  <head>
  <meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<meta http-equiv='pragma' content='no-cache'>
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!-- build:css(.) static/sb_admin/bower_components/styles/vendor.css -->
    <!-- bower:css -->
    <link rel="stylesheet" href="static/sb_admin/bower_components/bootstrap/dist/css/bootstrap.min.css" />
    <!-- endbower -->
    <!-- endbuild -->
    
    <!-- build:css(.tmp) static/sb_admin/bower_components/styles/main.css -->
    <link rel="stylesheet" href="static/sb_admin/styles/main.css">
    <link rel="stylesheet" href="static/sb_admin/styles/sb-admin-2.css">
    <link rel="stylesheet" href="static/sb_admin/styles/timeline.css">
    <link rel="stylesheet" href="static/sb_admin/bower_components/metisMenu/dist/metisMenu.min.css">
    <link rel="stylesheet" href="static/sb_admin/bower_components/angular-loading-bar/build/loading-bar.min.css">
    <link rel="stylesheet" href="static/sb_admin/bower_components/font-awesome/css/font-awesome.min.css" type="text/css">
    <!-- endbuild -->
    
    <!-- build:js(.) scripts/vendor.js -->
    <!-- bower:js -->
    <script src="static/sb_admin/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="static/sb_admin/bower_components/angular/angular.min.js"></script>
    <script src="static/sb_admin/bower_components/angular-animate/angular-animate.min.js"></script>
     <script src="static/sb_admin/bower_components/angular-sanitize/angular-sanitize.min.js"></script>
    <script src="static/sb_admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="static/sb_admin/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
    <script src="static/sb_admin/bower_components/json3/lib/json3.min.js"></script>
    <script src="static/sb_admin/bower_components/oclazyload/dist/ocLazyLoad.js"></script>
    <script src="static/sb_admin/bower_components/angular-loading-bar/build/loading-bar.min.js"></script>
    <script src="static/sb_admin/bower_components/angular-bootstrap/ui-bootstrap.min.js"></script>
    <script src="static/sb_admin/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
    <script src="static/sb_admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <script src="static/sb_admin/bower_components/Chart.js/Chart.min.js"></script>
    
    <link rel="stylesheet" href="static/external/toaster/toaster.min.css" type="text/css">
 	<script src="static/external/toaster/toaster.js"></script>
    <!-- endbower -->
    <!-- endbuild -->
    
    <!-- build:js({.tmp,app}) scripts/scripts.js -->
        <script src="static/sb_admin/scripts/app.js"></script>
        <script src="static/sb_admin/js/sb-admin-2.js"></script>
    <!-- endbuild -->

 	<link rel="stylesheet" href="static/external/angular.ui-tree/angular-ui-tree.css" type="text/css">
 	<script src="static/external/angular.ui-tree/angular-ui-tree.js"></script>
 	
    <!-- Custom CSS -->

    <!-- Custom Fonts -->

    <!-- Morris Charts CSS -->
    <!-- <link href="static/sb_admin/bower_components/styles/morrisjs/morris.css" rel="stylesheet"> -->

	<script>
	var remoteIP = '${remoteIP}';
	</script>
    </head>
   
    <body>
    <div ng-app="sbAdminApp">

        <div ui-view>
          
         </div>

    </div>

    </body>

</html>