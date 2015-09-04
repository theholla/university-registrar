# To Do List - with database

##### A university registrar. September 1, 2015.

#### By **Nataliya Bareeva-Miller and  Diana Holland**

## Description

Administrators can add students to courses, and courses to students.

## Setup

* Please have all Java developer tools ready, including the JDK.
* You'll need to be able to run and compile Java. This app uses Gradle for that purpose.
* This app uses Apache Spark and Velocity to handle its user interface.

**To use the database included with this file, follow these instructions:**

* Clone this git repository on your computer ```git clone https://github.com/theholla/university-registrar.git```
* In terminal, start the PostgreSQL server by typing ```postgres``` and ```psql```
* In psql, create a new database by typing ```CREATE DATABASE university_registrar;```
* In another tab in terminal, connect to this project folder: ```cd path/to/this/folder```
* Dump the database information from my sql file into your local database by typing the following code: ```university_registar < university_registrar.sql```

* To run tests, you can create a new test database in psql: ```CREATE DATABASE university_registrar_test WITH TEMPLATE university_registrar;```


## Database Schema

<img src="/src/main/resources/public/img/sql_schema.png">

## Technologies Used

This app is written in Java with a little help from Bootstrap.

### Legal

Copyright (c) 2015 **Nataliya Bareeva-Miller and  Diana Holland**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
