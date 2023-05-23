const validateForm = ({ location }) => {

    if (location.length <= 0) return { msg: 'Enter location to search', sts: false }

    return { sts: 'success', msg: 'all fields are valid' }
}

function setupTable() {

    const table = document.getElementById('tableJob')

    const btnSearch = document.getElementById('btnSearch')

    btnSearch.onclick = () => {

        apiFetchAllLocationJobs(table, document.getElementById('location').value)
    }

    apiFetchAllJobs(table)
}

setupTable()

function propulateActualData(table, jobs) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    for (const job of jobs) {
        const { id, jobTitle, location, industry, postedDate} = job
        const viewPageUrl = `./viewjob.html?id=${id}`
       

        const row = table.insertRow()
        row.insertCell(0).innerHTML = id
        row.insertCell(1).innerHTML = jobTitle
        row.insertCell(2).innerHTML = location
        row.insertCell(3).innerHTML = industry
        row.insertCell(4).innerHTML = postedDate
        row.insertCell(5).innerHTML = `
            
            <a class='ms-2' href='${viewPageUrl}'>View</a> 
            
        `
    }
}


function apiFetchAllJobs(table) {

    axios.get('http://localhost:8080/jobseeker/upcomingjobs')
        .then(res => {
            const { data } = res
            const { sts, msg, bd } = data
            console.log(data)
            propulateActualData(table, data)
        })
        .catch(err => console.log(err))
}

function apiFetchAllLocationJobs(table, loc) {

    const url = `http://localhost:8080/jobseeker/jobs`
    axios.get(url, {
        params: {
            location: loc
        }
    }).then(res => {
        const { data } = res
        const { sts, msg, bd } = data
        propulateActualData(table, data)
    })
        .catch(err => console.log(err))
}
function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}