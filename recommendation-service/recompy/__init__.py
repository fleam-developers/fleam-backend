from recompy.FunkSVD import FunkSVD
import numpy as np
import pkg_resources


def load_movie_data():

    DATA_PATH = pkg_resources.resource_filename('recompy', 'data/')

    return np.genfromtxt(DATA_PATH + 'ratings.csv',
                         delimiter=',', skip_header=1)
