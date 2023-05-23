const homeLink = document.getElementById("home-link");
homeLink.addEventListener("click", reloadPage);
function reloadPage() {
  window.location.reload();
}



document.getElementById("login-link").addEventListener("click", function() {
  window.location.href = "../loginpage/login.html";
});