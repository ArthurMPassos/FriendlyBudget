function login2() {
    var name = document.getElementById("idLogin").value;
    var password = document.getElementById("idSenha").value;
    var oReq = new XMLHttpRequest();
    oReq.responseType = "text";

    oReq.onload = function () {
        if (eval(this.response)) {
            window.location.href = "calculo.html";
        }
        else {
            accountNotAuthorized(name);
        }
    };

    oReq.onerror = function (e) {
        console.log("Something went wrong");
    };

    user = {}
    user.name = name;
    user.password = password;

    oReq.open("post", "http://localhost:8080/login", true);
    oReq.send(JSON.stringify(user));
}

function createUser() {

    var name = document.getElementById("idLogin").value;
    var password = document.getElementById("idSenha").value;
    var oReq = new XMLHttpRequest();
    oReq.responseType = "text";

    oReq.onload = function () {
        if (eval(this.response)) {
            window.location.href = "calculo.html";
        }
        else {
            accountNotAuthorized(name);
        }
    };

    oReq.onerror = function (e) {
        console.log("Something went wrong");
    };

    user = {}
    user.name = name;
    user.password = password;

    oReq.open("post", "http://localhost:8080/createUser", true);
    oReq.send(JSON.stringify(user));
}
