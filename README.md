----
# Ledger Co

### Project Requirements

* JDK 1.8
* Maven (3.8.1)
* For Unit Tests:  
  * Junit 4
  * Mockito

### Compiling/Building and running the unit tests
Go to the project root folder and then run: ./bin/setup.sh

### Runing the project
NOTE: Before running, please make sure you do the above setup step. Otherwise it will not run. 
The project can be run as follows:

1) **Using file based input:**: It accepts a filename as a parameter at the command prompt and read the commands from that file.   
  ./bin/ledger.sh  <input_filepath>  
 Example: ./bin/ledger.sh  ./file_input.txt

