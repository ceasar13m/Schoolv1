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
                    column2Ref: document.createElement('td')
                };

                teacherStorage.push(teacherObject);
                let tr = document.createElement("tr");

                let td1 = teacherObject.column1Ref;
                let td2 = teacherObject.column2Ref;

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






var table = document.getElementById('content');

var editingTd;

table.onclick = function(event) {

    var target = event.target;

    while (target != table) {
        if (target.className == 'edit-cancel') {
            finishTdEdit(editingTd.elem, false);
            return;
        }

        if (target.className == 'edit-ok') {
            finishTdEdit(editingTd.elem, true);
            return;
        }

        if (target.nodeName == 'TD') {
            if (editingTd) return; // already editing

            makeTdEditable(target);
            return;
        }

        target = target.parentNode;
    }
}

function makeTdEditable(td) {
    editingTd = {
        elem: td,
        data: td.innerHTML
    };

    td.classList.add('edit-td'); // td, not textarea! the rest of rules will cascade

    var textArea = document.createElement('textarea');
    textArea.style.width = td.clientWidth + 'px';
    textArea.style.height = td.clientHeight + 'px';
    textArea.className = 'edit-area';

    textArea.value = td.innerHTML;
    td.innerHTML = '';
    td.appendChild(textArea);
    textArea.focus();

    td.insertAdjacentHTML("beforeEnd",
        '<div class="edit-controls"><button class="edit-ok">OK</button><button class="edit-cancel">CANCEL</button></div>'
    );
}

function finishTdEdit(td, isOk) {
    if (isOk) {
        td.innerHTML = td.firstChild.value;

        xyu(td);
    } else {
        td.innerHTML = editingTd.data;
    }
    td.classList.remove('edit-td'); // remove edit class
    editingTd = null;
}


function xyu(td) {
    let x;
    for(let i = 0; i < teacherStorage.length; i ++) {
        if(teacherStorage[i].column2Ref == td) {
            x = td.id;
            console.log(x);
        }
    }

}
