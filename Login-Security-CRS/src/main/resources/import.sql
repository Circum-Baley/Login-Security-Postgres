-- INSERT Role
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('SUPPORT');
INSERT INTO role (name) VALUES ('ROOT');


-- INSERT Users
INSERT INTO users (username, password) VALUES ('john.doe', 'password123');
INSERT INTO users (username, password) VALUES ('jane.smith', 'securePwd');
INSERT INTO users (username, password) VALUES ('mike.jones', 'mySecretPass');
INSERT INTO users (username, password) VALUES ('susan.white', 'userPass123');
INSERT INTO users (username, password) VALUES ('chris.green', 'pass1234');
INSERT INTO users (username, password) VALUES ('lisa.adams', 'lisaPwd');
INSERT INTO users (username, password) VALUES ('robert.king', 'king123');
INSERT INTO users (username, password) VALUES ('emily.parker', 'emilyPass');
INSERT INTO users (username, password) VALUES ('david.wilson', 'david123');
INSERT INTO users (username, password) VALUES ('sarah.brown', 'sarahPwd');

-- INSERT UserInRole
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (1, 1);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (2, 2);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (3, 3);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (4, 3);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (5, 4);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (6, 3);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (7, 1);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (8, 2);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (9, 3);
INSERT INTO user_in_role (user_id_fk, role_id_fk) VALUES (10, 2);


-- INSERT Vehicles
INSERT INTO vehicle (patent, user_id_fk) VALUES ('ABC123', 1);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('XYZ456', 2);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('DEF789', 3);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('GHI456', 4);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('JKL123', 5);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('MNO789', 6);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('PQR123', 7);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('STU456', 8);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('VWX789', 9);
INSERT INTO vehicle (patent, user_id_fk) VALUES ('YZA123', 10);

-- INSERT Consumptions
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (50000.00, 'Gasoline refill', 12345, '2023-09-01', 1);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (60000.00, 'Diesel refill', 67890, '2023-09-02', 2);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (45000.00, 'Electricity charge', 24680, '2023-09-03', 3);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (55000.00, 'Gasoline refill', 13579, '2023-09-04', 4);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (62000.00, 'Diesel refill', 97531, '2023-09-05', 5);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (48000.00, 'Electricity charge', 86420, '2023-09-06', 6);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (51000.00, 'Gasoline refill', 75309, '2023-09-07', 7);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (61000.00, 'Diesel refill', 64298, '2023-09-08', 8);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (47000.00, 'Electricity charge', 53187, '2023-09-09', 9);
INSERT INTO consumption (amount, description, consumption_number, date_uploaded, vehicle_id_fk) VALUES (59000.00, 'Gasoline refill', 42076, '2023-09-10', 10);



-- INSERT Profiles
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('John', 'Doe', '1985-05-15', 1);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Jane', 'Smith', '1990-12-10', 2);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Mike', 'Jones', '1978-09-20', 3);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Susan', 'White', '1988-03-25', 4);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Chris', 'Green', '1995-11-05', 5);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Lisa', 'Adams', '1982-07-18', 6);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Robert', 'King', '1970-04-30', 7);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Emily', 'Parker', '1998-02-12', 8);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('David', 'Wilson', '1983-06-08', 9);
INSERT INTO profile (name, last_name, birth_date, user_id_fk) VALUES ('Sarah', 'Brown', '2000-09-28', 10);


-- INSERT Addresses
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('123 Main St', 'Apt 4B', 'New York', 1);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('456 Elm St', 'Unit 7C', 'Los Angeles', 2);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('789 Oak St', 'Suite 2D', 'Chicago', 3);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('101 Pine St', 'Apt 5E', 'San Francisco', 4);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('222 Maple St', 'Unit 8F', 'Miami', 5);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('333 Cedar St', 'Suite 3G', 'Dallas', 6);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('444 Birch St', 'Apt 6H', 'Boston', 7);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('555 Redwood St', 'Unit 9I', 'Seattle', 8);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('666 Willow St', 'Suite 4J', 'Phoenix', 9);
INSERT INTO address (street_address, building_number, city_name, profile_id_fk) VALUES ('777 Spruce St', 'Apt 7K', 'Denver', 10);

-- INSERT Devices
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Smartphone', 'Samsung', 'Galaxy S22', '2023-08-15', 'SN12345', 1);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Laptop', 'Dell', 'XPS 15', '2023-08-20', 'SN67890', 2);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Tablet', 'Apple', 'iPad Pro', '2023-08-25', 'SN13579', 3);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Smartwatch', 'Fitbit', 'Versa 4', '2023-08-30', 'SN24680', 4);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Desktop', 'HP', 'Pavilion 590', '2023-09-05', 'SN97531', 5);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Smart TV', 'LG', 'OLED C1', '2023-09-10', 'SN86420', 6);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Gaming Console', 'Sony', 'PlayStation 5', '2023-09-15', 'SN75309', 7);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Headphones', 'Bose', 'QuietComfort 45', '2023-09-20', 'SN64298', 8);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Camera', 'Canon', 'EOS R5', '2023-09-25', 'SN53187', 9);
INSERT INTO device (name, brand, model, date_acquisition, serial_number, profile_id_fk) VALUES ('Fitness Tracker', 'Garmin', 'Forerunner 945', '2023-09-30', 'SN42076', 10);






