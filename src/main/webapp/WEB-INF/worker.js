let worker = function (targetId, value) {
    let temp = targetId.split('-');
    let id = parseInt(temp[2], 10);

    switch (temp[0]) {
        case 'teacher': {
            for (i in studentsStorage) {
                if (studentsStorage[i].id === id) {
                    let teacher = {
                        firstName: studentsStorage[i].column1Value,
                        secondName: studentsStorage[i].column2Value,
                        id: id
                    };


                    if (temp[1] == 0) {
                        teacher.firstName = value;
                        studentsStorage[i].column1Value = teacher.firstName;
                    } else {
                        if (temp[1] == 1) {
                            teacher.secondName = value;
                            studentsStorage[i].column2Value = teacher.secondName;
                        }
                    }


                    let jsonString = JSON.stringify(teacher);
                    let request = new XMLHttpRequest();
                    request.open("PUT", "http://localhost:8080/teachers", true);


                    request.send(jsonString);
                    break;
                }
            }
            break;
        }

        case 'student': {
            for (i in studentsStorage) {
                if (studentsStorage[i].id === id) {
                    let student = {
                        firstName: studentsStorage[i].column1Value,
                        secondName: studentsStorage[i].column2Value,
                        gradeId: studentsStorage[i].column3Value,
                        id: id
                    };
                    if (temp[1] == 0) {
                        student.firstName = value;
                        studentsStorage[i].column1Value = student.firstName;
                    } else {
                        if (temp[1] == 1) {
                            student.secondName = value;
                            studentsStorage[i].column2Value = student.secondName;
                        } else {
                            if(temp[1] == 2) {
                                student.gradeId = value;
                                studentsStorage[i].column3Value = student.gradeId;
                            }
                        }
                    }


                    let jsonString = JSON.stringify(student);
                    console.log(jsonString);
                    let request = new XMLHttpRequest();
                    request.open("PUT", "http://localhost:8080/students", true);


                    request.send(jsonString);
                    break;
                }
            }
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