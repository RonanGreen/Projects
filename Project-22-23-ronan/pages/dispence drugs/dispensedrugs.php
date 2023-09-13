<!-- 
  Page name:dispensedrugs.php
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

<!-- 
    Icons obtained from https://remixicon.com/ and https://fonts.google.com/icons 
 -->

<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>Pharmacy</title>
    <link rel="stylesheet" href="addDrug.css"> <!-- links stylesheets -->
   
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

        
<main>
<div class="mainForm">
    <div class="formText">
    <div class="form-container">
      <div class="myForm"> 
<form name="myForm" action="dispenseinsert.php" onsubmit="return confirmCheck()" method="post">
<h1>Prescription</h1>
<h4>Please select a customer you wish to make a presciption for</h4>                      
                    <div class="list">
                    <?php include 'customerlist.php';?>
                    </div>
                    <?php include '../assets/php/db_connection.php'; 
                    $sql = "use BigPharma;";
                    mysqli_query($conn, $sql);
                    ?>
                    <div class="input-wrapper">
					<label for="doctorID">Doctor:</label>
                    <select name="doctorID" id="doctorID" placeholder="Dr "required >
                    <?php 
                    $sql = "SELECT * FROM Doctor";
                    $result = mysqli_query($conn, $sql);

                    if ($result->num_rows > 0) {
                        while($row = $result->fetch_assoc()) {    
                           echo '<option value="' . $row['doctorID'] . '">' . $row['lastName'] . '</option>';
                      }
                 }
                 ?>
                </select>
                </div>
                <div class="input-wrapper">
                <label for="date">date:</label>                     
                <input  type="date" name="date" id="date" required/>
                </div>
                <div class="input-wrapper">
                <div id="drug_entries">
                    <div class="drug_entry">
                    <label for="genericName">drug:</label>
                    <select name="genericName" id="genericName" placeholder="Dr "required >
                    <?php 
                    $sql = "SELECT * FROM Drug";
                    $result = mysqli_query($conn, $sql);

                    if ($result->num_rows > 0) {
                        while($row = $result->fetch_assoc()) {    
                           echo '<option value="' . $row['drugID'] . '">' . $row['genericName'] . '</option>';
                      }
                 }
                 ?>
                 </select>
                 </div>
                 <div class="input-wrapper">
                    <label for="sizeOfDosage">Size of dosage:</label>                     
                    <input  type="text" name="sizeOfDosage" id="sizeOfDosage" placeholder="2 tablets" required maxlength="30"/>
                </div>
                <div class="input-wrapper">
                    <label for="frequencyOfDosage">Frequency of dosage:</label>                     
                    <input  type="text" name="frequencyOfDosage" id="frequencyOfDosage" placeholder="3 times a day" required maxlength="30"/>
                </div>
                <div class="input-wrapper">
                    <label for="lengthOfDosage">Length of dosage:</label>                     
                    <input  type="text" name="lengthOfDosage" id="lengthOfDosage" placeholder="5 days" required maxlength="30"/>
                </div>
                <div class="input-wrapper">  
                    <label for="instructions">Usage Instruction:</label>                     
                    <input  type="text" name="instructions" id="instructions" placeholder="with food" required maxlength="100"/>
                </div>
            </div>
        </div>
        
        
        <input type="submit" value="Submit Prescription" class="button">
    </form>

    

                         
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