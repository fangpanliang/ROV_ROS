#!/usr/bin/env python
# -*- coding: utf-8 -*-
import MySQLdb
import MySQLdb.cursors
import datetime
import time
import socket  
import json
import re
import sys
from sys import argv

#HOST = '192.168.10.200'
#PORT =  8899
BUFSIZE = 1024

def RovConnect():
	if len(sys.argv) < 3:
		print 'argv num err'
		return
	HOST = argv[1]
	PORT = argv[2]
	ADDR = (HOST,int(PORT))
	while(True):
		try:
			tcpCliSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
			tcpCliSock.connect(ADDR)
			time.sleep( 0.3)#s级的延时是支持的 
			tcpData = tcpCliSock.recv(BUFSIZE)  #接受数据
			tup = re.search('ros_message', tcpData).span()
			tup2 = re.search('}', tcpData[tup[1]:]).span()
			tup3 = re.search('oceanbot', tcpData[tup[1]:]).span()
			tcpData = '{'+tcpData[tup[1]+tup3[1]+1:tup[1]+tup2[0]+1]#找出完整的json
			print tcpData
			tcpCliSock.close()
			json_data=json.loads(tcpData) #json 解析
			DbConnect(json_data['rov_roll'],json_data['rov_yaw'],json_data['rov_pitch'],json_data['rov_depth'],json_data['rov_speed'])
		except Exception,e:
			print 'Error: ',e
			time.sleep( 1 )
	
def DbConnect(roll,yaw,pitch,depth,speed):
	conn = MySQLdb.connect(host='39.106.135.226',user='root',passwd='root',db='rov_ros',port=3306,cursorclass=MySQLdb.cursors.DictCursor)
	cur=conn.cursor()
	dt=datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
	value = [roll,yaw,pitch,depth,speed,dt]
	cur.execute('INSERT INTO rov(roll,yaw,pitch,depth,speed,times) values(%s,%s,%s,%s,%s,%s)',value)
	conn.commit()
	cur.close()
	conn.close()

if __name__ == '__main__':
	RovConnect()
