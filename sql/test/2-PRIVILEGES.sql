  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'adminippietest'@'localhost' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `ippie-db-test`.* TO 'adminippietest'@'localhost' WITH GRANT OPTION;

  FLUSH PRIVILEGES;
