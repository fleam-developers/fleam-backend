from recompy import load_movie_data, FunkSVD
from pydantic import BaseModel
from src.eureka import eureka_client

class UserInput(BaseModel):
    userId: int
    numOfRecommendations: int

class MovieInput(BaseModel):
    movieId: int
    numOfRecommendations: int

class ResponseOutput(BaseModel):
    recommendations: list

def train_model() -> FunkSVD:
    clf = FunkSVD()
    clf.fit(load_movie_data())
    return clf

def get_voting(user_id) -> dict:
    ratings = eureka_client.do_service("ACCOUNT-SERVICE", "/movie/rating/user/"+str(user_id), return_type="json")
    return ratings


def predict_recommendations_of_user(user_votings: dict, num_of_items: int) -> list:

    return model.get_recommendation_for_new_user(user_votings, similarity_measure = 'cosine_similarity', 
                                       howManyUsers = 1, howManyItems = num_of_items)

def predict_recommendations_of_movie(movie_id: int, num_of_items: int) -> list:
    # Mock user voting
    user_votings = {
        f'{movie_id}':5,
    }
    print(user_votings)
    return model.get_recommendation_for_new_user(user_votings, similarity_measure = 'cosine_similarity', 
                                       howManyUsers = 1, howManyItems = num_of_items)

model = train_model()