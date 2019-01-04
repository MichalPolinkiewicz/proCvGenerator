function addEducationRow() {
    if (document.readyState === 'complete') {
        let table = document.getElementById('educationsTable');
        let max = 5;
        let i = table.rows.length - 1;

        if (i < max) {
            let newRow = table.insertRow(-1);
            newRow.id = 'education' + i;

            let newCell1 = newRow.insertCell(0);
            let newCell2 = newRow.insertCell(1);
            let newCell3 = newRow.insertCell(2);
            let newCell4 = newRow.insertCell(3);
            let newCell5 = newRow.insertCell(4);
            let newCell6 = newRow.insertCell(5);

            let tableId = "educationsTable";
            let elementId = "education" + i;

            newCell1.innerHTML = '<tr> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="educationListWrapperDto.educationList' + i + '.schoolName" name="educationListWrapperDto.educationList[' + i + '].schoolName" required></tr>';
            newCell2.innerHTML = '<tr> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="educationListWrapperDto.educationList' + i + '.subject" name="educationListWrapperDto.educationList[' + i + '].subject" required></tr>';
            newCell3.innerHTML = '<tr> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="educationListWrapperDto.educationList' + i + '.degree" name="educationListWrapperDto.educationList[' + i + '].degree" required></tr>';
            newCell4.innerHTML = '<tr> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="educationListWrapperDto.educationList' + i + '.startDate" name="educationListWrapperDto.educationList[' + i + '].startDate" required></tr>';
            newCell5.innerHTML = '<tr> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="educationListWrapperDto.educationList' + i + '.endDate" name="educationListWrapperDto.educationList[' + i + '].endDate" required></tr>';
            newCell6.innerHTML = '<td><button type="button" class="btn btn-danger" onclick="deleteRow(\'' + tableId + '\', \'' + elementId + '\', \'\'+2+\'\' )"/>Usuń</td>'
        } else {
            window.alert("Max value for this section is 5")
            //document.getElementById('educationsButton').style.visibility = "hidden";
        }
    }
}

function addEmploymentRow() {
    if (document.readyState === 'complete') {
        let table = document.getElementById('employmentsTable');
        let max = 5;
        let i = table.rows.length - 1;

        if (i < max) {
            let newRow = table.insertRow(-1);
            newRow.id = 'employment' + i;

            let newCell1 = newRow.insertCell(0);
            let newCell2 = newRow.insertCell(1);
            let newCell3 = newRow.insertCell(2);
            let newCell4 = newRow.insertCell(3);
            let newCell5 = newRow.insertCell(4);
            let newCell6 = newRow.insertCell(5);

            let tableId = "employmentsTable";
            let elementId = "employment" + i;

            newCell1.innerHTML = '<td> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="employmentListWrapperDto.employmentList' + i + '.company" name="employmentListWrapperDto.employmentList[' + i + '].company" required></td>';
            newCell2.innerHTML = '<td> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="employmentListWrapperDto.employmentList' + i + '.position" name="employmentListWrapperDto.employmentList[' + i + '].position" required></td>';
            newCell3.innerHTML = '<td> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="employmentListWrapperDto.employmentList' + i + '.jobDescription" name="employmentListWrapperDto.employmentList[' + i + '].jobDescription" required></td>';
            newCell4.innerHTML = '<td> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="employmentListWrapperDto.employmentList' + i + '.startDate" name="employmentListWrapperDto.employmentList[' + i + '].startDate" required></td>';
            newCell5.innerHTML = '<td> <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="employmentListWrapperDto.employmentList' + i + '.endDate" name="employmentListWrapperDto.employmentList[' + i + '].endDate" required></td>';
            newCell6.innerHTML = '<td><button type="button" class="btn btn-danger" onclick="deleteRow(\'' + tableId + '\',\'' + elementId + '\',\'' + 2 + '\')"/>Usuń</td>'
        } else {
            window.alert("Max value for this section is 5")
            //document.getElementById('employmentsButton').style.visibility = "hidden";
        }
    }
}

function addSkill() {
    if (document.readyState === 'complete') {
        let table = document.getElementById('skillsList');
        let i = table.getElementsByTagName('tr').length;
        let tableId = "skillsList";
        let elementId = "skill" + i;

        if (i < 6) {
            let newRow = table.insertRow(-1)
            newRow.id = 'skill' + i;
            newRow.innerHTML = '<tr><td><input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="skillsListWrapperDto.skills' + i + '" name="skillsListWrapperDto.skills[' + i + ']" size="50" required></td>' +
                '<td><button type="button" class="btn btn-danger" onclick="deleteRow(\'' + tableId + '\', \'' + elementId + '\', \'' + 1 + '\')"/>Usuń</td></tr>';
        } else {
            window.alert("Max value for this section is 6")
            //document.getElementById('skillsButton').style.visibility = "hidden";
        }
    }
}

function addHobby() {
    if (document.readyState === 'complete') {
        let table = document.getElementById('hobbiesList');
        let i = table.getElementsByTagName('tr').length;
        let tableId = "hobbiesList";
        let elementId = "hobby" + i;

        if (i < 6) {
            let newCell = table.insertRow(-1)
            newCell.id = 'hobby' + i;
            newCell.innerHTML = '<tr><td><input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="hobbiesListWrapperDto.hobbyList' + i + '" name="hobbiesListWrapperDto.hobbyList[' + i + ']" required></td> ' +
                '<td><button type="button" class="btn btn-danger" onclick="deleteRow(\'' + tableId + '\', \'' + elementId + '\', \'' + 1 + '\')"/>Usuń</td></tr>';
        } else if (i === 6) {
            //document.getElementById('hobbiesButton').style.visibility = "hidden";
            window.alert("Max value for this section is 6")
        }
    }
}

function deleteRow(tableId, elementId, min) {
    if (document.readyState === 'complete') {
        let table = document.getElementById(tableId);
        let counter = table.getElementsByTagName('tr').length;
        if (counter > min) {
            document.getElementById(elementId).remove();
        } else {
            window.alert("This section cannot be empty!")
        }

    }
}