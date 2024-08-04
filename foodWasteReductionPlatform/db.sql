-- Step 1: Drop the database if it already exists (Optional)
DROP DATABASE IF EXISTS FoodWaste;

-- Step 2: Create the database
CREATE DATABASE FoodWaste;

-- Step 3: Use the database
USE FoodWaste;

-- Step 4: Create the tables
CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    userType VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

CREATE TABLE Questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    questionDescription VARCHAR(255) NOT NULL
);

CREATE TABLE UserQuestions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    questionID INT NOT NULL,
    userID INT NOT NULL,
    answer VARCHAR(255) NOT NULL,
    FOREIGN KEY (userID) REFERENCES Users(id),
    FOREIGN KEY (questionID) REFERENCES Questions(id)
);

CREATE TABLE FoodItems (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    expirationDate DATE NOT NULL,
    price DECIMAL(10, 2),
    startDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    endDate TIMESTAMP,
    FOREIGN KEY (userID) REFERENCES Users(id)
);

CREATE TABLE Subscriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    phoneNum VARCHAR(255),
    communicationMethod ENUM('email', 'phone') NOT NULL,
    foodPreferences VARCHAR(255),
    FOREIGN KEY (userID) REFERENCES Users(id)
);

CREATE TABLE Claims_Perchase (
    id INT AUTO_INCREMENT PRIMARY KEY,
    foodItemID INT NOT NULL,
    userID INT NOT NULL,
    quantity INT NOT NULL,
    claimedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (foodItemID) REFERENCES FoodItems(id),
    FOREIGN KEY (userID) REFERENCES Users(id)
);
