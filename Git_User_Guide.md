# Git User Guide

    * install
        > sudo apt install git

    * config global user
        > git config --global user.name “Giovanni Frison”
        > git config --global user.email “ing.giovanni.frison@gmail.com”

    * retrieve global user info
        > git config –l

    * expose hidden files on linux
        > ctrl + h → to delete .git folder

    * chenge editor for committing
        > git config --global core.editor emacs (emacs is the name of the editor)

## SSH KEY

    info @ https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent

    * generate ssh key
        > ssh-keygen -t rsa -b 4096 -C "ing.giovanni.frison@gmail.com"
    
    * start agent
        > eval "$(ssh-agent -s)"

    * copy ssh key to agent
        > ssh-add ~/.ssh/pyfry_key  (dir_of_the_key/name_of_the_key)

# INITIALIZE FROM LOCAL

    * connect to github with ssh key and first commit from desktop
        > git init
        > git add README.md
        > git commit -m "first commit"
        > git branch -M main
        > git remote add origin git@github.com:fdifrison/PyFry-v1.git
        > git push -u origin main

    * push an existing repository
        > git remote add origin git@github.com:fdifrison/PyFry-v1.git
        > git branch -M main
        > git push -u origin main

# CLONE A REPO

    git clone https://github.com/libgit2/libgit2 foldername

# GTI IGNORE

    * ignore all .a files
        >.a
    * but do track lib.a, even though you're ignoring .a files above
        > !lib.a
    * only ignore the TODO file in the current directory, not subdir/TODO
        > /TODO
    * ignore all files in any directory named build
        > build/
    * ignore doc/notes.txt, but not doc/server/arch.txt
        > doc/*.txt
    * ignore all .pdf files in the doc/ directory and any of its subdirectories
        > doc/**/*.pdf