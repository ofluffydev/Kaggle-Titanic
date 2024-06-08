[![Java CI with Gradle](https://github.com/ofluffydev/Kaggle-Titanic/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/ofluffydev/Kaggle-Titanic/actions/workflows/gradle.yml)

# Titanic Survival Prediction

This Java project uses machine learning to predict passenger survival on the Titanic based on historical data. It utilizes the Weka library for building a Support Vector Machine (SVM) classifier and the Apache Commons CSV library for parsing CSV files.

## Features

- Parses training and testing data from CSV files
- Preprocesses data by filling in missing ages and fares with median values
- Encodes categorical features (e.g., sex) as numerical values
- Builds an SVM classifier using the SMO algorithm from the Weka library
- Generates predictions for the test dataset and saves them to a CSV file

## Dependencies

- Java 8 or higher
- Weka library
- Apache Commons CSV library

## Getting Started

1. Clone the repository:
   ```
   git clone https://github.com/your-username/titanic-survival-prediction.git
   ```

2. Ensure that you have the required dependencies in your project's classpath.

3. Place the `train.csv` and `test.csv` files in the `resources` directory of your project.

4. Run the `App` class to execute the program.

5. The predicted survivals will be saved to a CSV file in the `submissions` directory.

## Data

The project uses two CSV files for training and testing:

- `train.csv`: Contains historical data of passengers, including their attributes and survival outcomes.
- `test.csv`: Contains passenger data for which survival predictions need to be made.

Make sure to place these files in the `resources` directory before running the program.

## Model

The project uses the Support Vector Machine (SVM) classifier with the Sequential Minimal Optimization (SMO) algorithm from the Weka library. The classifier is trained on the preprocessed training data and then used to make predictions on the test data.

## Results

The predicted survivals for the test dataset are saved to a CSV file in the `submissions` directory. The file is named `predicted_survivals.csv` by default, but if a file with the same name already exists, a numeric suffix is added (e.g., `predicted_survivals1.csv`, `predicted_survivals2.csv`, etc.).

## Important Note

This code is provided as an example and should not be used directly to create a submission for the Titanic competition on Kaggle or any other platform. The purpose of this project is to demonstrate the implementation of a machine learning model for predicting passenger survival. To achieve competitive results, further improvements, feature engineering, and model tuning may be necessary.

## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE.md).
