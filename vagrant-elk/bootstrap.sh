2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
#!/usr/bin/env bash
wget -O - http://packages.elasticsearch.org/GPG-KEY-elasticsearch | apt-key add -
echo 'deb http://packages.elasticsearch.org/elasticsearch/1.3/debian stable main' | sudo tee /etc/apt/sources.list.d/elasticsearch.list
echo 'deb http://packages.elasticsearch.org/logstash/1.4/debian stable main' | sudo tee /etc/apt/sources.list.d/logstash.list
echo 'deb http://packages.elasticsearch.org/logstashforwarder/debian stable main' | sudo tee /etc/apt/sources.list.d/logstashforwarder.list
# update apt
sudo apt-get update
# install java
sudo apt-get install openjdk-7-jre-headless nginx apache2-utils -y
sudo apt-get install elasticsearch logstash logstash-forwarder -y
# Configure logstash forwarder
cd /etc/init.d/
sudo wget https://raw.github.com/elasticsearch/logstash-forwarder/master/logstash-forwarder.init -O logstash-forwarder
sudo chmod +x logstash-forwarder
sudo update-rc.d logstash-forwarder defaults
sudo service elasticsearch start
 
# install head
sudo /usr/share/elasticsearch/bin/plugin -install mobz/elasticsearch-head
# Install Kibana
cd ~
wget https://download.elasticsearch.org/kibana/kibana/kibana-3.1.0.tar.gz
tar zxvf kibana-3.1.0.tar.gz
 
mv kibana-3.1.0 /usr/share/kibana3
sed -i 's/hostname+":9200/hostname+":80/' /usr/share/kibana3/config.js
wget https://github.com/elasticsearch/kibana/raw/kibana3/sample/nginx.conf
cp nginx.conf /etc/nginx/sites-available/default
sudo service nginx restart
- See more at: http://www.dhillonblog.com/2014/08/elasticsearch-logstash-kibana-using-vagrant-virtualbox-ubuntu-14-04/#sthash.pTV5RHVF.dpuf
