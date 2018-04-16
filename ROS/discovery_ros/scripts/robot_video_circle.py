#!/usr/bin/env python
# -*- coding: utf-8 -*-
import rospy
import time
import socket  
from  discovery_ros.msg import command
import json
import cv2
import numpy as np
import string

robot_command=command()
def Rovvideo():
	rospy.init_node('VideoCircle', anonymous=True)	
	pub = rospy.Publisher('chatterPY', command, queue_size=1)
	rate = rospy.Rate(10)# hz
	IPAddress = "rtsp://192.168.10.10:554/user=admin&password=&channel=1&stream=0.sdp?"
	cap=cv2.VideoCapture(IPAddress)
	#cap=cv2.VideoCapture(0)
	cv2.namedWindow('HoughCirlce')
	#while True:
	while not rospy.is_shutdown():
		ret,image=cap.read()
		gray_img = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
		img = cv2.medianBlur(gray_img,5)
		cimg = cv2.cvtColor(img,cv2.COLOR_GRAY2BGR)
		circles = cv2.HoughCircles(img,cv2.HOUGH_GRADIENT,1.8,10,param1=100,param2=40,minRadius=30,maxRadius=120)# 200  110
		circles = np.uint16(np.around(circles))
		for i in circles[0,:]:
			cv2.circle(image,(i[0],i[1]),i[2],(0,255,0),2)
			cv2.circle(image,(i[0],i[1]),2,(0,0,255),3)
			print("the location is ( %d , %d ),the radius is %d"%(i[0],i[1],i[2]))
			centerx = i[0]
			centery = i[1]
			radius = i[2]
			robot_command.right_y = (60-radius)*2
		pub.publish(robot_command)
		#rate.sleep()
		cv2.imshow("HoughCircles",image)
		if cv2.waitKey(10) == 27:
			break
	del(cap)
	cv2.destroyWindow('HoughCircle')

if __name__ == '__main__':
	Rovvideo()

