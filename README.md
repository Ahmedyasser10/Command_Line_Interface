# Terminal Application Readme

This readme provides an overview and documentation for the terminal application implemented in Java. The application simulates basic terminal commands and file operations.

## Table of Contents

1. [Overview](#overview)
2. [Usage](#usage)
3. [Command List](#command-list)
4. [File Structure](#file-structure)
5. [Implementation Details](#implementation-details)
6. [Contributing](#contributing)
7. [License](#license)

## Overview

The terminal application allows users to interact with a simulated file system through a command-line interface. It supports various commands for file manipulation and navigation.

## Usage

To use the terminal application, follow these steps:

1. Clone the repository.
2. Compile the Java files: `javac Main.java Terminal.java Parser.java`
3. Run the application: `java Main`
4. Enter commands when prompted.

## Command List

- `pwd`: Print the current working directory.
- `ls`: List files and directories in the current directory.
- `ls-r`: List files and directories in reverse order.
- `mkdir [directoryName]`: Create a new directory.
- `rmdir [directoryName]`: Remove an empty directory.
- `rm [fileName/directoryName]`: Remove a file or directory recursively.
- `cp [source] [destination]`: Copy a file to a specified destination.
- `cp-r [source] [destination]`: Copy a directory and its contents to a specified destination.
- `touch [fileName]`: Create a new empty file.
- `echo [text]`: Display the provided text.
- `cat [file1] [file2]`: Display the content of one or two files.
- `cd [directory]`: Change the current working directory.
- `wc [fileName]`: Display the line, word, and character count of a file.
- `exit`: Exit the terminal application.

## File Structure

- `Main.java`: The main class that handles user input and command execution.
- `Terminal.java`: Contains methods for various file operations (e.g., `mkdir`, `ls`, `cp`).
- `Parser.java`: Parses user commands and extracts command and arguments.

## Implementation Details

The application uses Java's `java.nio.file` package for file manipulation, including creating directories, copying files, and removing files and directories recursively. The `Scanner` class is used to read user input and process commands.
