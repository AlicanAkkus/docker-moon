
<p align="center">
  <img title="docker-moon" src='https://alicanakkus.github.io/images/alicanakkus-site-logo.png' />
</p>

**_Docker Moon_** is a lightweight management UI which allows you to **easily** manage your Docker environments (Docker hosts or Swarm clusters).

**_Docker Moon_** is **simple** to deploy as it is to use. It consists of a single container that can run on any Docker engine. Also you can run with **java -jar** as son as possible.

**_Docker Moon_** allows you to manage your Docker containers, images, volumes, networks, secrets, configs and more! It is compatible with the *standalone Docker* engine and with *Docker Swarm mode*.

## Demo

Coming soon..

<img src="https://media.giphy.com/media/3oxHQqkTf88cllMk6c/giphy.gif" width="77%"/>


## Getting started

### Deploy to Docker;
```
docker run -d -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock aakkus/docker-moon
```

### Deploy to Swarm as a Service;

```
docker service create \
    --name docker-moon \
    --publish 8080:8080 \
    --constraint 'node.role == manager' \
    --mount type=bind,src=//var/run/docker.sock,dst=/var/run/docker.sock \
    aakkus/docker-moon 
```

## Next Step?

* Connect to different Docker engine.
* Use multiple docker engine at same time.
* Connect to Docker engine with TLS. 
* User Management.
* UI & UX improvements.
* Add new feature to container management such as export container & stats etc.
* Refactor are needed for some class.
* Write Unit & Integration & Functional Tests.

## Screenshot



## Reporting bugs and contributing

* Want to report a bug or request a feature? Please send email to **alican.akkus94@gmail.com**
