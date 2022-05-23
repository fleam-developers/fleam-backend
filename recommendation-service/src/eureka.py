from py_eureka_client.eureka_client import EurekaClient
from src.cli import PORT

def initialize_eureka_client() -> EurekaClient:
    client = EurekaClient(eureka_server="http://discovery-server:8761/", 
                          app_name="recommendation-service", 
                          instance_port=PORT)
    client.start()
    return client

eureka_client = initialize_eureka_client()