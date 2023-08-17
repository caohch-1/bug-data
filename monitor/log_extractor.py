import docker
client = docker.from_env()
# print([c.name for c in client.containers.list])