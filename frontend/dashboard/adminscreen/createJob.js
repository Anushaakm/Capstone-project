const validateForm = ({ jobTitle, jobDescription, loaction, industry, qualification, applicationRequirement }) => {

    if (jobTitle.length <= 0) return { msg: 'invalid title', sts: false }
    if (jobDescription.length <= 0) return { msg: 'invalid job Description', sts: false }
    if (location.length <= 0) return { msg: 'invalid location', sts: false }
    if (industry.length <= 0) return { msg: 'invalid industry', sts: false }
    if (qualification.length <= 0) return { msg: 'invalid qualification', sts: false }
    if (applicationRequirement.length <= 0) return { msg: 'invalid applicationRequirement', sts: false }

    return { sts: 'success', msg: 'all fields are valid' }
}

function setupForm() {

    const err = document.getElementById('errMsg')
    err.style.display = 'none'

    const formSignup = document.getElementById('new-event-link')

    formSignup.onsubmit = ev => {
        ev.preventDefault()

        const formData = new FormData(ev.target)

        const user = Object.fromEntries(formData.entries())
        console.log(user)

        const { sts, msg } = validateForm(user)

        if (sts) apiSignup(user, formSignup)
        else {
            err.style.display = 'block'
            err.innerHTML = `<strong>${msg}</strong>`
        }
    }
}

setupForm()

function apiSignup(user, form) {
    const headers = {
        'content-type': 'application/json'
    }
    axios.post('http://localhost:8080/admin/newjob', user, { headers })

        .then(res => {
            form.reset()
            showSuccessModal()
        }).catch(err => console.log(err))
}

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}

function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}