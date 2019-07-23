document.getElementById("fountainG").style.visibility = 'hidden';
let teachersButton = document.getElementById("teachersButton");
let gradesStorage = [];


teachersButton.onclick = function () {
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
                var teacherObject = {
                    column1Value: teachers[i].firstName,
                    column1Ref: document.createElement('td'),
                    column2Value: teachers[i].secondName,
                    column2Ref: document.createElement('td'),
                    id: teachers[i].id
                };

                gradesStorage[i] = teacherObject;
                let tr = document.createElement("tr");


                let td1 = teacherObject.column1Ref;
                td1.setAttribute('id', "teacher-" + 0 + "-" + teacherObject.id);
                td1.className = 'editable';
                let td2 = teacherObject.column2Ref;
                td2.setAttribute('id', "teacher-"+ 1 + "-" + teacherObject.id);
                td2.className = 'editable';


                let delTeacherButton = document.createElement('button');
                delTeacherButton.innerText = "del";
                delTeacherButton.setAttribute('id', teacherObject.id);
                delTeacherButton.onclick = function (ev) {
                    let req = new XMLHttpRequest();
                    req.open("DELETE", "http://localhost:8080/teachers", true);
                    req.setRequestHeader('teacherId', teacherObject.id);
                    req.send();
                }
                td1.innerHTML = teacherObject.column1Value;
                td2.innerHTML = teacherObject.column2Value;



                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(delTeacherButton);
                content.appendChild(tr);

            }

            let trInp = document.createElement('tr');
            let tdInp1 = document.createElement('td');
            let tdInp2 = document.createElement('td');

            let form1 = document.createElement('input');
            let form2 = document.createElement('input');

            form1.setAttribute('type', 'text');
            form1.setAttribute('id', 'firstNameForm');
            form1.setAttribute('size', '10');
            form2.setAttribute('type', 'text');
            form2.setAttribute('id', 'secondNameForm');
            form2.setAttribute('size', '10');



            let addTeacherButton = document.createElement('button');
            addTeacherButton.innerText = "add";
            addTeacherButton.setAttribute('id', teacherObject.id);
            addTeacherButton.onclick = function (ev) {
                let tempFN = document.getElementById('firstNameForm');
                let tempSN = document.getElementById('secondNameForm');
                let tempTeacher = {
                    firstName: tempFN.value,
                    secondName: tempSN.value
                }
                let jsonString = JSON.stringify(tempTeacher);
                let request = new XMLHttpRequest();
                request.open("POST", "http://localhost:8080/teachers", true);
                request.send(jsonString);
            }


            tdInp1.appendChild(form1);
            tdInp2.appendChild(form2);
            trInp.appendChild(tdInp1);
            trInp.appendChild(tdInp2);
            trInp.appendChild(addTeacherButton);

            content.appendChild(trInp);

        }
        document.getElementById("fountainG").style.visibility = 'hidden';
    }
    request.send();
};



