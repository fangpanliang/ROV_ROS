#sql("findRoll")
SELECT roll as datas,times FROM  rov
#end

#sql("findYaw")
SELECT yaw as datas,times FROM  rov
#end

#sql("findPitch")
SELECT pitch as datas,times FROM rov
#end

#sql("findDepth")
SELECT depth as datas,times FROM  rov
#end

#sql("findSpeed")
SELECT speed as datas,times FROM  rov
#end

#sql("findStatus")
SELECT roll,yaw,pitch FROM  rov order by id DESC limit 1
#end
