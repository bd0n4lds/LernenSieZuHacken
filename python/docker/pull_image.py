# docker pull

import docker

client = docker.from_env()
image = client.images.pull("alpine")
print
image.id
