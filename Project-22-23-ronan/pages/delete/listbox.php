<!-- 
  Page name:listbox.php
  Page purpose:show details drug
  Name:Ronan Green
  Student number:C00270395
  Date:20/03/23
-->
<?php
include '../assets/php/db_connection.php';
$sql = "use BigPharma;";


$sql = "SELECT drugID, brandName, genericName, form, strength, supplierID, usageInstructions, sideEffects, costPrice, retailPrice, reorderLevel, reorderQuantity  FROM BigPharma.Drug WHERE deleted = false";

if (!$result = mysqli_query($conn, $sql)) {
    die("Error in querying the database: " . mysqli_error($conn));
}

echo "<select name='listbox' id='listbox' onclick = 'populate()'>";

while ($row = mysqli_fetch_array($result)) {
    $drugID = $row['drugID'];
    $brandName = $row['brandName'];
    $genericName = $row['genericName'];
    $form = $row['form'];
    $strength = $row['strength'];
    $supplierID = $row['supplierID'];
    $usageInstructions = $row['usageInstructions'];
    $sideEffects = $row['sideEffects'];
    $costPrice = $row['costPrice'];
    $retailPrice = $row['retailPrice'];
    $reorderLevel = $row['reorderLevel'];
    $reorderQuantity = $row['reorderQuantity'];
    $allText = "$drugID,$brandName,$genericName,$form,$strength,$supplierID,$usageInstructions,$sideEffects,$retailPrice,$costPrice,$reorderLevel,$reorderQuantity ";
    echo "<option value='$allText'>$brandName</option>";
}
echo "</select>";

mysqli_close($conn);
?>