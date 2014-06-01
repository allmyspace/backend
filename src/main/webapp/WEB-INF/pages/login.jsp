<%--
  @author : jknair
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@include file="scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/login.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">AllMySpace</h1>
            <div class="account-wall">
                <img class="profile-img" src="http://s3.amazonaws.com/hackathon-io/profiles/images/000/030/905/thumb/icon.png?1401515206"
                     alt="">
                <form class="form-signin"  action="../../j_spring_security_check" method="post" >
                    <input type="text" class="form-control" id="j_username" name="j_username" placeholder="Username" required autofocus>
                    <input type="password" class="form-control"  id="j_password" name="j_password" placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

                </form>
            </div>
            <a href="#create" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>

<script type="application/javascript">


</script>

</body>
</html>