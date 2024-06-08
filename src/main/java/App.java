import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import weka.classifiers.functions.SMO;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("DuplicatedCode")
public class App {

    public static void main(String[] args) {
        InputStream testStream = App.class.getResourceAsStream("/test.csv");
        InputStream trainStream = App.class.getResourceAsStream("/train.csv");

        if (trainStream == null) throw new RuntimeException("Train data not found");
        if (testStream == null) throw new RuntimeException("Test data not found");
        Reader trainReader = new InputStreamReader(trainStream);
        Reader testReader = new InputStreamReader(testStream);
        Iterable<CSVRecord> trainingRecords, testingRecords;
        ArrayList<Passenger> trainingData = new ArrayList<>();
        ArrayList<TestPassenger> testingData = new ArrayList<>();

        try {
            trainingRecords = CSVFormat.RFC4180.builder().setHeader().setSkipHeaderRecord(true).build().parse(trainReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            testingRecords = CSVFormat.RFC4180.builder().setHeader().setSkipHeaderRecord(true).build().parse(testReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ParseTraining(trainingRecords, trainingData);
        ParseTesting(testingRecords, testingData);
        FillMissingAgesInTraining(trainingData);

        Instances trainingInstances = BuildInstances(trainingData);
        SMO classifier = new SMO();
        BuildClassifier(classifier, trainingInstances);
        Instances testInstances = BuildInstancesForTesting(testingData);
        String outputFile = FindOutputFile();
        try (FileWriter fileWriter = new FileWriter(outputFile); CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.builder().setHeader("PassengerId", "Survived").build())) {
            for (int i = 0; i < testInstances.numInstances(); i++) {
                int passengerId = 892 + i;
                double predictedClass = classifier.classifyInstance(testInstances.instance(i));
                int predictedSurvival = (int) predictedClass;
                csvPrinter.printRecord(passengerId, predictedSurvival);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void FillMissingAgesInTraining(ArrayList<Passenger> trainingData) {
        double sum = 0;
        for (Passenger passenger : trainingData) {
            if (!passenger.getAge().isEmpty()) {
                sum += Double.parseDouble(passenger.getAge());
            }
        }
        double median = sum / trainingData.size();
        median = Math.round(median);
        for (Passenger passenger : trainingData) {
            if (passenger.getAge().isEmpty()) {
                passenger.setAge(String.valueOf(median));
            }
        }
    }

    private static void BuildClassifier(SMO classifier, Instances trainingInstances) {
        try {
            classifier.buildClassifier(trainingInstances);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String FindOutputFile() {
        String submissionsFolder = "submissions";
        String outputFile = submissionsFolder + "/predicted_survivals.csv";
        File folder = new File(submissionsFolder);
        if (!folder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            folder.mkdir();
        }
        int i = 1;
        while (new File(outputFile).exists()) {
            outputFile = submissionsFolder + "/predicted_survivals" + i + ".csv";
            i++;
        }
        return outputFile;
    }

    private static Instances BuildInstancesForTesting(ArrayList<TestPassenger> testingData) {
        double sum = 0;
        for (TestPassenger passenger : testingData) {
            if (!passenger.getAge().isEmpty()) {
                sum += Double.parseDouble(passenger.getAge());
            }
        }
        double median = sum / testingData.size();
        median = Math.round(median);

        sum = 0;
        for (TestPassenger passenger : testingData) {
            if (!passenger.getFare().isEmpty()) {
                sum += Double.parseDouble(passenger.getFare());
            }
        }
        double medianFare = sum / testingData.size();

        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("PassengerClass"));
        attributes.add(new Attribute("Sex"));
        attributes.add(new Attribute("Age"));
        attributes.add(new Attribute("SibSp"));
        attributes.add(new Attribute("Parch"));
        attributes.add(new Attribute("Fare"));

        ArrayList<String> survivedValues = new ArrayList<>();
        survivedValues.add("0");
        survivedValues.add("1");
        attributes.add(new Attribute("Survived", survivedValues));

        Instances instances = new Instances("Titanic Dataset", attributes, testingData.size());
        instances.setClassIndex(instances.numAttributes() - 1);
        for (TestPassenger passenger : testingData) {
            double[] values = new double[instances.numAttributes()];
            values[0] = Double.parseDouble(passenger.getPclass());
            values[1] = EncodeSex(passenger.getSex());
            if (passenger.getAge().isEmpty()) {
                values[2] = median;
            } else {
                values[2] = Double.parseDouble(passenger.getAge());
            }
            values[3] = Double.parseDouble(passenger.getSibSp());
            values[4] = Double.parseDouble(passenger.getParch());
            if (passenger.getFare().isEmpty()) {
                values[5] = medianFare;
            } else {
                values[5] = Double.parseDouble(passenger.getFare());
            }
            instances.add(new DenseInstance(1.0, values));
        }

        return instances;
    }

    private static Instances BuildInstances(ArrayList<Passenger> trainingData) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("PassengerClass"));
        attributes.add(new Attribute("Sex"));
        attributes.add(new Attribute("Age"));
        attributes.add(new Attribute("SibSp"));
        attributes.add(new Attribute("Parch"));
        attributes.add(new Attribute("Fare"));

        ArrayList<String> survivedValues = new ArrayList<>();
        survivedValues.add("0");
        survivedValues.add("1");
        attributes.add(new Attribute("Survived", survivedValues));

        Instances instances = new Instances("Titanic Dataset", attributes, trainingData.size());
        instances.setClassIndex(instances.numAttributes() - 1);

        for (Passenger passenger : trainingData) {
            double[] values = new double[instances.numAttributes()];
            values[0] = Double.parseDouble(passenger.getPassengerClass());
            values[1] = EncodeSex(passenger.getSex());
            values[2] = Double.parseDouble(passenger.getAge());
            values[3] = Double.parseDouble(passenger.getSibSp());
            values[4] = Double.parseDouble(passenger.getParch());
            values[5] = Double.parseDouble(passenger.getFare());
            values[6] = instances.attribute(6).indexOfValue(passenger.getSurvived());

            instances.add(new DenseInstance(1.0, values));
        }

        return instances;
    }

    private static double EncodeSex(String sex) {
        if (sex.equals("male")) {
            return 0;
        } else if (sex.equals("female")) {
            return 1;
        }
        throw new IllegalArgumentException();
    }

    private static void ParseTraining(Iterable<CSVRecord> trainingRecords, ArrayList<Passenger> trainingData) {
        for (CSVRecord record : trainingRecords) {
            String id = record.get("PassengerId");
            String survived = record.get("Survived");
            String pclass = record.get("Pclass");
            String name = record.get("Name");
            String sex = record.get("Sex");
            String age = record.get("Age");
            String sibSp = record.get("SibSp");
            String parch = record.get("Parch");
            String ticket = record.get("Ticket");
            String fare = record.get("Fare");
            String cabin = record.get("Cabin");
            String embarked = record.get("Embarked");
            Passenger passenger = new Passenger(id, survived, pclass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked);
            trainingData.add(passenger);
        }
    }

    private static void ParseTesting(Iterable<CSVRecord> trainingRecords, ArrayList<TestPassenger> trainingData) {
        for (CSVRecord record : trainingRecords) {
            String id = record.get("PassengerId");
            String pclass = record.get("Pclass");
            String name = record.get("Name");
            String sex = record.get("Sex");
            String age = record.get("Age");
            String sibSp = record.get("SibSp");
            String parch = record.get("Parch");
            String ticket = record.get("Ticket");
            String fare = record.get("Fare");
            String cabin = record.get("Cabin");
            String embarked = record.get("Embarked");
            TestPassenger passenger = new TestPassenger(id, pclass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked);
            trainingData.add(passenger);
        }
    }
}