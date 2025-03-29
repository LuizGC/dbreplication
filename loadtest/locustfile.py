from locust import HttpUser, task, between

class ClientUser(HttpUser):

    @task
    def get_client_sum(self):
        # This sends a GET request to /client/sum with the accept header set to "*/*"
        self.client.get("/client/sum", headers={"accept": "*/*"})
