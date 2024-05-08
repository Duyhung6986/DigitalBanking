const emailForm = document.querySelector(".email-form");
const personalInfo = document.querySelector(".personal-info");
const submit = document.querySelector(".btn-submit");

// FUNCTION CHECK EMAIL - RETURN TRUE/FALSE
function checkEmail(email) {
  const regex =
    /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return regex.test(String(email).toLowerCase());
}

// CHECK EMAIL WHEN CLICK SUBMIT BOTTON
submit.addEventListener("click", function () {
  const email = document.getElementById("email").value;
  if (checkEmail(email)) {
    emailForm.classList.add("hide");
    personalInfo.classList.remove("hide");
  } else {
    alert("Vui lòng nhập đúng định dạng Email !");
  }
});

// FUNCTION SHOW/HIDE JOB-ITEMS
function showHide(id, btnview) {
  const element = document.querySelector(`.${id}`);

  if (!element.classList.contains("hide")) {
    element.classList.add("hide");
    document.querySelector(`.${btnview}`).textContent = "↓ VIEW MORE";
  } else {
    element.classList.remove("hide");
    document.querySelector(`.${btnview}`).textContent = "↑ VIEW LESS";
  }
}
