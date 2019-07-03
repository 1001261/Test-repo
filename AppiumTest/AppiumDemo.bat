D:
cd "D:\Kondal Thummeti\Eclipse Workspace\AppiumTest"
set ProjectPath=D:\Kondal Thummeti\Eclipse Workspace\AppiumTest
echo %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\lib\*
echo %classpath%
java org.testng.TestNG "%ProjectPath%\AppiumDemo.xml"