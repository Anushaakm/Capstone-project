const validateForm = ({ location }) => {

    if (location.length <= 0) return { msg: 'Enter location to search', sts: false }

    return { sts: 'success', msg: 'all fields are valid' }
}

function setupTable1() {

    const table = document.getElementById('tableJob')

    const btnSearch = document.getElementById('btnSearch1')

    btnSearch.onclick = () => {

        apiFetchAllLocationJobs(table, document.getElementById('location').value)
    }

    apiFetchAllJobs(table)
}
function setupTable2() {

    const table = document.getElementById('tableJob')

    const btnSearch = document.getElementById('btnSearch2')

    btnSearch.onclick = () => {

        apiFetchAllIndustryJobs(table, document.getElementById('industry').value)
    }

    apiFetchAllJobs(table)
}
function setupTable3() {

    const table = document.getElementById('tableJob')

    const btnSearch = document.getElementById('btnSearch3')

    btnSearch.onclick = () => {

        apiFetchAllKeywordJobs(table, document.getElementById('jobTitle').value)
    }

    apiFetchAllJobs(table)
}

setupTable1()
setupTable2()
setupTable3()
function propulateActualData(table, jobs) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    for (const job of jobs) {
        const { id, jobTitle, location, industry} = job
        const viewPageUrl = `./viewjob.html?id=${id}`
       

        const row = table.insertRow()
        row.insertCell(0).innerHTML = id
        row.insertCell(1).innerHTML = jobTitle
        row.insertCell(2).innerHTML = location
        row.insertCell(3).innerHTML = industry
        // row.insertCell(4).innerHTML = postedDate
        row.insertCell(4).innerHTML = `
            
            <a class='ms-2' href='${viewPageUrl}'>Apply</a> 
            
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

    const url = `http://localhost:8080/jobseeker/jobs/location`
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

function apiFetchAllIndustryJobs(table, ind) {

    const url = `http://localhost:8080/jobseeker/jobs/industry`
    axios.get(url, {
        params: {
            industry: ind
        }
    }).then(res => {
        const { data } = res
        const { sts, msg, bd } = data
        propulateActualData(table, data)
    })
        .catch(err => console.log(err))
}
function apiFetchAllKeywordJobs(table, tit) {

    const url = `http://localhost:8080/jobseeker/jobs/keyword`
    axios.get(url, {
        params: {
            jobTitle: tit
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