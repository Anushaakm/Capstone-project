const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.id
}
const validateForm = ({ comment, location, interviewDate }) => {

    if (comment.length <= 0) return { msg: 'Comment cannot be empty', sts: false}
    if (location.length <= 0) return { msg: 'Rating cannot be empty', sts: false}
    if (interviewDate.length <= 0) return { msg: 'postedDate cannot be empty', sts: false}
    return { sts : true , msg :'all fields are valid' }
}

function setupForm() {

    const err = document.getElementById('errMsg')
    err.style.display = 'none'
    
    const feedback = document.getElementById('feedback')
    console.log(feedback)

    feedback.onsubmit = ev => { 

        ev.preventDefault() 

        const formData = new FormData(ev.target) 

        const user = Object.fromEntries(formData.entries()) 
        console.log(user)

        const { sts, msg } = validateForm(user)

        if (sts) apiFeedback(user, feedback)
        else {
            err.style.display = 'block'
            err.innerHTML = `<strong>${msg}</strong>`
        }
    }
}

setupForm()

function apiFeedback(user, form) {
    const headers = {
        'content-type': 'application/json'
    }
    const jobPostId = readIdQueryParam()
    console.log(jobPostId)
    const url = `http://localhost:8080/admin/${jobPostId}/feedback`
    axios.post(url , user, { headers })
        .then(()=> {
            form.reset()
            showSuccessModal()

        }).catch(err => console.log(err))
}

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.
show()
}
function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}
function reloadPage() {
    location.reload();
  }