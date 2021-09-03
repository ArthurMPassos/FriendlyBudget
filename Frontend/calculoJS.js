var globalNegative = 0;

    listTransactions();

    /**
     * Show the transaction table
     */
    function showTable(totalTable){
      
        var total = 0
        for (let i = 0; i < totalTable.length; i++){
            total += parseFloat(totalTable[i].value); 
            var table = document.getElementById("idtab");
            var row = table.insertRow(table.length);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            cell1.innerHTML = totalTable[i].description;
            cell2.innerHTML = totalTable[i].value;
            cell3.innerHTML = totalTable[i].date;
        }  
        document.getElementById("totalSum").innerHTML = total.toFixed(2);
    }

    /**
     * Create the array to send on sendTrans()
     */
    function addTable(){

        var description = document.getElementById("description").value;
        var value = document.getElementById("value").value;
        var date = document.getElementById("date").value;
        var exp = document.getElementById("expense").value;
        value = parseFloat(value);
        var dateText = "";
        var dateText = '' + date[8] + date[9] + "/" + date[5] + date[6] + "/" + date[0] + date[1] + date[2] + date[3];
        var oReq = new XMLHttpRequest();
        var dict = {};
        dict.description = description;

        if (globalNegative == 1){
            if (value<0){
                value *= -1;
            }
            dict.value = 0 - value;
        }

        else{
            dict.value = value;
        }
    
        dict.date = dateText;
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
    
    function positiveValue() { console.log("Alo"); globalNegative = 0; }

    function negativeValue() { console.log("Alo2"); globalNegative = 1; }