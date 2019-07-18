document.getElementById("fountainG").style.visibility = 'hidden';
let gradesButton = document.getElementById("gradesButton");


gradesButton.onclick = function () {
    document.getElementById("content").innerHTML = "";

    document.getElementById("fountainG").style.visibility = 'visible';

    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/grades", true);
    request.onreadystatechange = function () {
        if (this.readyState === this.DONE && request.readyState === 4) {

            let content = document.getElementById("content");
            let grades = JSON.parse(request.responseText).arrayList;

            let trT = document.createElement("tr");
            let th1 = document.createElement("th");

            th1.innerHTML = 'Grade';

            trT.appendChild(th1);

            content.appendChild(trT);


            for (var grade in grades) {
                let tr = document.createElement("tr");

                let td1 = document.createElement("td");

                td1.innerHTML = grades[grade].name;

                tr.appendChild(td1);


                content.appendChild(tr);
                document.getElementById("fountainG").style.visibility = 'hidden';
            }
        }
    }
    request.send();
}




