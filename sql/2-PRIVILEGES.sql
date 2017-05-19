  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'adminippie'@'localhost' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `ippie-db`.* TO 'admincdb'@'localhost' WITH GRANT OPTION;

  FLUSH PRIVILEGES;
