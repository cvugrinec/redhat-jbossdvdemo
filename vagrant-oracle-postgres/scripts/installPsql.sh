yum -y install postgresql
yum -y install postgresql-server
service postgresql initdb
createdb employees

