document.getElementById("fountainG").style.visibility = 'hidden';
let studentsButton = document.getElementById("studentsButton");


studentsButton.onclick = function () {
    document.getElementById("content").innerHTML = "";
    document.getElementById("fountainG").style.visibility = 'visible';

    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/students?getAll=true", true);
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            let content = document.getElementById("content");
            let students = JSON.parse(request.responseText).arrayList;

            let trT = document.createElement("tr");
            let th1 = document.createElement("th");
            let th2 = document.createElement("th");
            let th3 = document.createElement("th");

            th1.innerHTML = 'First Name';
            th2.innerHTML = 'Second Name';
            th3.innerHTML = 'Grade';

            trT.appendChild(th1);
            trT.appendChild(th2);
            trT.appendChild(th3);

            content.appendChild(trT);

            for (let i = 0; i < students.length; i++) {
                let tr = document.createElement("tr");

                let td1 = document.createElement("td");
                let td2 = document.createElement("td");
                let td3 = document.createElement("td");

                td1.innerHTML = students[i].firstName;
                td2.innerHTML = students[i].secondName;
                td3.innerHTML = students[i].gradeId;


                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);


                content.appendChild(tr);

            }
        }
        document.getElementById("fountainG").style.visibility = 'hidden';
    }
    request.send();
}



