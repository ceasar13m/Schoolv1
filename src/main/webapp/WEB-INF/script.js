
document.getElementById("fountainG").style.visibility = 'hidden';
let teachersButton = document.getElementById("teachersButton");
let studentsButton = document.getElementById("studentsButton");
let gradesButton = document.getElementById("gradesButton");
let subjectsButton = document.getElementById("subjectsButton");


let teachersButtonClick = function () {
    document.getElementById("content").innerHTML = "";
    document.getElementById("fountainG").style.visibility = 'visible';
    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/teachers", true);
    request.onreadystatechange = function () {
        if (request.status == 200 && request.readyState == 4) {

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

            for (var teacher in teachers) {
                let tr = document.createElement("tr");

                let td1 = document.createElement("td");
                let td2 = document.createElement("td");

                td1.innerHTML = teachers[teacher].firstName;
                td2.innerHTML = teachers[teacher].secondName;

                tr.appendChild(td1);
                tr.appendChild(td2);



                content.appendChild(tr);
                document.getElementById("fountainG").style.visibility = 'hidden';
            }
        }
    };
    request.send();
}

let studentsButtonClick = function () {
    document.getElementById("content").innerHTML = "";
    document.getElementById("fountainG").style.visibility = 'visible';

    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/students?getAll=true", true);
    request.onreadystatechange = function () {
        if (request.status == 200 && request.readyState == 4) {
            let content = document.getElementById("content");
            let students = JSON.parse(request.responseText).arrayList;
            console.log(students);
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
                document.getElementById("fountainG").style.visibility = 'hidden';
            }
        }
    };
    request.send();
}

let gradesButtonClick = function () {
    document.getElementById("content").innerHTML = "";

    document.getElementById("fountainG").style.visibility = 'visible';

    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/grades", true);
    request.onreadystatechange = function () {
        if (request.status == 200 && request.readyState == 4) {

            let content = document.getElementById("content");
            let grades = JSON.parse(request.responseText).arrayList;
            for (var grade in grades) {
                let tr = document.createElement("tr");

                let td1 = document.createElement("td");

                td1.innerHTML = grades[grade].name;

                tr.appendChild(td1);


                content.appendChild(tr);
                document.getElementById("fountainG").style.visibility = 'hidden';
            }
        }
    };
    request.send();
}

let subjectsButtonClick = function () {
    document.getElementById("content").innerHTML = "Subjects";
}


teachersButton.addEventListener("click", teachersButtonClick);
studentsButton.addEventListener("click", studentsButtonClick);
gradesButton.addEventListener("click", gradesButtonClick);
subjectsButton.addEventListener("click", subjectsButtonClick);

