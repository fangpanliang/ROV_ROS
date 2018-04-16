#include <ros/ros.h>
//#include <geometry_msgs/Twist.h>
#include <signal.h>
#include <termios.h>
#include <stdio.h>
#include "/home/joke/catkin_ws/devel/include/discovery_ros/command.h"
#include <unistd.h>

#define KEYCODE_A 65
#define KEYCODE_W 87
#define KEYCODE_S 83
#define KEYCODE_D 68

#define KEYCODE_I 73
#define KEYCODE_J 74
#define KEYCODE_K 75
#define KEYCODE_L 76

 discovery_ros::command robot_command;
class TeleopTurtle
{
public:
  TeleopTurtle();
  void keyLoop();

private:

  
  ros::NodeHandle nh_;
  ros::Publisher discovery_pub_;
  //int left_x,left_y,right_x,right_y 
  
};

TeleopTurtle::TeleopTurtle()
{
  discovery_pub_= nh_.advertise<discovery_ros::command>("chatterPY", 1);
}

int kfd = 0;
struct termios cooked, raw;

void quit(int sig)
{
  (void)sig;
  tcsetattr(kfd, TCSANOW, &cooked);
  ros::shutdown();
  exit(0);
}


int main(int argc, char** argv)
{
  ros::init(argc, argv, "teleop_dicvoery");
  TeleopTurtle teleop_turtle;

  signal(SIGINT,quit);

  teleop_turtle.keyLoop();
  
  return(0);
}


void TeleopTurtle::keyLoop()
{
  char c;
  bool dirty=false;


  // get the console in raw mode                                                              
  tcgetattr(kfd, &cooked);
  memcpy(&raw, &cooked, sizeof(struct termios));
  raw.c_lflag &=~ (ICANON | ECHO);
  // Setting a new line, then end of file                         
  raw.c_cc[VEOL] = 1;
  raw.c_cc[VEOF] = 2;
  tcsetattr(kfd, TCSANOW, &raw);

  puts("Reading from keyboard");
  puts("---------------------------");
  puts("Use arrow keys to move the Discovry ROV.");


  for(;;)
  {
    // get the next event from the keyboard  
    if(read(kfd, &c, 1) < 0)
    {
      perror("read():");
      exit(-1);
    }

        robot_command.ClimborDive= 0; 
	robot_command.ForwardorBackward= 0; 
	robot_command.LeftorRight= 0; 
	robot_command.UporDown= 0; 
    ROS_DEBUG("value: 0x%02X\n", c);
  
    switch(c)
    {
      case KEYCODE_A:
        //ROS_DEBUG("LEFT");
        //angular_ = 1.0;
        robot_command.LeftorRight=-50;
        dirty = true;
        break;
      case KEYCODE_W:
        //ROS_DEBUG("RIGHT");
        //angular_ = -1.0;
        robot_command.UporDown=50;
        dirty = true;
        break;
      case KEYCODE_S:
       // ROS_DEBUG("UP");
        //linear_ = 1.0;
        robot_command.UporDown=-50;
        dirty = true;
        break;
      case KEYCODE_D:
       // ROS_DEBUG("DOWN");
       // linear_ = -1.0;
        robot_command.LeftorRight=50;
        dirty = true;
        break;
      case KEYCODE_J:
        //ROS_DEBUG("w");
        //angular_ = 1.0;
	robot_command.ClimborDive= -50;        
	dirty = true;
        break;
      case KEYCODE_K:
        //ROS_DEBUG("RIGHT");
        //angular_ = -1.0;
	robot_command.ForwardorBackward= -50;        
	dirty = true;
        break;
      case KEYCODE_L:
        //ROS_DEBUG("UP");
        //linear_ = 1.0;
	robot_command.ClimborDive= 50;        
	dirty = true;
        break;
      case KEYCODE_I:
        //ROS_DEBUG("DOWN");
        //linear_ = -1.0;
	robot_command.ForwardorBackward= 50;        
	dirty = true;
        break;
    }
   

    

    if(dirty ==true)
    { 
      printf("keyvalue==%d,%c\r\n",(int)c,c);
      
      usleep(0);
      dirty=false;
    }
    discovery_pub_.publish(robot_command);
       
  }


  return;
}



