# UNIX Terminal Commands

    * moving in folders:
        > cd
            ..          -> go to parent directory
            ../../..    -> go to parent parent parent directory
            ~           -> go to user directory /home/user
            /           -> go to root
            ~/folder    -> go directly to folder wherever you are


    * help funtion
        > man <function>
            es. man cd  -> return instruction about "cd" command


    * print current location path
        > pwd


    * making folders
        > mkdir <foldername>
            fold1 fold2             -> create 2 folders at the same time
            fold1/fold2             -> create 2 nested folder
            - p fold1/fold2/fold3   -> create the parent dir that is missing
    * delete folders
        > rmdir <foldername>
            n.b works only if the dir is empty
        > rm -r <foldername> -> remove everything in the folder recursively
        > rm -ri <foldername> -> interactively decide what to delete


    * create file
        > touch <filname>
            n.b. the extension of filename doesn't impose the filetype
    * delete file
        > rm <filname>
            n.b. deleted file with rm doesn't go in the trash!!
            -v <filename> -> add verbose to the output
    * open file
        > xdg-open <filename>
        > xdg-open . -> open current folder
    * move or rename file/folder
        > mv <filename> <newname>       -> rename <filename> in <newname>
        > mv <filename> <path>          -> move <filename> to <path>
    *copy a file/folder
        > cp <filename> <path>
        > cp -r <folder> <path> -> copy a folder and its content

    * redirect output to file
        pwd > <filename>  -> write print-work-directory fo <filename>, overwrite/create <filename> 
        pwd >> <filename>  -> append print-work-directory fo <filename>


    

## Installation and extraction

    * install a .deb file
        > sudo dpkg -i <filename>.deb

    * extract .tar archive
        > tar -xf <archivename>
        > tar -xvf <archivename>            -> increase verbosity
        > tar -xf <archivename> -c <path>   -> specify extraction path 

    * install .sh
        > sudo bash <filename> 



