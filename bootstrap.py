__author__ = 'jduan'
__credits__ = "http://nbviewer.ipython.org/gist/aflaxman/6871948"

#!/usr/bin/python3.4
import pymssql
import pandas as pd
import numpy as np
import time
start_time = time.time()

#  Making connection with SQL server
server = ""
user = ""
password = ""
connection = pymssql.connect(server, user, password, "")
cursor = connection.cursor()

step1_a_ABTNewDrop = """\
                select * from table """

cursor.execute(step1_a_ABTNewDrop)
rows = cursor.fetchall()
ab_test = pd.DataFrame.from_records(rows)
ab_test.head(n=5)

cursor.close()


def bootstrap_resample(X, n=None):
    """ Bootstrap resample an array_like
    Parameters
    ----------
    X : array_like
      data to resample
    n : int, optional
      length of resampled array, equal to len(X) if n==None
    Results
    -------
    returns X_resamples
    """
    if isinstance(X, pd.Series):
        X = X.copy()
        X.index = range(len(X.index))
    if n == None:
        n = len(X)

    resample_i = np.floor(np.random.rand(n)*len(X)).astype(int)
    X_resample = np.array(X[resample_i])
    return X_resample


def bootstrap_loop(X, n=None, l=None):
    """This function returns the standard deviation of bootstrapped averages
    :param X: the array you pass for bootstrap
    :param n: resample size
    :param l: the loop times
    :return: standard deviation of the bootstrap samples
    """
    empty = []

    if l == None:
        l = 1000

    if n == None:
        n = len(X)

    i = 0
    while i <= l:
        empty.append(bootstrap_resample(X, n).mean())
        i += 1

    return empty


print(np.std(bootstrap_loop(ab_test[2]))**2)
print("--- %s seconds ---" % (time.time() - start_time))
