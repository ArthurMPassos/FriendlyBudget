var globalNegativo = 0;

    listTransactions();

    /**
     * 
     */
    function showTable(tabelaTotal){
      
        var total = 0
        for (let i = 0; i < tabelaTotal.length; i++){
            total += parseFloat(tabelaTotal[i].value); 
            var table = document.getElementById("idtab");
            var row = table.insertRow(table.length);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            cell1.innerHTML = tabelaTotal[i].description;
            cell2.innerHTML = tabelaTotal[i].value;
            cell3.innerHTML = tabelaTotal[i].date;
        }  
        document.getElementById("somaTotal").innerHTML = total;
    }

    /**
     * Create the array to 
     */
    function addTable(){

        var description = document.getElementById("description").value;
        var valor = document.getElementById("value").value;
        var data = document.getElementById("date").value;
        var exp = document.getElementById("expense").value;
        valor = parseFloat(valor);
        const tabelaEnviar = [];
        var textoData = "";
        var textoData = '' + data[8] + data[9] + "/" + data[5] + data[6] + "/" + data[0] + data[1] + data[2] + data[3];
        var oReq = new XMLHttpRequest();
        var dict = {};
        dict.description = description;

        if (globalNegativo == 1){
            if (valor<0){
                valor *= -1;
            }
            dict.value = 0 - valor;
        }

        else{
            dict.value = valor;
        }
    
        dict.date = textoData;
        sendTrans(dict);
    }

    /**
     * Send strinfied transactions to addTable()
     */
    function sendTrans(dict){
        var oReq = new XMLHttpRequest();
        oReq.responseType = "text";

        oReq.onload = function() {
            //location.reload();
            window.location.href = "calculo.html";
            showTable(JSON.parse(this.response));
        };

        oReq.onerror = function(e) {
            console.log("Something went wrong");
        };

        oReq.open("put", "http://localhost:8080/add", true);
        oReq.send(JSON.stringify(dict));
    
    }

    /**
     * List all trasactions from user
     */
    function listTransactions(){

        var oReq = new XMLHttpRequest();
        oReq.responseType = "text";
        oReq.onload = function() { 
            showTable(JSON.parse(this.response)); 
        };
        oReq.onerror = function(e) { 
            console.log("Something went wrong"); 
        };

        oReq.open("get", "http://localhost:8080/transactions", true);
        oReq.send();

    }
    
    function positiveValue() { console.log("Alo"); globalNegativo = 0; }

    function negativeValue() { console.log("Alo2"); globalNegativo = 1; }