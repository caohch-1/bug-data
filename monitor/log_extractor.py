import docker

class LogExtractor:
    def __init__(self) -> None:
        self.client = docker.from_env()
        self.container_names = [c.name for c in self.client.containers.list() if "ts" in c.name]
    
    def extract(self, container_name:str, output_dir:str="./logs"):
        with open(f"{output_dir}/{container_name}.txt", "w") as f:
            dkg = self.client.containers.get(container_name).logs(stream=True, follow=False)
            lines = []
            try:
                while True:
                    lines.append(next(dkg).decode("utf-8"))    
            except StopIteration:
                print(f"Logs extracted for {container_name}")
            f.writelines(lines)

    def extract_all(self, output_dir:str="./logs"):
        for container_name in self.container_names:
            self.extract(container_name, output_dir)