# DataTranslator

This is a data translator application which translates the content of a text file based on the confiuration provided in 
two other text files for column and rows respectively and writes the translated data into a new file. 

There is a **Data.txt** file which holds the initial data. **rowConfigFile** and **columnConfigFile** files holds the 
configurations for row and columns respectively. Once the translation process finishes a new file named **processedFile.txt**
gets generated with the updated data.

For test purpose, a **test.txt** (test data), **testColumnConfig.txt** (test column configuration) and **testIdConfig.txt**
(test row configuration) have been added in the package. 