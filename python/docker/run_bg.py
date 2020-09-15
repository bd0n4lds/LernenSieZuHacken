# You can also run containers in the background,
# the equivalent of typing docker run -d bfirsh/reticulate-spines:

import docker

client = docker.from_env()
container = client.containers.run("bfirsh/reticulate-splines", detach=True)
print
container.id
