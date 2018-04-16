#!/usr/bin/env python
# -*- coding: utf-8 -*-
import rospy
import socket
import json
from  discovery_ros.msg import command

HOST = '192.168.10.200'
PORT =  8899
BUFSIZE = 512
ADDR = (HOST,PORT)

def callback(data):
    print("开始链接")
    tcpCliSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    tcpCliSock.connect(ADDR)
    MessageToRobot="{\"joystick\":{\"left_x\":%d,\"left_y\":%d,\"right_x\":%d,\"right_y\":%d}}\r\n" % (data.LeftorRight, data.UporDown,data.ClimborDive,data.ForwardorBackward)
    print(MessageToRobot)
    print("准备发送")
    tcpCliSock.send(MessageToRobot.encode()) #通过tcpsock给水下机器人发送控制命令
    print("已发送")

def RovConnect():
    rospy.init_node('sublistener', anonymous=True)
    rospy.Subscriber("chatterPY", command, callback)
    rospy.spin()

if __name__ == '__main__':
    RovConnect()



