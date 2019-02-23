#Run these commands in order to configure the instance
# NOTE: DOES NOT WORK AS A SCRIPT YET

sudo su
yum -y update
yum -y install vim wget gcc
yum -y install centos-release-scl
yum -y install rh-python36 python3-devel
scl enable rh-python36 bash
exit
wget http://repo.mysql.com/mysql-community-release-el7-5.noarch.rpm
sudo su
sudo rpm -ivh mysql-community-release-el7-5.noarch.rpm
yum -y update
yum -y install mysql mysql-server mysql-libs mysql-devel
systemctl start mysqld
chkconfig --levels 235 mysqld on
yum -y install python36-setuptools
easy_install-3.6 pip
exit
pip install flask-restful mysqlclient --user
