function addEducationRow() {
    let table = document.getElementById('educationsTable');
    let max = 5;
    let i = table.rows.length - 1;

    if (i < max) {
        let newRow = table.insertRow(-1);
        let newCell1 = newRow.insertCell(0);
        let newCell2 = newRow.insertCell(1);
        let newCell3 = newRow.insertCell(2);
        let newCell4 = newRow.insertCell(3);
        let newCell5 = newRow.insertCell(4);

        newCell1.innerHTML = '<tr> <input id="educationListWrapperDto.educationList' + i + '.schoolName" name="educationListWrapperDto.educationList[' + i + '].schoolName" required></tr>';
        newCell2.innerHTML = '<tr> <input id="educationListWrapperDto.educationList' + i + '.subject" name="educationListWrapperDto.educationList[' + i + '].subject" required></tr>';
        newCell3.innerHTML = '<tr> <input id="educationListWrapperDto.educationList' + i + '.degree" name="educationListWrapperDto.educationList[' + i + '].degree" required></tr>';
        newCell4.innerHTML = '<tr> <input id="educationListWrapperDto.educationList' + i + '.startDate" name="educationListWrapperDto.educationList[' + i + '].startDate" required></tr>';
        newCell5.innerHTML = '<tr> <input id="educationListWrapperDto.educationList' + i + '.endDate" name="educationListWrapperDto.educationList[' + i + '].endDate" required></tr>';
    } else {
        window.alert("Max value for this section is 5")
        document.getElementById('educationsButton').style.visibility = "hidden";
    }
}

function addEmploymentRow() {
    let table = document.getElementById('employmentsTable');
    let max = 5;
    let i = table.rows.length - 1;

    if (i < max) {
        let newRow = table.insertRow(-1);
        let newCell1 = newRow.insertCell(0);
        let newCell2 = newRow.insertCell(1);
        let newCell3 = newRow.insertCell(2);
        let newCell4 = newRow.insertCell(3);
        let newCell5 = newRow.insertCell(4);

        newCell1.innerHTML = '<tr> <input id="employmentListWrapperDto.employmentList' + i + '.company" name="employmentListWrapperDto.employmentList[' + i + '].company" required></tr>';
        newCell2.innerHTML = '<tr> <input id="employmentListWrapperDto.employmentList' + i + '.position" name="employmentListWrapperDto.employmentList[' + i + '].position" required></tr>';
        newCell3.innerHTML = '<tr> <input id="employmentListWrapperDto.employmentList' + i + '.jobDescription" name="employmentListWrapperDto.employmentList[' + i + '].jobDescription" required></tr>';
        newCell4.innerHTML = '<tr> <input id="employmentListWrapperDto.employmentList' + i + '.startDate" name="employmentListWrapperDto.employmentList[' + i + '].startDate" required></tr>';
        newCell5.innerHTML = '<tr> <input id="employmentListWrapperDto.employmentList' + i + '.endDate" name="employmentListWrapperDto.employmentList[' + i + '].endDate" required></tr>';
    } else {
        window.alert("Max value for this section is 5")
        document.getElementById('employmentsButton').style.visibility = "hidden";
    }
}

function addSkill() {
    let skillsRow = document.getElementById('skillsRow');
    let i = skillsRow.getElementsByTagName('td').length;
    let newCell = skillsRow.insertCell(-1)

    if (i < 6) {
        newCell.innerHTML = '<td> <input id="skillsListWrapperDto.skills' + i + '" name="skillsListWrapperDto.skills[' + i + ']" required></td>';
    } else {
        window.alert("Max value for this section is 6")
        document.getElementById('skillsButton').style.visibility = "hidden";
    }
}

function addHobby(){
    let hobbiesRow = document.getElementById('hobbiesRow');
    let i = hobbiesRow.getElementsByTagName('td').length;
    let newCell = hobbiesRow.insertCell(-1)

    if (i < 6) {
        newCell.innerHTML = '<td> <input id="hobbiesListWrapperDto.hobbyList' + i + '" name="hobbiesListWrapperDto.hobbyList[' + i + ']" required></td>';
    } else {
        window.alert("Max value for this section is 6")
        document.getElementById('hobbiesButton').style.visibility = "hidden";
    }
}