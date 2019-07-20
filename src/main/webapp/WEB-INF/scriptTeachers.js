document.getElementById("fountainG").style.visibility = 'hidden';
let teachersButton = document.getElementById("teachersButton");
let teacherStorage = [];


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

                teacherStorage.push(teacherObject);
                let tr = document.createElement("tr");

                let td1 = teacherObject.column1Ref;
                td1.setAttribute('id', "teacher-" + teacherObject.id);
                let td2 = teacherObject.column2Ref;
                td2.setAttribute('id', "teacher-" + teacherObject.id);

                td1.innerHTML = teacherObject.column1Value;
                td2.innerHTML = teacherObject.column2Value;


                tr.appendChild(td1);
                tr.appendChild(td2);
                content.appendChild(tr);

            }
        }
        document.getElementById("fountainG").style.visibility = 'hidden';
    }
    request.send();
};



