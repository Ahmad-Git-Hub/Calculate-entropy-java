import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EnrtopyCalculator {

    static Scanner scan = new Scanner(System.in);

    private double[] source;
    double[][] table;
    int samples;
    int symbols;
    static final int NUMBER_OF_COLUMNS = 4;
    final int column0_symbols = 0;
    final int column1_frequencies = 1;
    final int column2_probabilities = 2;
    final int column3_selfInformation = 3;
    boolean isFrequenciesCalculated = false;
    boolean isProbabilityCalculated = false;
    boolean isSelfInformationCalculated = false;

    public EnrtopyCalculator() {

    }

    public EnrtopyCalculator(double[] source) {
        setSourceData(source);
    }

    public void setSourceData(double[] source) {
        this.source = source;
        System.out.println("source data : " + Arrays.toString(source));
        samples = source.length;
        // array list to add distinct values to find number of symbols(data)
        ArrayList<Double> arrayList = new ArrayList<>();
        symbols = 0;
        for (int i = 0; i < samples; i++) {

            if (!arrayList.contains(source[i])) {
                arrayList.add(source[i]);
                symbols++;
            }
        }
        table = new double[symbols][NUMBER_OF_COLUMNS];

        int indexOfArrayList = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            table[i][column0_symbols] = arrayList.get(indexOfArrayList++);

        }
        displayTable();

    }

    // printing method
    public void displayTable() {
        if (table == null) {
            System.out.println("There is no data to display.");
            return;
        }

        // Table Header
        System.out.println();
        System.out.println("\t\t\t\t** Entropy Table **");
        System.out.println();

        // Column Headers with formatting
        System.out.printf("\t\t| %-15s | %-15s | %-15s | %-20s |\n", "Samples", "Frequency", "Probability", "Self Information");
        System.out.println("\t\t|------------------|-----------------|-----------------|--------------------|\n");

        // Table Body
        for (int row = 0; row < table.length; row++) {
            double[] currentRow = table[row];
            System.out.printf("\t\t| row %d \t | ", row + 1); // Use row + 1 to start from 1
            for (double value : currentRow) {
                System.out.printf("%.2f \t\t | ", value); // Format with 2 decimal places
            }
            System.out.println();
        }

        // Table Footer with formatting
        System.out.println("\t\t|------------------|-----------------|-----------------|--------------------|\n");
        System.out.printf("\t\t| Symbols: %d \t | Samples: %d \t |\n", symbols, samples);
        System.out.println();
    }



    public void calculateEntropy() {

        if (source == null) {
            System.out.println("there is no data to calculate");
            return;
        }
        if (!isFrequenciesCalculated) {
            calculateFrequencies();

        }
        if (!isProbabilityCalculated) {
            calculateProbability();
        }
        if (!isSelfInformationCalculated) {
            calculateSelfInformation();
        }

        double entropy = 0;
        for (int row = 0; row < symbols; row++) {
            entropy += table[row][column2_probabilities] * table[row][column3_selfInformation];
        }
        System.out.println("entropy = " + entropy);
    }

    public void calculateFrequencies() {

        int freq = 0;

        for (int row = 0; row < symbols; row++) {
            for (int indexOfSource = 0; indexOfSource < samples; indexOfSource++) {
                if (table[row][column0_symbols] == source[indexOfSource]) {
                    freq++;
                    source[indexOfSource] = 0;
                }
            }
            table[row][column1_frequencies] = freq;
            freq = 0;
        }
        isFrequenciesCalculated = true;
        displayTable();
    }

    public void calculateProbability() {

        if (!isFrequenciesCalculated) {
            calculateFrequencies();
        }

        for (int currentRow = 0; currentRow < symbols; currentRow++) {

            table[currentRow][column2_probabilities] = table[currentRow][column1_frequencies] / samples;

        }
        isProbabilityCalculated = true;
        displayTable();

    }

    public void calculateSelfInformation() {

        if (!isProbabilityCalculated) {
            calculateProbability();
        }

        for (int currentRow = 0; currentRow < symbols; currentRow++) {

            table[currentRow][column3_selfInformation] = log2(1 / table[currentRow][column2_probabilities]);

        }
        isSelfInformationCalculated = true;
        displayTable();

    }

    public double log2(double n) {
        double temp = 1;
        double logBase2 = 0;

        while (temp < n) {
            temp *= 2;
            logBase2++;
        }
        return logBase2;

    }

}
