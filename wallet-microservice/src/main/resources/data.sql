
CREATE TABLE IF NOT EXISTS players
(
    identity VARCHAR(11) PRIMARY KEY,
    fname VARCHAR(50),
    lname VARCHAR(50),
    balance DECIMAL(15)
);

CREATE TABLE IF NOT EXISTS transactions
(
    transaction_id LONG PRIMARY KEY AUTO_INCREMENT,
	identity VARCHAR(11) ,
    amount DECIMAL(15) ,
    transaction_type INTEGER
);

