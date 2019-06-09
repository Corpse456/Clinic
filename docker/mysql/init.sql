create user 'testuser'@'localhost' identified by 'testpass';
grant all privileges on *.* to 'testuser'@'localhost';
flush privileges;