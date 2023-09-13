<!-- 
  Page name:AmendView.php
  Page purpose:amend drug
  Name:Ronan Green
  Student number:C00270395
  Date:20/03/23
-->
<?php
include '../assets/php/db_connection.php';

$sql = "use BigPharma;";
mysqli_query($conn, $sql);
// Execute the query to select the database
if (! mysqli_query($conn, $sql)) {
    die("Error in selecting the database: " . mysqli_error($conn));
}

$sql = "UPDATE Drug SET 
        brandName = '$_POST[amendBrandName]',
        genericName = '$_POST[amendGenericName]',  
        form = '$_POST[amendForm]', 
        strength = '$_POST[amendStrength]',
        supplierID = '$_POST[amendSupplier]',
        usageInstructions = '$_POST[amendUsageInstructions]', 
        sideEffects = '$_POST[amendSideEffects]',
        costPrice = '$_POST[amendCostPrice]',
        retailPrice = '$_POST[amendRetailPrice]',
        reorderLevel = '$_POST[amendReorderLevel]',
        reorderQuantity = '$_POST[amendReorderQuantity]'
        WHERE drugID = $_POST[amendDrugId]";

if (! mysqli_query($conn, $sql)) {
    echo "Error ".mysqli_error($conn);
} else {
    if (mysqli_affected_rows($conn) != 0) {
        echo mysqli_affected_rows ($conn)." record(s) updated <br>";
        echo "The Drug ".$_POST['amendBrandName'] . " has been updated";
    } else {
        echo "No records were changed";
    }
}
mysqli_close($conn);
?>
<form action="AmendView.html.php" method="post">
    <input type="submit" value="Return to Previous Screen">
</form>
	   
	   