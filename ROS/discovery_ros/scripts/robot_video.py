# -*- coding: utf-8 -*-
import time
import socket  
import json
import cv2
import numpy as np
import string

def Rovvideo():
	IPAddress = "rtsp://192.168.10.10:554/user=admin&password=&channel=1&stream=0.sdp?"
	cap=cv2.VideoCapture(IPAddress)
	#cap=cv2.VideoCapture(0)
	cv2.namedWindow('HoughCirlce')
	while True:
	#while not rospy.is_shutdown():
		ret,image=cap.read()
		if ret == True:
			cv2.imshow("HoughCircles",image)
		if cv2.waitKey(10) == 27:
			break
	del(cap)
	cv2.destroyWindow('HoughCircle')

if __name__ == '__main__':
	Rovvideo()

