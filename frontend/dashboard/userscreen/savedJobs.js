function setupTable() {
    const table = document.getElementById('tableJob')
    apiFetchAllJobs(table)
}

setupTable()

function propulateActualData(table, jobs) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    for (const job of jobs) {
        const { id, jobTitle, location, industry } = job
        const applyPageUrl = `./viewJob.html?id=${id}`
        const viewPageUrl = `./viewJobDes.html?id=${id}`

        const row = table.insertRow()
        row.insertCell(0).innerHTML = id
        row.insertCell(1).innerHTML = jobTitle
        row.insertCell(2).innerHTML = location
        row.insertCell(3).innerHTML = industry
        row.insertCell(4).innerHTML = `
           
        <a class='ms-2' href='${viewPageUrl}'>View</a> 
        <a class='ms-2' href='${applyPageUrl}'>Apply</a>
        
    `
       
    }
}

function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}


function apiFetchAllJobs(table) {
    const id = localStorage.getItem("userId");
    axios.get(`http://localhost:8080/jobseeker/savejob/${id}`)
        .then(res => {
            const { data } = res
            propulateActualData(table, data)
        })
        .catch(err => console.log(err))
}

