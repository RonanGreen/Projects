const date = new Date();
document.getElementById("time").innerHTML = date.toLocaleDateString();
function confirmSubmit() {
    var response = confirm("Are you sure you want to submit this form?");
    if (response == true) {
      document.getElementById("myForm").submit();
    } else {
      return false;
    }
  }