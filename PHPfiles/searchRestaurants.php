<?php
include 'connection.php';
if(isset($_GET['r'])){
	$query = $_GET['r'];
}else{
	header('Location: restaurants.php');
}


// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = 'SELECT id FROM stephencook_group.restaurants where name like "%'.$query.'%"';
$result = mysqli_query($db, $sql);

if (!$result) {
    $message  = 'Invalid query: ' . mysqli_error() . "\n";
    $message .= 'Whole query: ' . $sql;
    die($message);
}

// Use result
// Attempting to print $result won't allow access to information in the resource
// One of the mysql result functions must be used
// See also mysql_result(), mysql_fetch_array(), mysql_fetch_row(), etc.
while ($row = mysqli_fetch_assoc($result)) {
	echo $row['id'];
	echo ",";

}
?>
