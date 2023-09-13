<!-- 
  Page name:Delete.html.php
  Page purpose:Delete drug
  Name:Ronan Green
  Student number:C00270395
  Date:20/03/23
-->
<?php
include '../assets/php/db_connection.php';

$sql = "use BigPharma;";
mysqli_query($conn, $sql);
?>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"  href="delete.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" /> <!-- symbols -->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">

</head> 
<body>
<div class="horizonal-nav">
        <span id="time"></span>
            <div class="logo-container">
            <i class="ri-capsule-line"></i>
            <span id="logo-title"> | BP</span>
        </div>
        <div class="account-container">
            <button>
                <span class="accountId">Logout</span>
            </button>
        </div>
    </div>
    <div class="main-container">
        <div class="vertical-nav">
            <a href="#" >Counter Sales</a>
            <a href="menu.php"class="selected">Dispense Drugs</a>
            <a href="#">Stock Control</a>
            <a href="#">Supplier Accounts</a>
            <a href="#">File Maintenance</a>
            <a href="#">Reports</a>
        </div>
<script>
function populate() {
  var sel = document.getElementById("listbox"); 
  var result;
 	result = sel.options[sel.selectedIndex].value; 
  var drugDetails = result.split(','); 
  document.getElementById("display").innerHTML = "The details of the selected person are: " + result;
  document.getElementById("delDrugId").value = drugDetails[0];
  document.getElementById("delBrandName").value = drugDetails[1]; 
  document.getElementById("delGenericName").value = drugDetails[2]; 
  document.getElementById("delForm").value = drugDetails[3];
  document.getElementById("delStrength").value = drugDetails[4];
  document.getElementById("delSupplier").value = drugDetails[5];
}
function confirmCheck() {
  var response = confirm('Are you sure you want to save these changes?'); 
  if (response) {
    document.getElementById("delDrugId").disabled = false; 
    document.getElementById("delBrandName").disabled = false;
    document.getElementById("delGenericName").disabled = false;
    document.getElementById("delForm").disabled = false; 
    document.getElementById("delStrength").disabled = false;
    document.getElementById("delSupplier").value  =false;
    return true;
  } 
  else 
  {
    populate();
    return false;
  }
}
</script>

<p id="display"> </p>
<main>
<div class="mainForm">
    <div class="formText">
    <div class="form-container">
      <div class="myForm"> 
        <form name="myForm" action="delete.php" onsubmit="return confirmCheck()" method="post">
          <h1> Delete a Drug</h1>
          <h4>Please select a drug and then click the amend button if you wish to update</h4>
            <div class="list">
            <?php include 'listbox.php';?>
            </div>
            <label for="delDrugId">Drug ID </label>
            <input type="text" name="delDrugId" id="delDrugId" >
            <label for="delBrandName" >Brand Name </label>
            <input type="text" name="delBrandName"  id="delBrandName" >
            <label for="delGenericName" >Generic Address</label>
            <input type="text" name="delGenericName" id="delGenericName" >
            <label for="delForm">Form </label>
            <input type="text" name="delForm" id="delForm" >
            <label for="delStrength">Strength </label>
            <input type="text" name="delStrength" id="delStrength" >

  <br>
<input type="submit" value="Delete the record" class="button">

<?php
    if(ISSET($_SESSION["drudID"])) {
        echo "<h1 class = 'myMessage'>Record deleted for" . $_SESSION["brandName"]
        . "</h1>";
    }
    ?>
    </form>
     </div>
</div>
    </div>
  </div>
</main>
    </body>
    </html>