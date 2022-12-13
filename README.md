# ToDoProject
<H2>Classes Purpose</H2><br>

  1. Class 1: Constants -> this class is used to store all the constants used in the project e.g file name, ADD_TASK switch value,etc
  2. Class 2: View -> this class is the starting point of the project and all the features of the project are run through this class
  3. Class 3: MessageReader -> this class is used to read all the messages stored in message.properties file and it has one method getValue(key) -> it returns the value of the key passed.
  4. Class 4: ToDoRepo ->This class is very important and used to perform crud operations. It is responsible to do serialization and de-serialization. this class store the data given by todoDTO to file (serialization) by addTask() method and retreive all the data from file (De-serialization) using printAllTask() (De-serialization)
  It also impements the Interface ITodoRepo as it gives only specific methods
  5. Interface ITodoRepo -> This is declared to hide details from the co-programmer... It is just to set standards and it only has two methods addTask() and printTask() , which are must.
  
  6. Class 5: ToDoDTO -> this class is used to store the detials of the task and then it is transfered to ToDoRepo for crud operations.
  
  7. Class 6: CustomId and GetCustomId -> these class are used to generate custom task id.
  
  


<H2>Learning Outcome</H2>
1. Java core
2. Java oops 
3. Java Serialization/De-Serialization
4. Java Exception Handling
5. Singleton Class design pattern
6. Dry principle
7. Inheritance,interface
8. static
9. SOLID principle
10. File Handling

<H2>Project Details</H2>
<b>This is a gui based project in which only 6 things can be performed:-</b><br>
  1. ADD Task<br>
  2. Delete Task<br>
  3. Search Task<br>
  4. Update Task<br>
  5. Print Task<br>
  6. Update Task Status<br>
<b>All the date is stored locally using ObjectOutputStream</b>

<b><u>Each task has 4 properties</u></b> 
  1. ID -> Custom generation
  2. Name -> User input
  3. Description -> User input
  4. Date -> System Generated
  5. Status -> Default Pending
  
<b>Some of the screenshots -></b><br>
It is a menu driven program 
1. Menu
![image](https://user-images.githubusercontent.com/82015676/207358667-3a185fc7-2e01-4055-a97e-7b958004377f.png)
2. Task Display
![image](https://user-images.githubusercontent.com/82015676/207358860-00152edc-1c27-4d06-8df8-12e708552d53.png)
3. Package
![image](https://user-images.githubusercontent.com/82015676/207358957-2a2a93f2-93c8-4d7e-9ea7-6e8e4e2ee07e.png)


