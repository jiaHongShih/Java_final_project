<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Playwrite+BE+VLG:wght@100..400&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Edu+AU+VIC+WA+NT+Hand:wght@400..700&family=Playwrite+BE+VLG:wght@100..400&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="pageStyle.css">
    <title>Retialer</title>
</head>

<body>

    <header>
        <img src="./Photos/LogoNoBG.png" alt="Logo" class="TopLogo">
        <h3 class="pTitle">Retailer</h3>
        <a href="index.html"><button id="LogOutBTN">LogOut</button></a>
    </header>

    <div class="main-content">
        <!-- div to hold all items posted for consumer to buy. -->
        <div class="ItemList">

        </div>
    </div>


    <div class="shopping-cart-sidebar">
        <h2>Add An Item</h2>
        <div class="Add-Item">

            <div id="TOA">
                <h4>Is this item for?</h4>
                <label for="">Consumer</label>
                <input type="radio" name="question" id="">
                <br>
                <label for="">Charitable-Organization</label>
                <input type="radio" name="question" id="">
            </div>
            <br>

            <div class="textfieldSign">
                <label for="Name">Name of product:</label>
                <input type="text" name="name" id="name" placeholder="Name of product">
                <p id="pName"></p>
            </div>

            <div class="textfieldSign">
                <label for="Name">Quantity:</label>
                <input type="text" name="name" id="name" placeholder="Quantity">
                <p id="Quantity"></p>
            </div>

            <div class="textfieldSign">
                <label for="Name">Expirty Date:</label>
                <input type="date" name="name" id="name" placeholder="Date">
                <p id="DateOfE"></p>
            </div>

            <div class="textfieldSign">
                <label for="Name">Price:</label>
                <input type="text" name="name" id="name" placeholder="Price">
                <p id="Price"></p>
            </div>

            


        </div>
        <button id="BuyBtn">Add Item</button>
    </div>

</body>

</html>