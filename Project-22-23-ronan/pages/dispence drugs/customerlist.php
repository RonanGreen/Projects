<!-- 
  Page name:customerlist.php
  Page purpose:show customer information
  Name:Ronan Green
  Student number:C00270395
  Date:20/03/23
-->
<?php

include '../assets/php/db_connection.php';

$sql = "use BigPharma;";



$sql = "SELECT customerID, firstName, secondName, customerAddress, dob, telephoneNumber FROM BigPharma.Customer WHERE deleted = false";

if (!$result = mysqli_query($conn, $sql)) {
    die("Error in querying the database: " . mysqli_error($conn));
}

echo "<select name='customerlist' id='customerlist' onclick = 'populate()'>";

while ($row = mysqli_fetch_array($result)) {
    $customerID = $row['customerID'];
    $firstName = $row['firstName'];
    $secondName = $row['secondName'];
    $customerAddress = $row['customerAddress'];
    $dob = $row['dob'];
    $telephoneNumber = $row['telephoneNumber'];
    $allText = "$customerAddress,$dob";
    echo "<option value='$allText'>$firstName $secondName - $allText</option>";
}
echo "</select>";

mysqli_close($conn);
?>