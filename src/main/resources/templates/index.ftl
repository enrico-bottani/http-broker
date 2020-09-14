<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Spring security oauth2</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width"/>
    <base href="/"/>
</head>
<body>
<h1>Demo</h1>
<div class="container unauthenticated">
    With GitHub: <a href="/oauth2/authorization/github">click here</a>
</div>
<div class="container authenticated" style="display:none">
    Logged in as: <span id="user"></span>
    <div>
        <button onClick="logout()" class="btn btn-primary">Logout</button>
    </div>
</div>

<script type="text/javascript" src="./js/js.cookie-2.2.1.min.js"></script>

<script type="text/javascript">

    function onResponse(response) {
        console.log(response);
    }

    function post(url, callback) {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == XMLHttpRequest.DONE) {   // XMLHttpRequest.DONE == 4
                if (xmlHttp.status == 200) {
                    callback(xmlHttp.response);
                } else if (xmlHttp.status == 400) {
                } else {
                }
            }
        };
        xmlHttp.open("POST", "/api/2020-11/get/", true);
        let request = {
            "url": url,
            "parameters": {
                "username": "anna@gmail.com",
                "token": "abc"
            }
        }

        xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlHttp.send(JSON.stringify(request));
    }


    function POST(url) {
        post(url, onResponse);
        return "POST request to " + url;
    }
</script>

<a href="https://spring.io/guides/tutorials/spring-boot-oauth2/#github-register-application">https://spring.io/guides/tutorials/spring-boot-oauth2/#github-register-application</a>
</body>
</html>