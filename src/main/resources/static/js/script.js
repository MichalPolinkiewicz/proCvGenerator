function addRow() {
    var i = table.rows.length -1;

    var table = document.getElementById('educationsTable');
    var newRow = table.insertRow(-1);
    var newCell1 = newRow.insertCell(0);
    var newCell2 = newRow.insertCell(1);
    var newCell3 = newRow.insertCell(2);
    var newCell4 = newRow.insertCell(3);
    var newCell5 = newRow.insertCell(4);

    newCell1.innerHTML = "<th><input id='educationListWrapperDto.educationList2.schoolName' name='educationListWrapperDto.educationList[2].schoolName'></th>";
    newCell2.innerHTML = "<th><input type='text' id='educationListWrapperDto.educationList' + i + '.subject' name='educationListWrapperDto.educationList[0].subject' th:field='*{subject}'></th>";
    newCell3.innerHTML = "<th><input type='text' id='educationListWrapperDto.educationList' + i + '.degree' name='educationListWrapperDto.educationList[0].degree' th:field='*{degree}'></th>";
    newCell4.innerHTML = "<th><input type='text' id='educationListWrapperDto.educationList' + i + '.startDate' name='educationListWrapperDto.educationList[0].startDate' th:field='*{startDate}'></th>";
    newCell5.innerHTML = "<th><input type='text' id='educationListWrapperDto.educationList' + i + '.endDate' name='educationListWrapperDto.educationList[0].endDate' th:field='*{endDate}'></th>";
}