# DataTranslator

This is a data translator application which translates the content of a text file based on the configuration provided in 
two other text files for **column** and **rows** respectively and writes the translated data into a new file. 

In the package, there is a **Data.txt** file which holds the initial data. **rowConfigFile** and **columnConfigFile** 
files holds the configurations for row and columns respectively. Once the translation process finishes a new file named 
**processedFile.txt** gets generated with the updated data.

Running the **main** method in the **Main** class will start the application and run the translation process. User can 
update the **rowConfigFile** to change the row id. Only the row id mentioned in the file will be available in the processed file. 
Similarly, user can update the **columnConfigFile** to update the column title and the columns in the result file.



##Test
For test purpose, a **test.txt** (test data), **testColumnConfig.txt** (test column configuration) and **testIdConfig.txt**
(test row configuration) have been added in the package. 

To run the tests, please open the **test package** and run the methods in the test classes.