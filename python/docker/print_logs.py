# Prints the logs of a container for given its ID

import docker

client = docker.from_env()
container = client.containers.get('f1064a8a4c82')
print
container.logs()
