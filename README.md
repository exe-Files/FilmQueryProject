# Film Query Project

## Overview
This program searches a SQL database of films based on user input, creates a film object based on the query results, and returns the attributes of the object back to the user. The focus of this project is familiarization with SQL in Java and getting used to JDBC, as well as building upon Java concepts and OOP fundamentals.


### Technologies used
* Java
* SQL
* Maven
* JDBC
* Classes
* Objects
* Interfaces
* Exception Handling

### How it works


### Lessons Learned
Working with SQL through a Java program was an interesting assignment, and it really helped to strengthen some areas I wasn't sure about and build upon others that needed more clarification. 
Working with the executed SQL query and figuring out how to properly pull data from it proved to be a challenge. I wasn't entirely sure what would be the best way to constitute certain parts of the resultset. Should this be a String? Should this be a date? Reading in dates properly was an area I was unsure about, so taking the time to review helped. 
There were multiple questions that arose throughout the project as I progressed.
One of my methods ends up calling a constructor with multiple parameters, but another method ends up calling a default constructor and updating each of its attributes individually. 
Another area that I'm still shakey on is the exception handling. I end up throwing the SQLException through multiple parents, even all the way to the interface, without catching the exception. Is it better throw an exception and never catch it or should you handle it as low as you can?  
Another issue I ran into while working on my project is that I ended up running into an error with the SQL database having too many queries to it, causing my program to throw a runtime exception. It made me consider whether it was better to have multiple queries throughout your program or just one big query that pulls from multiple tables all at once.
Based on the scale of the program and the uncharted territory, it was easy to get lost while working on this program. Multiple times, I would have to step back to make sure I wasn't getting ahead of myself by working on areas before others were completed and working properly. Taking time to brek apart the program requirements helped to keep things relatively on course. Although I still have many questions about Java best practices and how I can apply them to my code, I was happy to resolve an issue where my queries would skip the first result as it printed out the results. It turns out that even if the query you used returns an empty set, ResultSet will not equal null when you assign it. So using .equals(null) on the result set would never be true. In order to properly check if a ResultSet is null, you should call the .next() method on it instead. It will then iterate through the set, starting at the first index available, index 0. The problem is, when you call .next() a second time, it will no longer start at the first index available, and instead start where the cursor was left, which would be index 1. In order to get around this, I used a do-while loop to nest the code following the first use of .next() so that resultset wouldn't have to be reevaluated until it ran at least once, and it would be able to properly pull the first line of the SQL return statement
