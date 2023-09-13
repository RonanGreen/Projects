<!-- 
  Page name:addDruginsert.php
  Page purpose:add drug
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

                    $brandName = $_POST['brandname']; //declares variable
                    $genericName = $_POST['genericname'];
                    $form = $_POST['form'];
                    $strength = $_POST['strength'];
                    $supplierID = $_POST['supplier'];
                    $usageInstructions = $_POST['usage'];
                    $sideEffects = $_POST['effects'];
                    $costPrice = $_POST['cost'];
                    $retailPrice = $_POST['retail'];
                    $reorderLevel= $_POST['reorder'];
                    $reorderQuantity = $_POST['quantity'];


                    $sql = "SELECT * FROM Drug"; //selects drugs table

                    $result = mysqli_query($conn, $sql); //gets information

                    while($row = mysqli_fetch_array($result)){ 
                        $last_id = $row['drugID']; 
                    }

                    $new_id = $last_id+1; //increments last id

                    $sql = "Insert into Drug(drugID,brandName, genericName, form, strength,
                    supplierID, usageInstructions, sideEffects, costPrice, retailPrice, reorderLevel, reorderQuantity)
                    VALUES ('$new_id','$brandName','$genericName','$form','$strength','
                    $supplierID','$usageInstructions','$sideEffects','$costPrice','$retailPrice','$reorderLevel','$reorderQuantity');"; //sends info to database
                     mysqli_close($conn) ;

                    ?>


<html lang="en">
<head>
    
    <title>Pharmacy</title>
    <link rel="stylesheet" href="addDrug.css"> <!-- links stylesheets -->
   
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=MaDDterial+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" /> <!-- symbols -->
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
        <form method="post"  action="addDrug.php">
        <h3 class="title2">Entry Added</h3>
        <input type="submit" value="Return to Previous Screen">
        </form>
        </div>
            </div>
        </main>
     </body>
    <script src="../assets/js/date.js"></script> 
</html>