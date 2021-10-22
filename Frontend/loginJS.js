function login2() {

    var name = document.getElementById("idLogin").value;
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

    oReq.open("post", "http://192.168.1.23:8080/login", true);
    oReq.send(name);
}

function addTransactions(transaction) {

    var oReq = new XMLHttpRequest();
    oReq.responseType = "text";
    oReq.onload = function () {
        showTransactions(JSON.parse(this.response));
    };

    oReq.onerror = function (e) {
        console.log("Something went wrong");
    };
    
    oReq.open("put", "http://192.168.1.23:8080/add", true);
    oReq.send(transaction);
}