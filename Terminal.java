import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.*;
import java.sql.SQLOutput;
import java.util.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Terminal {
    public void mkdir(String directoryName) {
        if (directoryName.length() == 0) {
            System.out.println("mkdir takes a directory name as an argument");
        } else {
            File newDirectory = new File(directoryName);
            if (!newDirectory.exists()) {
                newDirectory.mkdir();
                System.out.println(directoryName + " Directory has been created successfully! ");
            } else {
                System.out.println("Directory is already exist");
            }
        }
    }

    public void pwd() {
        System.out.println(Main.currentDirectory);
    }

    public void rmdir(String directoryName) {
        File targetDirectory = new File(directoryName);
        if (directoryName.length() == 0) {
            System.out.println("rmdir takes a directory name as an argument");
        } else {
            if (!targetDirectory.exists()) {
                System.out.println("the directory is not exist");
            } else {
                if (targetDirectory.delete()) {
                    System.out.println(targetDirectory + " Directory was deleted");
                } else {
                    System.out.println("this directory is not empty! you can use rm command");
                }
            }
        }
    }

    public void rm(String path) throws IOException {
        Path directory = Paths.get(path);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void ls() throws IOException {
        File f = new File(Main.currentDirectory);
        System.out.println(Main.currentDirectory);
        if (!f.exists()) {
            System.out.println("Directory is not exist");
        } else {
            File[] files = f.listFiles();
            ArrayList<String> fileNames = new ArrayList<String>();
            for (File file : files) {
                fileNames.add(file.getName());
            }
            Collections.sort(fileNames);
            for (String name : fileNames) {
                System.out.println(name);
            }
        }
    }

    public void ls_r() throws IOException {
        File f = new File(Main.currentDirectory);
        if (!f.exists()) {
            System.out.println("Directory is not exist");
        } else {
            File[] files = f.listFiles();
            ArrayList<String> fileNames = new ArrayList<String>();
            for (File file : files) {
                fileNames.add(file.getName());
            }
            Collections.reverse(fileNames);
            for (String name : fileNames) {
                System.out.println(name);
            }
        }


    }

    public void cp(String[] args) throws IOException {
        Path file1 = Path.of(args[1]);
        Path file2 = Path.of(args[2]);
        if (!file1.isAbsolute()) {
            file1 = Paths.get(Main.currentDirectory, args[1]);
        }
        if (!file2.isAbsolute()) {
            file2 = Paths.get(Main.currentDirectory, args[2]);
        }


        try {
            if (Files.exists(file1)) {
                File file = new File(file2.toString());
                file.createNewFile();
                if (Files.exists(file2)) {
                    Files.copy(file1, file2, REPLACE_EXISTING);
                } else {
                    System.out.println("Enter a Valid Destination File Name");
                }

            } else {
                System.out.println("Enter a Valid Source File Name");
            }
        } catch (IOException e) {
            System.out.println("Error in Copying File " + e);
        }
    }

    public void cp_r(String[] args) {
        Path folder1 = Path.of(args[1]);
        ;
        Path folder2 = Path.of(args[2]);
        if (!folder1.isAbsolute()) {
            folder1 = Paths.get(Main.currentDirectory, args[1]);
        } else {
            folder1 = Path.of(args[1]);
        }
        if (!folder2.isAbsolute()) {
            folder2 = Paths.get(Main.currentDirectory, args[2]);
        }

        try {
            Path finalFolder = folder2;
            Path finalFolder1 = folder1;
            Files.walk(folder1).forEach(s ->
                    {
                        try {
                            Files.copy(s, finalFolder.resolve(finalFolder1.relativize(s)), StandardCopyOption.REPLACE_EXISTING);

                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }

            );
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void cat(String fileName1, String fileName2) {
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(fileName1));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("cannot reed from this file");
        }
        if (fileName2.length() != 0) {
            try {
                br = new BufferedReader(new FileReader(fileName2));
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("cannot reed from this file");
            }
        }

    }

    public void wc(String fileName){
        BufferedReader br;
        String line;
        int cntw = 0, cntchar = 0, cntline = 0;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                cntline++;
                String words[] = line.split("\\s+");
                cntw += words.length;
                for(String s: words){
                    cntchar += s.length();
                }
            }
            System.out.println(cntline  + " " + cntw + " " + cntchar + " " + fileName);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("cannot reed from this file");
        }
    }

    public void cd(String args, String address) {
        if (args.equalsIgnoreCase("")) {
            Main.currentDirectory = Main.homeDirectory;
        } else if (args.equalsIgnoreCase("..")) {
            if (Path.of(Main.currentDirectory).getParent() != null) {
                Main.currentDirectory = String.valueOf(Path.of(Main.currentDirectory).getParent());

            }

        } else {
            if (!Path.of(args).isAbsolute()) {
                args = String.valueOf(Paths.get(Main.currentDirectory, args));
            }
            if (Files.exists(Path.of(args))) {
                Main.currentDirectory = args;
            } else {
                System.out.println("Directory not found");
            }
        }

    }

    public void echo(String[] input) {
        for (int i = 1; i < input.length; ++i) {
            System.out.println(input[i]);
        }
    }



    public void touch(String fileName) throws IOException {
        if (!Path.of(fileName).isAbsolute()) {
            fileName = String.valueOf(Paths.get(Main.currentDirectory, fileName));
        }
        File f = new File(Path.of(Main.currentDirectory).relativize(Path.of(fileName)).toUri());
        f.createNewFile();

    }
}
