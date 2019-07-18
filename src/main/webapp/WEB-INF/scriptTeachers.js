document.getElementById("fountainG").style.visibility = 'hidden';
let teachersButton = document.getElementById("teachersButton");



teachersButton.onclick = function() {
    document.getElementById("content").innerHTML = "";
    document.getElementById("fountainG").style.visibility = 'visible';

    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/teachers", true);
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            let content = document.getElementById("content");
            let teachers = JSON.parse(request.responseText).arrayList;

            let trT = document.createElement("tr");
            let th1 = document.createElement("th");
            let th2 = document.createElement("th");

            th1.innerHTML = 'First Name';
            th2.innerHTML = 'Second Name';

            trT.appendChild(th1);
            trT.appendChild(th2);

            content.appendChild(trT);

            for (let i = 0; i < teachers.length; i++) {
                let tr = document.createElement("tr");

                let td1 = document.createElement("td");
                let td2 = document.createElement("td");

                td1.innerHTML = teachers[i].firstName;
                td2.innerHTML = teachers[i].secondName;


                tr.appendChild(td1);
                tr.appendChild(td2);
                content.appendChild(tr);

            }
        }
        document.getElementById("fountainG").style.visibility = 'hidden';
    }
    request.send();
};




