<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="js/jquery-3.2.1.min.js"></script>
    <script>
        function toRegist(){
            window.location.href = "toRegist";
        }
        function login(){
            $.ajax({
                url: "/login",
                type: "post",
                data: {
                    username: $("#username").val(),
                    password: $("#password").val()
                },
                datatype:"json",
                success: function (json) {
                    data =JSON.parse(json);
                    if (data.status == 1) {
                        location.href="/product"
                    } else {
                        $("#errMsg").html(data.msg);
                    }
                }
            })
        }
    </script>
</head>
<body>
<form action="login">
    username:<input type="text" id="username"/><br>
    password:<input type="password" id="password"/><br>
    <button type="button" onclick="login()">logim</button>
    <button type="button" id="regist" onclick="toRegist()">regist</button><br>
    <span id="errMsg"></span>
</form>
</body>
</html>
