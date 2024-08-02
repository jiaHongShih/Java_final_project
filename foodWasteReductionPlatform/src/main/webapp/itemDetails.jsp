<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item Details</title>
    <link rel="stylesheet" href="pageStyle.css">
</head>
<body>
    <header class="dHeader">
        <img src="./Photos/LogoNoBG.png" alt="Logo" class="TopLogo">
       <a href="index.jsp"><button id="LogOutBTND">Log Out</button></a> 
    </header>
    <div class="main-content">
        <div class="item-details">
            <h2>Item Details</h2>
         
                <div class="form-group">
                    <label for="itemName">Name:</label>
                    <input type="text" id="itemName" name="itemName" value="">
                </div>
                <div class="form-group">
                    <label for="itemQuantity">Quantity:</label>
                    <input type="number" id="itemQuantity" name="itemQuantity" value="">
                </div>
                <div class="form-group">
                    <label for="itemPrice">Price:</label>
                    <input type="text" id="itemPrice" name="itemPrice" value="">
                </div>
                <div class="form-group">
                    <label for="itemExpiry">Expiry Date:</label>
                    <input type="date" id="itemExpiry" name="itemExpiry" value="">
                </div>
                <div class="form-group">
                    <label for="postedBy">Posted By:</label>
                    <input type="text" id="postedBy" name="postedBy" value="" >
                </div>
                <a href="retailer.jsp"><button>Save Changes</button></a>
            
        </div>
    </div>
   
</body>
</html>
