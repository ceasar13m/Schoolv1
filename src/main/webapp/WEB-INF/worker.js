let worker = function (targetId, value) {
    alert(targetId);
    let temp = targetId.split('-');
    let id = parseInt(temp[1], 10);

    switch (temp[0]) {
        case 'teacher':{
            for(i in teacherStorage) {
                if(teacherStorage[i].id == id) {
                    let teacher = {
                        firstName: value,
                        secondName: value,
                        id: id
                    };

                    let jsonString = GSON.stringify(teacher);
                    let request = new XMLHttpRequest();
                    request.open("PUT", "http://localhost:8080/teachers", true);

                    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                    request.send(json);
                }
            }
            break;
        }

        case 'student': {
            break;
        }

        case 'subject': {
            break;
        }

        case 'grade': {
            break;
        }
    }
}