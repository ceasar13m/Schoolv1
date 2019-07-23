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


            for (let i = 0; i < grades.length; i++) {
                var gradeObject = {
                    columnValue: grades[i].name,
                    columnRef: document.createElement('td'),
                    id: grades[i].id
                };

                gradesStorage[i] = gradeObject;
                let tr = document.createElement("tr");


                let td1 = gradeObject.columnRef;
                td1.setAttribute('id', "grade-" + 0 + "-" + gradeObject.id);
                td1.className = 'editable';


                let delGradeButton = document.createElement('button');
                delGradeButton.innerText = "del";
                delGradeButton.setAttribute('id', gradeObject.id);
                delGradeButton.onclick = function (ev) {
                    let req = new XMLHttpRequest();
                    req.open("DELETE", "http://localhost:8080/grades", true);
                    req.setRequestHeader('gradeId', gradeObject.id);
                    req.send();
                }
                td1.innerHTML = gradeObject.columnValue;



                tr.appendChild(td1);
                tr.appendChild(delGradeButton);
                content.appendChild(tr);

            }

            let trInp = document.createElement('tr');
            let tdInp1 = document.createElement('td');

            let form1 = document.createElement('input');

            form1.setAttribute('type', 'text');
            form1.setAttribute('id', 'nameForm');
            form1.setAttribute('size', '3');



            let addGradeButton = document.createElement('button');
            addGradeButton.innerText = "add";
            addGradeButton.setAttribute('id', gradeObject.id);
            addGradeButton.onclick = function (ev) {
                let temp = document.getElementById('nameForm');
                let tempGrade = {
                    name: temp.value,
                }
                let jsonString = JSON.stringify(tempGrade);
                let request = new XMLHttpRequest();
                request.open("POST", "http://localhost:8080/grades", true);
                request.send(jsonString);
            }


            tdInp1.appendChild(form1);
            trInp.appendChild(tdInp1);
            trInp.appendChild(addGradeButton);

            content.appendChild(trInp);
            document.getElementById("fountainG").style.visibility = 'hidden';
        }
    }
    request.send();
}




