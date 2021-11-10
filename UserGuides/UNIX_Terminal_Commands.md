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



