function setupTable() {
    const table = document.getElementById('userBookingTable')

    const btnSearch = document.getElementById('btnSearch')
    
     btnSearch.onclick = () =>   {

         const api=apiFetchBooking(table, document.getElementById('email').value )
         console.log(api)
    }
    
    apiFetchAllbookings(table)
}

 setupTable()


function propulateActualData(table, userBookings) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    for(const userBooking of userBookings) {
        console.log(userBooking)
        const {userId, userEmail, jobTitle, location, industry } = userBooking 
       

        const row = table.insertRow()
        row.insertCell(0).innerHTML = userId
        row.insertCell(1).innerHTML = userEmail
        row.insertCell(2).innerHTML = jobTitle
        row.insertCell(3).innerHTML = location
        row.insertCell(4).innerHTML = industry    
        
    }
}



function apiFetchAllbookings(table) {
    axios.get('http://localhost:8080/admin/alluserJobs')
        .then(res => {
           
            const { data } = res
             
            const { sts, msg, bd } = data
            console.log(data) 
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBooking(table, email) {
    console.log(table)
    console.log(email)
    const url = `http://localhost:8080/admin/filterbyemail`
    axios.get(url,{
        params: {
            email: email
        }
    })
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}


console.log("View page")
function goBack() {
    window.history.back();
}