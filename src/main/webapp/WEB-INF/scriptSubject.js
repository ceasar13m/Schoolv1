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


            for (var i in subjects) {
                var subjectObject = {
                    columnValue: subjects[i].name,
                    columnRef: document.createElement('td'),
                    id: subjects[i].id
                };

                gradesStorage[i] = subjectObject;
                let tr = document.createElement("tr");


                let td1 = subjectObject.columnRef;
                td1.setAttribute('id', "subject-" + 0 + "-" + subjectObject.id);
                td1.className = 'editable';


                let delSubjectButton = document.createElement('button');
                delSubjectButton.innerText = "del";
                delSubjectButton.setAttribute('id', subjectObject.id);
                delSubjectButton.onclick = function (ev) {
                    let req = new XMLHttpRequest();
                    req.open("DELETE", "http://localhost:8080/subjects", true);
                    req.setRequestHeader('subjectId', subjectObject.id);
                    req.send();
                }
                td1.innerHTML = subjectObject.columnValue;


                tr.appendChild(td1);
                tr.appendChild(delSubjectButton);
                content.appendChild(tr);

            }

            let trInp = document.createElement('tr');
            let tdInp1 = document.createElement('td');

            let form1 = document.createElement('input');

            form1.setAttribute('type', 'text');
            form1.setAttribute('id', 'nameForm');
            form1.setAttribute('size', '3');


            let addSubjectButton = document.createElement('button');
            addSubjectButton.innerText = "add";
            addSubjectButton.setAttribute('id', subjectObject.id);
            addSubjectButton.onclick = function (ev) {
                let temp = document.getElementById('nameForm');
                let tempSubject = {
                    name: temp.value,
                }
                let jsonString = JSON.stringify(tempSubject);
                let request = new XMLHttpRequest();
                request.open("POST", "http://localhost:8080/subjects", true);
                request.send(jsonString);
            }


            tdInp1.appendChild(form1);
            trInp.appendChild(tdInp1);
            trInp.appendChild(addSubjectButton);

            content.appendChild(trInp);

        }
        document.getElementById("fountainG").style.visibility = 'hidden';

    }
    request.send();
}



