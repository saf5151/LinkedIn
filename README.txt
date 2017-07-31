Team: Swan Ronson
Date: 7/30/17

Our database has been pre-built, and pre-populated with data, so the SQL queries to create the database are not found
in the source code.  Instead, for any specific questions on the database schema, please reference the file
"db_schema.txt", located at the top directory of the project.

Project Build Instructions:
1. Unzip the project to your Desktop ( or wherever you want to store it )
2. In the command prompt, navigate to that location, ex: "cd Desktop\LinkedIn"
3. Run the command "javac src\ApplicationMain.java src\Employee.java src\Employer.java src\User.java src\Test.java"
   to compile the project.
4. Run the command to run the project, specifying the path to the the H2 jar file, and the database file:
   ex: "java -classpath "C:\Users\Aziz\OneDrive\Documents\workspace\Linkedin\bin;C:\Program Files (x86)\H2\bin\h2-1.4.196.jar" ApplicationMain jdbc:h2:~/LinkedIn/db/linkedin"
   The command-line arg is the address of the database file, which may be slightly different for you.
   The jar file has been pre-packaged in the db directory of the project, so you can use that address.
5. Go to DataData in the the DataGenerator folder and change the PATH variable to the directory of the source code. (Only if you are going to run the DataGenerator)