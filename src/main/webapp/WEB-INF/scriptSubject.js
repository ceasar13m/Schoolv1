document.getElementById("fountainG").style.visibility = 'hidden';

let subjectsButton = document.getElementById("subjectsButton");


subjectsButton.onclick = function () {
    document.getElementById("content").innerHTML = "";

    document.getElementById("fountainG").style.visibility = 'visible';

    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/subjects", true);
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            let subjects = JSON.parse(request.responseText).arrayList;
            let content = document.getElementById("content");
            let trT = document.createElement("tr");
            let th1 = document.createElement("th");

            th1.innerHTML = 'Subject';

            trT.appendChild(th1);

            content.appendChild(trT);


            for (var subject in subjects) {
                let tr = document.createElement("tr");

                let td1 = document.createElement("td");

                td1.innerHTML = subjects[subject].name;

                tr.appendChild(td1);


                content.appendChild(tr);
                document.getElementById("fountainG").style.visibility = 'hidden';
            }
        }
    }
    request.send();
}



