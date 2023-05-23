const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.id
}

function apiGetJobDetails() {
    const id = readIdQueryParam()

    axios.get(`http://localhost:8080/admin/jobs/${id}`)
        .then(function (response) {
            const data = response.data.bd;
            document.getElementById('jobTitle').textContent = data.jobTitle;
            document.getElementById('jobDescription').textContent = data.jobDescription;
            document.getElementById('location').textContent = data.location;
            document.getElementById('industry').textContent = data.industry;
            document.getElementById('qualification').textContent = data.qualification;
            document.getElementById('applicationRequirement').textContent = data.applicationRequirement;
        })
        .catch(function (error) {
            console.log(error);
        });
}

function setupForm() {
    const formEvent = document.getElementById('view-jobdetails')

    formEvent.onsubmit = ev => {
        ev.preventDefault()
        showSuccessModal()
    }
}

setupForm()

apiGetJobDetails()

function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}


function bookJobByUserId() {
    const userId = localStorage.getItem("userId");
   
    const jobPostId = readIdQueryParam()

    const headers = {
        'content-type': 'application/json'
    }
    axios.post(`http://localhost:8080/jobseeker/${userId}/job/${jobPostId}`, { headers })

        .then(
            ()=>{showSuccessModal()}
        ).catch (err => console.log(err))
}


function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}

