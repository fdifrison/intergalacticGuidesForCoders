# Docker Manual

For the official manual -> https://docs.docker.com/

Released in 2013, Docker represents a huge shift in the IT ecosystem introducing `containerization`. Basically a containers reduce the complexity software development lifecycle serving in a single platform the tools required for deploying, testing even when the application is developed in different languages, frameworks, or on different clouds and so on. The maintenance of software is another crucial factor that has led docker to be a must. There is no need to rewrite code, it is just a different way of running and packaging an application and moving around an infrastructure.

https://landscape.cncf.io/ is a fun link to appreciate the magnitude of the container based application that are out there.

## Docker Editions

Docker has two main edition: `CE` the free community edition and `EE` the paid enterprise edition. Since it is a technology that moves super fast, it is important to have always the latest version installed. Docker is native on Linux, therefore the direct installation can happen there. The version for Windows and Mac are run Docker on top of a VM since it is not directly supported by the OS. Another possibility is given by the cloud installation, on AWS for example, where Docker is preinstalled (usually on Linux) and has a number of special features ad hoc for cloud usage.

### Install on Ubuntu

N.B. Remember to enable virtualization on BIOS (right now Docker Desktop runs on a VM also on Linux)

To install on ubuntu, "simply" follows the instruction @ https://docs.docker.com/engine/install/ubuntu/#set-up-the-repository.

Once Docker is installed we can do some quick checks to very that it works properly (sudo is required):

* `docker` : to have a list of the possible commands (at the bottom)
  * the structure of commands is the following: `docker <command> <sub-command>`
* `docker version` : will show the Client and the Server (Engine) version installed, meaning they are communicating correctly
* `docker info`: returns a lot of information about the status of the containers in the machine and the engine configurations

## Docker Hub

https://hub.docker.com/ is the repository of official container image.

## Intro to Containers

First of all, what is the difference between an `image` and a `container`? An image is the binary, the libraries and the source code of an application while the *container is running instance of that image*.
therefore, we can have many containers running the same image.



Let's see what does it mean to create a mock-up container and what are the steps that docker performs in order to make it viable.

`sudo docker container run --publish 80:80 --name aGivenName -detach nginx:version`

In this line there is a lot to process; first of all we are calling the docker command `conatiner` with the sub-command `run`, then we are specifying `--publish` meaning that we want to open a communication port between the client and the host of the container on the same port `80:80`. after that we specify the name of the host with `--name` and this has to be an unique attribute of the container, if not specified docker will come up with one random name on its own; with the keyword `-detach` we are telling docker to run the container silently and not on the terminal. At last we specify the image we want to load into the container, `nginx` in this case, and the specific version we want.

Under the hood, docker is looking for the nginx image in the local cache and if not found it will look in the Docker Hub (standard image repository), it will download the version specified, create a container based on the chose image, giving it a virtual IP on a private network inside docker engine, open up the communication port on the host and on the container, and finally starts the container.