# Linux shell script to configure a Centos7 Instance
#   Written by Zachary Chester @2/22/2019

# Become root user
sudo -u root -i

# Install epel
yum -y install epel-release

# Install useful tools e.g. vim, wget
yum -y install vim wget

# Install python3
#   centos-release-scl allows python3 to work alongside python2
yum -y install centos-release-scl
yum -y install rh-python36 python3-devel
scl enable rh-python36 bash

# Install mysql and necessary libraries
yum -y install mysql mysql-server mysql-libs mysql-server mysql-devel

# Start mysql-service automatically during boot
service mysqld start
chkconfig --levels 235 mysqld on

# Install pip for python3
yum -y install python36-setuptools
easy_install-3.6 pip

# Update pip to latest version
pip install --upgrade pip

# Install necessary python libraries
pip install flask-restful mysqlclient

# Exit out of root user
exit
