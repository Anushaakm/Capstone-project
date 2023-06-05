function setupTable() {
    const table = document.getElementById('feedbacks')

    apiFetchAllbookings(table)
}

setupTable()


function propulateActualData(table, users) {
    console.log(users)
   
    for(const user of users) {

        const {comment,location,interviewDate} = user
        console.log(comment)
        console.log(location)
        
        const row = table.insertRow()
        row.insertCell(0).innerHTML = comment
        row.insertCell(1).innerHTML = location
        row.insertCell(2).innerHTML = interviewDate
      
    }
}

function showConfirmDeleteModal(id) {
    console.log('clicked ' + id)
    const myModalEl = document.getElementById('deleteModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()

    const btDl = document.getElementById('btDl')
    btDl.onclick = () => {
        apiCallDeleteBooking(id, modal)
    }
}

function apiFetchAllbookings(table) {
    
    axios.get('http://localhost:8080/admin/feedback')
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data
            console.log(bd)
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}
function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}