# Docker Manual

For the official manual -> https://docs.docker.com/

---

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

`sudo docker container run --publish [host_port]:[container_port] --name [container_name] -detach [image]:[version]`

In this line there is a lot to process; first of all we are calling the docker command `conatiner` with the sub-command `run`, then we are specifying `--publish` meaning that we want to open a communication port between the client and the host of the container on the same port `80:80`. after that we specify the name of the host with `--name` and this has to be an unique attribute of the container, if not specified docker will come up with one random name on its own; with the keyword `-detach` we are telling docker to run the container silently and not on the terminal. At last we specify the image we want to load into the container, `nginx` in this case, and the specific version we want.

Under the hood, docker is looking for the nginx image in the local cache and if not found it will look in the Docker Hub (standard image repository), it will download the version specified, create a container based on the chose image, giving it a virtual IP on a private network inside docker engine, open up the communication port on the host and on the container, and finally starts the container.

## Docker command line

Following a list of the most common docker command that can be used from the terminal:

### container side

* `docker container ls` - list the running containers; all the available with option `-a`
* `docker container top [container_name]` - list the active process in a container
* `docker container port [container_name]` - check if a port has been opened between host and container
* `docker container inspect [container_name]` - return container's data in JSON format
  * `docker container inspect --format '{{ .NetworkSettings.IPAddress}}' [container_name]` - select the container IP field from the JSON and return it [https://docs.docker.com/config/formatting/]
* `docker container stats` - shows a summary of the resources used by the active containers

### network side

* `docker network ls` - list the existing networks
* `docker network inspect [network_name]` - return network's data in JSON format
* `docker network create [network_name]` - create a new network with default *bridge* driver
* `docker network connect [network_name] [container_name]` - connect a container to a network
## How to interact with a container

Now we have seen how to create a container, but how do we access it? We might think that an SSH is required in order to connect to the inside of a container, instead, thanks to the docker cli there is no need since we can easily get a shell inside the container. To create a new container interactively (eg. opening bash shell):

`docker container run -it --name [container_name] [image] bash`

We can see two additional option that are `-it` where:

* `i` stands for interactive
* `t` is the abbreviation for `tty` which simulates a real terminal, similar to what ssh does
  
After specifying the image we are going to containerize, we can add optional arguments such as `bash` in this case, which is a common shell that we can usually find in a container.
                      
Running this command will open a prompt with root privileges. Type `exit` to exit the prompt.
After exiting, we can restart the interactive container with:

`docker container star -ai [container_name]`

### interact with an existing container

We have seen how to create a container and star it interactively, but what if the container was already there? In this case we need the `exec` command:

`docker container exec -it [container_name] bash`

The exec command runs an additional process on the already running container, therefore, exiting the interactive process won't stop the container.

N.B. if we are trying to lunch interactively something that is not installed inside the container (e.g. bash is not installed in Alpine, a super lightweight distribution of linux) we will get the following error : *executable file not found in $PATH*

## Docker Networks

We have seen that when creating a container we can specify the option `-p` (for publish) in order to specify the host and the container listening ports. This essentially means that we are open port on our ethernet network, which has its own firewall, but what it might seem surprising is that our communication is not traveling directly to the container but it pass trough a `private virtual network` called `bridge` or `docker 0` (this is why the container won't share the host IP). As a matter of fact, all containers are attache to a private virtual network and between them they can freely communicate without the need to 'publish' a specific communication port. Instead, to talk to the host the `-p` need to be specified and only one container can be connected to that port.

The best practice is to create a new virtual network for each application (which can count more then one container), because in this way the different containers can talk freely between each other, while, for example, only one is able to talk directly to the host (reducing security issues and traffic). 

For the same reason, is better to have containers, that may interact together, on the same private virtual network because otherwise, if a communication is required, information must pass first to the host and then redirected.

N.B. Docker is known to be a **Batteries included, but removable" framework, meaning che most of the default options works fine almost every time but can also be tweaked at user's will. For example, what we have just described about networks, bridges and containers is the default docker behavior, however we can attach containers to more than one bridge, or skip entirely the bridge and connect directly to the user IP etc..

### Networks management

Similarly as we create containers, we can create and inspect networks as well as connect and disconnect networks from containers (similarly to how we plug and unplug hardware in our machine).

If we want to list the available networks we can `docker network ls` and by default we will see 3 networks:
* `bridge\docker0` network, is the default vn that bridge to the NAT's firewall to the physical network the host is connected to
* `host` network, is the way we have to skip the vn of docker to have a direct connection to host, gaining performance but sacrificing security
* `none` network, the equivalent of an interface of our machine that has nothing attached

With `ls` we can also see a column name `driver` which is an extension (built-in or third parties) that gives features tho the network; when we create a new network with `docker network create [network_name]` the default driver `bridge` is used which create a vn locally with its own subnet.

When creating a container we can directly specify the `--network` option to connect to a specific netowrk or, vice-versa, we can `docker network connect [network_name] [container_name]` to connect an existing container to a network.

### Connection with DNS

*how DNS works [https://howdns.works/ep1/]*

The structure of dockers container is very mutable, containers get created/deleted, shrinked/expanded, connected/disconnected etc.. therefore, relying on their IP address for communication is an anti-pattern in the logic of the framework. Instead, the built-in solution to this problem is to use `DNS naming`; as a matter of fact, the name we give to a container can be used by networks as DNS; in the same way if we create a second container on an existing network, the two container will be able to find each other using their name an not their IP.

