function addRow() {
    var table = document.getElementById('educationsTable');
    var newRow = table.insertRow(-1);
    var newCell1 = newRow.insertCell(0);
    var newCell2 = newRow.insertCell(1);
    var newCell3 = newRow.insertCell(2);
    var newCell4 = newRow.insertCel(3);
    var newCell5 = newRow.insertCell(4);

    newCell1.innerHTML = "<th><input type='text' th:field='*{schoolName}'></th>";
    newCell2.innerHTML = "<th><input type='text' th:field='*{subject}'></th>";
    newCell3.innerHTML = "<th><input type='text' th:field='*{degree}'></th>";
    newCell4.innerHTML = "<th><input type='text' th:field='*{startDate}'></th>";
    newCell5.innerHTML = "<th><input type='text' th:field='*{endDate}'></th>";
}