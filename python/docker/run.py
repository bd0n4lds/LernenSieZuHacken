# This is the equivalent of typing docker run alpine echo hello world at the command prompt:

import docker

client = docker.from_env()
print
client.containers.run("alpine", ["echo", "hello", "world"])
