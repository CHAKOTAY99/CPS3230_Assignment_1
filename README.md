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

What I have noticed is that I should have mocked the database so that all of the tests cases do not
effect the database.

04/11/2019: Finally fixed the clean database at the start of each test problem. We could probably now remove all the teardown
methods which exist in each individual test. Whilst this is great - in part 3 I will be changing everything
to mocks as this is very poor testing.

The persist() method cannot be directly tested as it is run every time a currency is written to disk.
Its performance is directly correlated with the addCurrency() and deleteCurrent(), as neither of their tests
would have been successful if not for the effectiveness of persist().

Added a helper class to be able to test the init() method. It was not possible to not apply the @Before and @After to these tests
so it was best to place them into their own helper class.

Now it is important to remember that from the init() perspective - the checking of the lines are done when reading the txt file.
Validation such as not entering a currency with more or less than three characters and other such systems do not happen at the init() level.
init() simply makes sure that at the start of the program, the provided currencies are entered correctly.
I noticed also that when entering a new currency in the fields, it is allowing ',' to be entered. When restarting the program these get
flagged, which is very bad.

On another note, in the CurrencyDatabase - adding a new Currency here does not require any validation. That happens in a separate class.

04/11/2019 Basic Unit Testing on CurrencyDatabase has been completed
Currency has been tested
ExchangeRate has been tested
Util has been tested
DefaultCurrencyServer has been tested


05/11/2019 Testing the main method is simply not possible. Whilst 100% code coverage would be great,
it is impossible to test what is being told to the system.

Whilst I have progressed in Task 2, I am not happy that all my tests are interacting with the currencies.txt file. Tests
should not be interacting with the actual database. Before moving to Task 3, I will investigate and attempt to fix this issue by mocking or
by a spy. I personally prefer mocks for simplicity.

Whilst I have managed to successfully mock CurrencyDatabase - I have noticed from the various sources which
I was taking information from that making use of mocks for the sake of using them is not recommended. Thus below
I will be listing what a system under test is and when mocks, spies and studs should be used. And if mocks could be used in SUT's.

System Under Test:
A system under test from a Unit Testing perspective represents all of the classes in a test which are not mocks or stubs.
http://xunitpatterns.com/SUT.html
It is always defined from the perspective of the test. It is not clear if depended-on components can be added under the SUT

In fact according to this source https://thoughtbot.com/blog/don-t-stub-the-system-under-test the Depended-On component should be
tested with a test double or the like. It is tempting to stub parts of a SUT (Which I want to do) and should be avioded.

Stubs vs Mocks
A stub defines specific behavior for an object's method. A mock acts as a stand-in object for another.

You should use a stub if you a unit test which already does the piece of functionality.

Mocking or stubbing is recommended when the code uses external dependencies like system calls or accessing a database.
https://circleci.com/blog/how-to-test-software-part-i-mocking-stubbing-and-contract-testing/
So this source agreed with my attempt to not write to the database.

In fact talking to the database is a bad unit test

As of 15/11/2019 I have decided to allow Task 2 tests to remain and not add mocks now. Its because the tests written under Task 2 are to be simple and easy to understand and test.
They do break good principles of testing as they interact with the database which I do not agree with. It has been decided to allow these glaring issues so that the code
can be refactored in Part 3 of this assignment.

CurrencyDatabaseHelperTest: These tests have been separated from the others because they have different requirements in order to run.
These tests are all for the init() method which tests the initial file at the start of the program. It retrieves the names located in the currencies.txt file
to start the initial tests.

Sticking with the naming convention taken with the tests, each name provides a clear goal to what the test is to achieve. 
