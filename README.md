# CPS3230 Assignment 1
Chakotay Incorvaia's version of the first assignment of the unit CPS3230 - Fundamentals of Software Testing

This readme file will contain the thought process which is used in order to tackle the tasks.
The contents of this README file will be added to the documentation.


For workflow purposes each task has been broken down further into sub-tasks.
With each test class a new branch will be created to properly separate the workflow.
The branch may be deleted once merged.

Testing the CurrencyDatabase:
After multiple attempts I have failed to test properly the init() method in this class.
Thankfully testing the init() method is not required as it mainly deals with the loading of the 
currency file. Depending on the viewpoint of the developer, Unit Tests should not be
responsible for checking if the file is inputted correctly. Testing a file of the filesystem is out
of the control of the system and is instead added by the user.

Current understanding of the file system: 

A .txt file provides the initial list of currencies for the program. This is then pushed into an
array list. Then the contents of the array list are written to a file under the /target/ directory meaning that it
is generated. When this file does indeed exist - the program takes the list of currencies from this file
and not from the one provided at the start of the program when the array list was empty.
Any changes to the currency list is made to the array list. Once a change ot the array list occurs, the persist() method
is called to write and reflect the changes in the currencies.txt file.
The initial data in the database is actually retrieve from the currencies.txt located
in the resource folder. The init() method's task is to write up and add them to the database.

I added a 5 liner to the setup() method so that the database is 100% clean before starting the tests.
I have decided not to re-introduce the default 5 in the teardown - simply because they can be recreated via
a build. 