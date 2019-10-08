<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login form</title>

</head>
<body>
<div id="divWindow">
    <div class="divForm">
        <h3> Login form</h3>
        <form action="home" method="post">

            <input type ="text" name="phone" maxlenght="15"
                   placeholder="Your phone..."/>

            <input type ="password" name= "password"  maxlenght="30"
                   placeholder="Your password..."/>

            <input type="submit" name="submit" value="login"/>
            <input type="reset" name="clear" value="clear"/>

            <p><a href="registration.jsp"> create new account</a></p>

        </form>
    </div>
</div>
</body>
</html>