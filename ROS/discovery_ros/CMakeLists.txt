cmake_minimum_required(VERSION 2.8.3)
project(discovery_ros)

find_package(catkin REQUIRED COMPONENTS
  message_generation
  roscpp
  rospy
  std_msgs
)


 add_message_files(  
  FILES  
  command.msg  
  #robotdata.msg  
)  
generate_messages(  DEPENDENCIES  std_msgs)

catkin_package()

catkin_install_python(PROGRAMS scripts/robot_connect_and_send.py
  DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION})
