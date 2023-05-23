function setupTable() {
    const table = document.getElementById('tableJob')
    apiFetchAllJobs(table)
}

setupTable()

function propulateActualData(table, jobs) {

    for (const job of jobs) {

        const { id, jobTitle, location, industry, postedDate} = job
        const updatePageUrl = `./updateJob.html?id=${id}`
        const viewPageUrl = `./viewJobDes.html?id=${id}`

        const row = table.insertRow()
        row.insertCell(0).innerHTML = id
        row.insertCell(1).innerHTML = jobTitle
        row.insertCell(2).innerHTML = location
        row.insertCell(3).innerHTML = industry
        // row.insertCell(4).innerHTML = postedDate
        row.insertCell(4).innerHTML = `
            <a class='ms-2' href='${updatePageUrl}'>Update</a> 
            <a class='ms-2' href='${viewPageUrl}'>View</a> 
            <a class='ms-2' onclick='showConfirmDeleteModal(${id})'>Delete</a> 
        `
    }
}

function showConfirmDeleteModal(id) {
    console.log('clicked ' + id)
    const myModalEl = document.getElementById('deleteModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()

    const btDl = document.getElementById('btDl')
    btDl.onclick = () => {
        apiCallDeleteJob(id, modal)
    }
}

function apiFetchAllJobs(table) {
    axios.get('http://localhost:8080/admin/jobs')
        .then(res => {
            const { data } = res
            console.log(data)
            propulateActualData(table, data)
        })
        .catch(err => console.log(err))
}


function apiCallDeleteJob(id, modal) {
    const url = `http://localhost:8080/admin/jobs/${id}`

    axios.delete(url)
        .then(res =>
            window.location.reload())
        .then(({ sts, msg, bd }) => modal.hide())
        .catch(console.log)
}

function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}
