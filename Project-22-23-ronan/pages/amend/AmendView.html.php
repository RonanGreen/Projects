<!-- 
  Page name:AmendView.html.php
  Page purpose:amends drug
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
<link rel="stylesheet"  href="AmendView.css">
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
  document.getElementById("amendDrugId").value = drugDetails[0];
  document.getElementById("amendBrandName").value = drugDetails[1]; 
  document.getElementById("amendGenericName").value = drugDetails[2]; 
  document.getElementById("amendForm").value = drugDetails[3];
  document.getElementById("amendStrength").value = drugDetails[4];
  document.getElementById("amendSupplier").value = drugDetails[5];
  document.getElementById("amendUsageInstructions").value = drugDetails[6];
  document.getElementById("amendSideEffects").value = drugDetails[7];
  document.getElementById("amendCostPrice").value = drugDetails[8];
  document.getElementById("amendRetailPrice").value = drugDetails[9];
  document.getElementById("amendReorderLevel").value = drugDetails[10];
  document.getElementById("amendReorderQuantity").value = drugDetails[11];
}

function toggleLock() {
  if (document.getElementById("amendViewbutton").value == "Amend Details") {
    document.getElementById("amendBrandName").disabled = false;
    document.getElementById("amendGenericName").disabled = false;
    document.getElementById("amendForm").disabled = false; 
    document.getElementById("amendStrength").disabled = false;
    document.getElementById("amendSupplier").disabled = false; 
    document.getElementById("amendUsageInstructions").disabled = false; 
	  document.getElementById("amendSideEffects").disabled = false; 
    document.getElementById("amendCostPrice").disabled = false; 
    document.getElementById("amendRetailPrice").disabled = false; 
	  document.getElementById("amendReorderLevel").disabled = false; 
    document.getElementById("amendReorderQuantity").disabled = false;  
    document.getElementById("amendViewbutton").value = "View Details";
  } else {
    document.getElementById("amendBrandName").disabled = true;
    document.getElementById("amendGenericName").disabled = true;
    document.getElementById("amendForm").disabled = true; 
    document.getElementById("amendStrength").disabled = true; 
    document.getElementById("amendSupplier").disabled = true; 
    document.getElementById("amendUsageInstructions").disabled = true; 
	  document.getElementById("amendSideEffects").disabled = true; 
    document.getElementById("amendCostPrice").disabled = true; 
    document.getElementById("amendRetailPrice").disabled = true; 
	  document.getElementById("amendReorderLevel").disabled = true; 
    document.getElementById("amendReorderQuantity").disabled = true; 
    document.getElementById("amendViewbutton").value = "Amend Details";
  }
}

function confirmCheck() {
  var response = confirm('Are you sure you want to save these changes?'); 
  if (response) {
    document.getElementById("amendDrugId").disabled = false; 
    document.getElementById("amendBrandName").disabled = false;
    document.getElementById("amendGenericName").disabled = false;
    document.getElementById("amendForm").disabled = false; 
    document.getElementById("amendStrength").disabled = false;
    document.getElementById("amendSupplier").disabled = false;
    document.getElementById("amendUsageInstructions").disabled = false; 
	  document.getElementById("amendSideEffects").disabled = false; 
    document.getElementById("amendCostPrice").disabled = false; 
    document.getElementById("amendRetailPrice").disabled = false; 
	  document.getElementById("amendReorderLevel").disabled = false; 
    document.getElementById("amendReorderQuantity").disabled = false;
    return true;
  } else 
  {
    populate();
    toggleLock();
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
<input type="button" value="Amend Details" id="amendViewbutton" onclick="toggleLock()" class="button">
<form name="myForm" action="AmendView.php" onsubmit="return confirmCheck()" method="post">
<h2> Amend/View a Person</h2>
<h4>Please select a person and then click the amend button if you wish to update</h4>
<div class=list>
<?php include 'listbox.php';?>
</div>
<label for="amendDrugId">Drug ID </label>
<input type="text" name="amendDrugId" id="amendDrugId" disabled>
<label for="amendBrandName" >Brand Name </label>
<input type="text" name="amendBrandName"  required id="amendBrandName" maxlength="30" disabled >
<label for="amendGenericName" required>Generic Address</label>
<input type="text" name="amendGenericName" id="amendGenericName" maxlength="30" disabled>
<label for="amendForm" required>Form </label>
<input type="text" name="amendForm" id="amendForm" disabled maxlength="20">
<label for="amendStrength" required>Strength </label>
<input type="text" name="amendStrength" id="amendStrength" disabled maxlength="20">
<label for="amendSupplier">Supplier Name:</label>
<input name="amendSupplier" id="amendSupplier"  disabled required >
<label for="amendUsageInstructions">Usage Instructions </label>
<input type="text" name="amendUsageInstructions" id="amendUsageInstructions" disabled required maxlength="100">
<label for="amendSideEffects">Side Effects </label>
<input type="text" name="amendSideEffects" id="amendSideEffects" disabled required maxlength="100">
<label for="amendCostPrice" required >Cost Price </label>
<input type="number" name="amendCostPrice" id="amendCostPrice" disabled  maxlength="15">
<label for="amendRetailPrice" required >Retail Price </label>
<input type="number" name="amendRetailPrice" id="amendRetailPrice" disabled maxlength="15">
<label for="amendReorderLevel" required >Reorder Level </label>
<input type="number" name="amendReorderLevel" id="amendReorderLevel" disabled maxlength="15">
<label for="amendReorderQuantity" required >Reorder Quantity </label>
<input type="number" name="amendReorderQuantity" id="amendReorderQuantity" disabled maxlength="15">

  

<br>
<input type="submit" value="Save Changes" class="button">

</form>
     </div>
</div>
    </div>
  </div>
</main>
</body>
</html>