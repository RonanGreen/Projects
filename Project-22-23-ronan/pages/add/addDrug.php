<!-- 
  Page name:addDrug.php
  Page purpose:add drug
  Name:Ronan Green
  Student number:C00270395
  Date:20/03/23
-->
<?php
//use database connection file
include '../assets/php/db_connection.php';
//set up connection to database
$sql = "use BigPharma;";
mysqli_query($conn, $sql);
?>

<!-- 
    Icons obtained from https://remixicon.com/ and https://fonts.google.com/icons 
 -->

<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>Pharmacy</title>
    <!-- Link the addDrug CSS stylesheet -->
    <link rel="stylesheet" href="addDrug.css"> <!-- links stylesheets -->
   <!-- Link Material Icons Outlined -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" /> <!-- symbols -->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
</head>
<body>
    <!-- Horizontal navigation bar -->
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
    <!-- Main container -->
    <div class="main-container">
        <!-- Vertical navigation menu -->
        <div class="vertical-nav">
            <a href="#" >Counter Sales</a>
            <a href="menu.php"class="selected">Dispense Drugs</a>
            <a href="#">Stock Control</a>
            <a href="#">Supplier Accounts</a>
            <a href="#">File Maintenance</a>
            <a href="#">Reports</a>
        </div>
        <script>
        function confirmCheck() {
            // Show confirmation 
  var response = confirm('Are you sure you want to save these changes?'); 
  // If the user confirms, enable all input fields and return true and submit the form
  if (response) {
    document.getElementById("drugId").disabled = false; 
    document.getElementById("brandname").disabled = false;
    document.getElementById("genericname").disabled = false;
    document.getElementById("form").disabled = false; 
    document.getElementById("strength").disabled = false;
    document.getElementById("supplier").disabled = false;
    document.getElementById("usage").disabled = false; 
	document.getElementById("effects").disabled = false; 
    document.getElementById("cost").disabled = false; 
    document.getElementById("retail").disabled = false; 
	document.getElementById("reorder").disabled = false; 
    document.getElementById("reorder").disabled = false;

    return true;
  } else 
  {
    // If the user cancels, return false do not submit the form
    return false;
  }
}
</script>
<!-- Main -->
        <main>
            <div class="mainForm">
                <div class="formText">
                    <i class="ri-capsule-line"></i>
                    <form method="post"  action="addDruginsert.php" onsubmit="return confirmCheck()">
                    <h1 class="title2">Add Drugs</h1>                       
                        <div>
                        <label for="brandname">Brand Name:</label>                       
                        <input  type="text" name="brandname" id="brandname" placeholder="Panadol" required  maxlength="30"/>
                        </div>
                        <div>
                        <label for="genericname">Generic Name:</label>                       
                        <input  type="text" name="genericname" id="genericname" placeholder="Paracetamol" required maxlength="30"/>
                        </div>
                        <div>
                        <label for="form">Form:</label>                       
                        <input  type="text" name="form" id="form" placeholder="Pill" required maxlength="20"/>
                        </div>
                        <div>
                        <label for="strength">Strength:</label>                       
                        <input  type="text" name="strength" id="strength" placeholder="Strong" required maxlength="20"/>
                        </div>
                        <div class="input box">
							<label for="supplier">Supplier Name:</label>
							<select name="supplier" id="supplier" required >
							<?php 
                            // SQL query to get all records from the Suppliers table
							$sql = "SELECT * FROM Suppliers";
                            // Execute the SQL query and store the result in a variable
							$result = mysqli_query($conn, $sql);

                            
							// Check if there are records in the result
							if ($result->num_rows > 0) {
                                // Iterate through each record in the result
								while($row = $result->fetch_assoc()) {	
                                        // Create an option element for each record, with the supplierID as its value and supplierName as the display text
									echo '<option value="' . $row['supplierID'] . '">' . $row['supplierName'] . '</option>';
								}
							}
							?>
							</select>
						</div>
                        <div>
                        <label for="ID">Usage Instruction:</label>                     
                        <input  type="text" name="usage" id="usage" placeholder="Take twice a day" required maxlength="100"/>
                        </div>
                        <div>
                        <label for="ID">Side Effects:</label>                       
                        <input  type="text" name="effects" id="effects" placeholder="Death" required maxlength="100"/>
                        </div>
                        <div>
                        <label for="ID">Cost Price:</label>                       
                        <input  type="number" name="cost" id="cost" placeholder="$"  required maxlength="15"/>
                        </div>
                        <div>
                        <label for="ID">Retail Price:</label>                       
                        <input  type="number" name="retail" id="retail" placeholder="$"  required maxlength="15"/>
                        </div>
                        <div>
                        <label for="ID">Reorder Level:</label>                       
                        <input  type="text" name="reorder" id="reorder" placeholder="0000" maxlength="15"/>
                        </div>
                        <div>
                        <label for="ID">Reorder Quantity:</label>                       
                        <input  type="text" name="quantity" id="quantity" placeholder="0000" maxlength="15"/>
                        </div>

                         <br>
                         <input  type="submit" name="Submit" value="Submit"() class="button"/>
                         <input  type="reset" name="Clear" value="Clear" class="button"/>
                    </div> 
                    
                    </form>
                    
                </div>
            </div>
        </main>
    </div>
</body>
    <script src="../assets/js/date.js">
    </script>
</html>