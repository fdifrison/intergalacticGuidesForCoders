# Shell User Guide

### Piping

> `|`

    with the symbol '|' we can create a pipeline of commands
    es. cat <filename> | sort > <newfile>

### know your OS

> lsb_release -a

### moving in folders

> `cd`

    cd..          -> go to parent directory
    cd../../..    -> go to parent parent parent directory
    cd~           -> go to user directory /home/user
    cd/           -> go to root
    cd~/folder    -> go directly to folder wherever you are

### help funtion

> `man`

    man cd  -> return instruction about "cd" command

### print current location path

> `pwd`

### making folders

> `mkdir`

    mkdir fold1 fold2             -> create 2 folders at the same time
    mkdir fold1/fold2             -> create 2 nested folder
    mkdir - p fold1/fold2/fold3   -> create the parent dir that is missing

### delete folders

> `rm`

    rmdir <foldername>  -> n.b works only if the dir is empty
    rm -r <foldername>  -> remove everything in the folder recursively
    rm -ri <foldername> -> interactively decide what to delete

### create file

> `touch`

    n.b. the extension of filename doesn't impose the filetype

> `echo`

    echo "sometext" -> print "sometext" to terminal
    echo "sometext" > <filename> -> create a <filename> with "sometext" inside
    echo "sometext" >> <filename> -> append "sometext" to <filename>

### delete file

> `rm`

    n.b. deleted file with rm doesn't go in the trash!!
    rm -v <filename> -> add verbose to the output

### open file

> `xdg-open`

    xdg-open . -> open current folder

### move or rename file/folder

> `mv`

    mv <filename> <newname>       -> rename <filename> in <newname>
    mv <filename> <path>          -> move <filename> to <path>

### copy a file/folder

> `cp`

    cp <filename> <path>
    cp -r <folder> <path> -> copy a folder and its content

### redirect output to file

> `'>'` or  `>>'`  

    pwd > <filename>  -> write print-work-directory fo <filename>, overwrite/create <filename>
    pwd >> <filename>  -> append print-work-directory fo <filename>

### read/inspect a file from terminal

> `cat`

    cat <filename>
    cat <filename1> <filename2> -> concatenate two or more files
    cat <filename1> <filename2> > <tofile> -> concatenate and redirect output
    cat -n <filename> -> print file with line numbers

> `less`

    open file in a new window 
    q       -> exit
    /<name> -> search for <name> in file

> `wc`

    word count -> shows lines words bytes of a file
    can be used as an option to other command (it is called piping)
    ls -l | wc -> word count on the files in folder

> `sort`

    sort <filename> -> sort <filename> content alphabetically (without modifying the file)
    sort -n     -> sort numerically
    sort -nr    -> sort numerically backwards 

# Installation and extraction

> .deb file

    sudo dpkg -i <filename>.deb

> .tar archive

    tar -xf <archivename>
    tar -xvf <archivename>            -> increase verbosity
    tar -xf <archivename> -c <path>   -> specify extraction path

> .sh

    sudo bash <filename>
