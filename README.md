# FileSystem
This repository contains a straightforward implementation of a file system program that performs various commands. The program is designed using common design patterns to enhance code organization and maintainability. It supports fundamental file operations and incorporates well-known design patterns to showcase best practices in software architecture.
## Key Features:

* __Command Execution__: Execute basic file system commands.
* __Design Patterns__: Utilizes design patterns for clear and scalable code structure.
* __Ease of Use__: Usage in console.
## Usage:

Clone the repository to your local machine.
Try out implemented commands.
## Supported Commands:

* `cat <FileName>` : Create a new file or open content of existing file.
* `mkdir <CatalogName>`: Create a new catalog in current catalog.
* `cpy <sourceFile> <destinationPath>`: Copy an existing file to directory path.
* `mv <sourceFile> <destinationPath>`: Moves an existing file to directory path.
* `cd <directoryPath>`: Change current node to directoryPath
* `ls`: List the contents of the current directory.
* `more`: Shows content of file
* `tree`: Display current tree structure.


## Design Patterns Implemented:

* __Command Pattern__: Encapsulates each command as an object, allowing for parameterization and queuing of requests.
* __Composite Pattern__: Composes objects into tree structures to represent part-whole hierarchies, treating individual objects and compositions uniformly.
* __Interpreter Pattern__: Defines a grammar for interpreting commands and provides an interpreter to interpret the commands.
