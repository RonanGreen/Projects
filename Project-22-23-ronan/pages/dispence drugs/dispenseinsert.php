<!-- 
  Page name:dispenseinsert.php
  Page purpose:insert drug
  Name:Ronan Green
  Student number:C00270395
  Date:20/03/23
-->
<?php

include '../assets/php/db_connection.php';
                    
                    $sql = "use BigPharma;"; //connects to database
                    mysqli_query($conn, $sql);
                    if (!mysqli_query($conn, $sql) ){
                         die ("An Error in the SQL Query: " . mysqli_error($conn)); //if not connected it dies
                    }

                    $doctorID = $_POST['doctorID']; //declares variable
                    $date = $_POST['date'];
                    $genericName = $_POST['genericName'];
                    $sizeOfDosage =$_POST['sizeOfDosage'];
                    $frequencyOfDosage = $_POST['frequencyOfDosage'];
                    $lengthOfDosage = $_POST['lengthOfDosage'];
                    $instructions = $_POST['instructions'];



                    $sql = "SELECT * FROM DispenseDrugs"; //selects drugs table

                    $result = mysqli_query($conn, $sql); //gets information

                    $sql = "Insert into DispenseDrugs(doctorID,date,genericName,sizeOfDosage,frequencyOfDosage,lengthOfDosage,instructions)
                    VALUES ('$doctorID','$date','$genericName','$sizeOfDosage','$frequencyOfDosage','$lengthOfDosage','$instructions');"; //sends info to database

                    mysqli_close($conn) ;

                    ?>


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
            <a href="#"class="selected">Dispense Drugs</a>
            <a href="#">Stock Control</a>
            <a href="#">Supplier Accounts</a>
            <a href="#">File Maintenance</a>
            <a href="#">Reports</a>
        </div>

         
        <main>
            <div class="mainForm">
                <div class="formText">
                    <i class="ri-capsule-line"></i>
        <form method="post"  action="dispensedrugs.php">
        <h3 class="title2">Entry Added</h3>
        <input type="submit" value="Return to Previous Screen" class="button">
        </form>
        </div>
            </div>
        </main>
     </body>
    <script src="../assets/js/date.js"></script> 
</html>