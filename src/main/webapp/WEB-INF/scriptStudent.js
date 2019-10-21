let studentsButton = document.getElementById("studentsButton");
let studentsStorage = [];



var gradesArray;



studentsButton.onclick = function () {
    document.getElementById("content").innerHTML = "";
    document.getElementById("fountainG").style.visibility = 'visible';


    let req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/grades", true);
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            gradesArray = JSON.parse(req.responseText).arrayList;
        }

    }
    req.send();

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


            for (let i in students) {
                var studentObject = {
                    column1Value: students[i].firstName,
                    column1Ref: document.createElement('td'),
                    column2Value: students[i].secondName,
                    column2Ref: document.createElement('td'),
                    column3Value: students[i].gradeId,
                    column3Ref: document.createElement('td'),
                    id: students[i].id
                };

                studentsStorage[i] = studentObject;
                let tr = document.createElement("tr");


                let td1 = studentObject.column1Ref;
                td1.setAttribute('id', "student-" + 0 + "-" + studentObject.id);
                td1.className = 'editable';

                let td2 = studentObject.column2Ref;
                td2.setAttribute('id', "student-" + 1 + "-" + studentObject.id);
                td2.className = 'editable';

                let grade;
                for(gradeIndex in gradesArray) {
                    if(gradesArray[gradeIndex].id === students[i].gradeId)
                        grade = gradesArray[gradeIndex].name;
                }
                let td3 = studentObject.column3Ref;
                td3.setAttribute('id', "student-" + 2 + "-" + studentObject.id);
                td3.className = 'editable';

                let delStudentButton = document.createElement('button');
                delStudentButton.innerText = "x";
                delStudentButton.setAttribute('id', studentObject.id);
                delStudentButton.onclick = function (ev) {
                    let req = new XMLHttpRequest();
                    req.open("DELETE", "http://localhost:8080/students", true);
                    req.setRequestHeader('studentId', studentObject.id);
                    req.send();

                }


                td1.innerHTML = studentObject.column1Value;
                td2.innerHTML = studentObject.column2Value;
                td3.innerHTML = grade;


                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(delStudentButton);
                content.appendChild(tr);

            }




            let trInp = document.createElement('tr');
            let tdInp1 = document.createElement('td');
            let tdInp2 = document.createElement('td');
            let tdInp3 = document.createElement('td');

            let form1 = document.createElement('input');
            form1.type = "text";
            form1.id = "firstNameForm";
            form1.size = 10;

            let form2 = document.createElement('input');
            form2.type = "text";
            form2.id = "secondNameForm";
            form2.size = 10;

            let select3 = document.createElement('select');
            select3.id = "select"

            for (let j in gradesArray) {
                var option = document.createElement("option");
                option.value = gradesArray[j].id;
                option.text = gradesArray[j].name;
                select3.appendChild(option);
            }



            let addStudentButton = document.createElement('button');
            addStudentButton.innerText = "add";
            addStudentButton.setAttribute('id', studentObject.id);
            addStudentButton.onclick = function (ev) {
                let tempFN = document.getElementById('firstNameForm');
                let tempSN = document.getElementById('secondNameForm');
                let tempGr = document.getElementById('select');
                let tempStudent = {
                    firstName: tempFN.value,
                    secondName: tempSN.value,
                    gradeId: tempGr.value
                }
                let jsonString = JSON.stringify(tempStudent);
                let request = new XMLHttpRequest();
                request.open("POST", "http://localhost:8080/students", true);
                request.send(jsonString);
            }


            tdInp1.appendChild(form1);
            tdInp2.appendChild(form2);
            tdInp3.appendChild(select3);
            trInp.appendChild(tdInp1);
            trInp.appendChild(tdInp2);
            trInp.appendChild(tdInp3);
            trInp.appendChild(addStudentButton);

            content.appendChild(trInp);
        }
        document.getElementById("fountainG").style.visibility = 'hidden';
    }
    request.send();
};



