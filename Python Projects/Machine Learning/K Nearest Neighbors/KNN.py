import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split as tts
import matplotlib.pyplot as plt
from scipy.spatial import distance
import statistics

np.random.seed(42)

n = 100  # synthetic samples for 2D data

X1 = np.random.normal(loc=-2.0, scale=2.0, size=int(n / 2))  # class0 dim1
X2 = np.random.normal(loc=2.0, scale=2.0, size=int(n / 2))  # class1 dim1
dim1 = np.concatenate((X1, X2), axis=0)

Y1 = np.random.normal(loc=0.0, scale=1.0, size=int(n / 2))  # class0 dim2
Y2 = np.random.normal(loc=0.0, scale=1.0, size=int(n / 2))  # class1 dim2
dim2 = np.concatenate((Y1, Y2), axis=0)

l1 = [0] * int(n / 2)
l2 = [1] * int(n / 2)
labels = l1 + l2
print(labels)

dt = pd.DataFrame({'Dim1': dim1, 'Dim2': dim2, 'Label': labels}, columns=['Dim1', 'Dim2', 'Label'])
dt.head(100)

plt.scatter(dim1, dim2, s=35, c='r')
plt.show()

X_train, X_test, Y_train, Y_test = tts(dt, labels, test_size=0.2, random_state=0)

referenceData = pd.DataFrame({'Dim1': X_train.Dim1, 'Dim2': X_train.Dim2, 'Label': Y_train},
                             columns=['Dim1', 'Dim2', 'Label'])
referenceData.head(100)
print(len(referenceData))  # number of the refrence data/training data assigned

newObservations = pd.DataFrame({'Dim1': X_test.Dim1, 'Dim2': X_test.Dim2}, columns=['Dim1', 'Dim2'])
newObservations.head(100)
print(len(newObservations))  # number of the new observations/test data assigned


def knn(newObservation, referenceData, k=3):  # k hyper parameter is set to 3
    list_of_all_distances = []
    for i in range(len(referenceData.index)):
        get_distance = distance.euclidean(newObservation.iloc[0, :], referenceData.iloc[i, :-1])
        # calculates distance between new observation/test data
        # and reference data/training data
        list_of_all_distances.append((get_distance, i))

    list_of_sorted_distances = sorted(list_of_all_distances)  # sorts all distances in list
    k_nearest_distances = list_of_sorted_distances[:3]  # takes nearest k=3 neighbors
    k_nearest_neighbors_labels = [referenceData.iloc[i, -1]
                                  for d, i in k_nearest_distances]
    return statistics.mode(k_nearest_neighbors_labels)


two_dimensions_prediction_results = []  # 2D prediction results list initialized
for row in newObservations.itertuples():
    newObservation = pd.DataFrame({'Dim1': [row.Dim1], 'Dim2': [row.Dim2]}, columns=['Dim1', 'Dim2'])
    two_dimensions_prediction_results.append((knn(newObservation, referenceData)))
print(two_dimensions_prediction_results)

count = 0
for i in range(len(two_dimensions_prediction_results)):  # accuracy of predictions on test data in 2D dataset
    if two_dimensions_prediction_results[i] == Y_test[i]:
        count += 1
accuracy = count / len(Y_test)
print('Accuracy : %f' % accuracy)

plt.subplot(1, 2, 1)
plt.scatter(X_train.iloc[:, 0], X_train.iloc[:, 1], s=25, c=Y_train, marker=".")
plt.scatter(X_test.iloc[:, 0], X_test.iloc[:, 1], s=50, c=Y_test, marker="v")
plt.title("Actual labels")

plt.subplot(1, 2, 2)
plt.scatter(X_train.iloc[:, 0], X_train.iloc[:, 1], s=25, c=Y_train, marker=".")
plt.scatter(X_test.iloc[:, 0], X_test.iloc[:, 1], s=50, c=two_dimensions_prediction_results, marker="v")
plt.title("Predicted labels")

plt.tight_layout()
plt.show()

n = 1000  # synthetic samples for 3D data

X1 = np.random.normal(loc=0.0, scale=3.0, size=int(n / 4))
X2 = np.random.normal(loc=0.0, scale=3.0, size=int(n / 4))
X3 = np.random.normal(loc=0.0, scale=3.0, size=int(n / 4))
X4 = np.random.normal(loc=0.0, scale=3.0, size=int(n / 4))
dim1 = np.concatenate((X1, X2, X3, X4), axis=0)

Y1 = np.random.normal(loc=-3.0, scale=1.0, size=int(n / 4))
Y2 = np.random.normal(loc=1.0, scale=2.0, size=int(n / 4))
Y3 = np.random.normal(loc=3.0, scale=1.0, size=int(n / 4))
Y4 = np.random.normal(loc=5.0, scale=3.0, size=int(n / 4))
dim2 = np.concatenate((Y1, Y2, Y3, Y4), axis=0)

Z1 = np.random.normal(loc=-1.0, scale=1.0, size=int(n / 4))
Z2 = np.random.normal(loc=1.0, scale=1.0, size=int(n / 4))
Z3 = np.random.normal(loc=4.0, scale=1.0, size=int(n / 4))
Z4 = np.random.normal(loc=-3.0, scale=1.0, size=int(n / 4))
dim3 = np.concatenate((Z1, Z2, Z3, Z4), axis=0)

l1 = [0] * int(n / 4)
l2 = [1] * int(n / 4)
l3 = [2] * int(n / 4)
l4 = [3] * int(n / 4)
labels = l1 + l2 + l3 + l4
print(labels)

dt = pd.DataFrame({'Dim1': dim1, 'Dim2': dim2, 'Dim3': dim3, 'Label': labels},
                  columns=['Dim1', 'Dim2', 'Dim3', 'Label'])
dt.head(1000)

X_train, X_test, Y_train, Y_test = tts(dt, labels, test_size=0.2, random_state=0)

referenceData = pd.DataFrame({'Dim1': X_train.Dim1, 'Dim2': X_train.Dim2, 'Dim3': X_train.Dim3, 'Label': Y_train},
                             columns=['Dim1', 'Dim2', 'Dim3', 'Label'])
referenceData.head(1000)
print(len(referenceData))

newObservations = pd.DataFrame({'Dim1': X_test.Dim1, 'Dim2': X_test.Dim2, 'Dim3': X_test.Dim3},
                               columns=['Dim1', 'Dim2', 'Dim3'])
newObservations.head(1000)
print(len(newObservations))

three_dimensions_prediction_results = []  # 3D prediction results list initialized
for row in newObservations.itertuples():
    newObservation = pd.DataFrame({'Dim1': [row.Dim1], 'Dim2': [row.Dim2], 'Dim3': [row.Dim3]},
                                  columns=['Dim1', 'Dim2', 'Dim3'])
    three_dimensions_prediction_results.append((knn(newObservation, referenceData)))
print(three_dimensions_prediction_results)

count = 0
for i in range(len(three_dimensions_prediction_results)):  # accuracy of predictions on test data in 3D dataset
    if three_dimensions_prediction_results[i] == Y_test[i]:
        count += 1
accuracy = count / len(Y_test)
print('Accuracy : %f' % accuracy)

