<!-- 
  Page name:Delete.php
  Page purpose:Delete drug
  Name:Ronan Green
  Student number:C00270395
  Date:20/03/23
-->
<?php
session_start();
include '../assets/php/db_connection.php';

$sql = "use BigPharma;";
if (!mysqli_query($conn, $sql)) {
    die("Error in selecting the database: " . mysqli_error($conn));
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $sql = "UPDATE Drug SET deleted = true WHERE drugID = '$_POST[delDrugId]'";

    if (! mysqli_query($conn,$sql))
    {
        echo "Error".mysqli_error($conn);
    }

    $_SESSION["drugID"] = $_POST['delDrugId'];
    $_SESSION["brandName"] = $_POST['delBrandName'];
    $_SESSION["genericName"] = $_POST['delGenericName'];
    $_SESSION["form"] = $_POST['delForm'];
    $_SESSION["strength"] = $_POST['delStrength'];

    if (mysqli_affected_rows($conn) != 0) {
        echo mysqli_affected_rows ($conn)." record(s) updated <br>";
        echo "The Drug ".$_POST['delBrandName'] . " has been updated";
    } else {
        echo "No records were changed";
    }

    mysqli_close($conn);
}

?>
<form action="delete.html.php" method="post">
    <input type="submit" value="Return to Previous Screen">
</form>

