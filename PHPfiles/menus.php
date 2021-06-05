<?php
    $conn=mysqli_connect('localhost','jordan.towse','nLasvQ88NbGReRwY','stephencook_group');
    $sql="SELECT * FROM restaurants";
    if(mysqli_connect_errno()){
echo "Failed To Connect: " .mysqli_connect_error();
    }
    $rs=mysqli_query($conn,$sql);
    if(!$rs){
die("Couldn't get data " .mysql_error());
    }
    while($row = mysqli_fetch_array($rs)){
        $res=$row['name'];
        $men=$row['menu'];
        echo "$res | $men <br />";
    }
    mysqli_close($con);
?>
