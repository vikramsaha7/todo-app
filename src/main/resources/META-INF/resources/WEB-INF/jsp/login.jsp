<html>
	<head>
		<title> Welcome</title>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
	</head>
	<body>
		<div class="container">
			<h1>Login Page</h1>
			<pre> ${errorMsg}</pre>
			<form method="post">
				<label>
					name:
					<input type="text" name="name">
				</label>
				<label>
					password:
					<input type="password" name="password">
				</label>
				<p></p>
				<button class="btn btn-success">submit</button>
			</form>
		</div>
	</body>
</html>