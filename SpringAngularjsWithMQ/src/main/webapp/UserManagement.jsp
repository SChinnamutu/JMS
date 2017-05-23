<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>


<script type="text/javascript">
	var app = angular.module('myapp', []);
	app.controller('myappcontroller', function($scope, $http) {
		
		$scope.users = []
		$scope.user = {name : "",department : "",id :"",editList:""};
		$scope.selection = []
		$scope.userform = {name : "",department : "",id :""};

		getUserDetails();

		function getUserDetails() {
			$http({
				method : 'GET',
				url : 'userdetails'
			}).then(function successCallback(response) {
				$scope.users = response.data;
			}, function errorCallback(response) {
				console.log(response.statusText);
			});
		}

		$scope.pushToQueue = function(user) {
			$scope.userform.editIdList.push($scope.user.id);
		}

		$scope.deleteUsers = function() {
			$scope.userform.editList = $scope.selection;
			$http({
				method : 'POST',
				url : 'deleteUsers',
				data : angular.toJson($scope.userform),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).then(getUserDetails(), clearForm()).success(function(data){
				$scope.users= data;
		    }); 
		}
		
		$scope.processUser = function() {
			$http({
				method : 'POST',
				url : 'user',
				data : angular.toJson($scope.userform),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).then(getUserDetails(), clearForm()).success(function(data){
				$scope.users= data;
		    });
		}
	
		$scope.saveUser = function() {
			if($scope.userform.id == "" || $scope.userform.name == "" || $scope.userform.department == ""){
				alert("Please completed the form");
				return false;		
			}else{
				alert("Not null");
				$http({
					method : 'POST',
					url : 'user',
					data : angular.toJson($scope.userform),
					headers : {
						'Content-Type' : 'application/json'
					}
				}).then(getUserDetails(), clearForm()).success(function(data){
					$scope.users= data;
			    });
			}
			
		}		
		
		
		
		/* $scope.editUser = function(user) {
			$scope.userform.id = user.id;
			$scope.userform.name = user.name;
			$scope.userform.department = user.department;
			$scope.userform.editList = user.editList;
			disableName();
		} */
		
		$scope.editUser = function() {
			$scope.userform.editList = $scope.selection;
			$http({
				method : 'POST',
				url : 'editUser',
				data : angular.toJson($scope.userform),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(data){
				user = data;
				$scope.userform.id = user.id;
				$scope.userform.name = user.name;
				$scope.userform.department = user.department; 
		    });
		}
		
		$scope.updateUser = function() {
			$http({
				method : 'POST',
				url : 'updateUser',
				data : angular.toJson($scope.userform),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).then(getUserDetails(), clearForm()).success(function(data){
				$scope.users= data;
		    });
		}
		
		$scope.deleteUserList = function(user) {
			$scope.selection.push(user.id);
		}
		
		
		
		function clearForm() {
			$scope.userform.id = "";
			$scope.userform.name = "";
			$scope.userform.department = "";
			document.getElementById("name").disabled = false;
		}
		
		
		function disableName() {
			document.getElementById("name").disabled = true;
		}
		
	});
</script>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>

<body ng-app="myapp" ng-controller="myappcontroller">
	
	
	
	<div  style="margin-left: 215px; margin-top: 60px;">
	
	<h3>User Registration Form</h3>
	
		     <form ng-submit="processUserDetails()">
				<div class="table-responsive">
					<table class="table table-bordered" style="width: 600px">
						<tr>
							<td>Id</td>
							<td><input type="text" id="name" ng-model="userform.id" size="10" /></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type="text" id="name" ng-model="userform.name" size="30" /></td>
						</tr>
						<tr>
							<td>Department</td>
							<td><input type="text" id="department" ng-model="userform.department" size="30" /></td>
						</tr>
						<tr>
							<td colspan="2">
									<input type="submit" class="btn btn-primary btn-sm" ng-click="saveUser()" value="Create User" /> |
									<input type="submit" class="btn btn-primary btn-sm" ng-click="updateUser()"	 value="Update User" />
							</td>
						</tr>
					
					</table>
				</div>
			</form>
		
			<h3>Registered Users</h3>
			<div class="table-responsive">
				<table class="table table-bordered" style="width: 600px">
					<tr>
						<th><input type="checkbox" /></th>
						<th>Id</th>
						<th>Name</th>
						<th>Department</th>
						<!-- <th>Actions</th> -->
					</tr>
		
					<tr ng-repeat="user in users">
						<td><input type="checkbox" id="checkbox" class="checkbox" ng-click="deleteUserList(user);" /></td>
						<td>{{ user.id}}</td>
						<td>{{ user.name}}</td>
						<td>{{ user.department }}</td>
					</tr>
					
				</table>
				
					<div>
						 <a ng-click="editUser()" class="btn btn-primary btn-sm">Edit</a>|<a ng-click="deleteUsers(user)" class="btn btn-danger btn-sm">Delete</a>
					</div>
					
			</div>
	</div>

</body>


</html>