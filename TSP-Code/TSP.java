import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Time;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.*; 

import javax.swing.*;

public class TSP {

	private static final int cityShiftAmount = 60; //DO NOT CHANGE THIS.
	
    /**
     * How many cities to use.
     */
    protected static int cityCount;

    /**
     * How many chromosomes to use.
     */
    protected static int populationSize = 100; //DO NOT CHANGE THIS.

    /**
     * The part of the population eligable for mating.
     */
    protected static int matingPopulationSize;

    /**
     * The part of the population selected for mating.
     */
    protected static int selectedParents;

    /**
     * The current generation
     */
    protected static int generation;

    /**
     * The list of cities (with current movement applied).
     */
    protected static City[] cities;
    
    /**
     * The list of cities that will be used to determine movement.
     */
    private static City[] originalCities;

    /**
     * The list of chromosomes.
     */
    protected static Chromosome[] chromosomes;
    
    /**
     * Temp list of chromosomes.
     */
    protected static Chromosome[] Tempchromosomes = new Chromosome[populationSize * 2];
    protected static Chromosome[] Tempchromosomes2 = new Chromosome[100];

    /**
    * Frame to display cities and paths
    */
    private static JFrame frame;

    /**
     * Integers used for statistical data
     */
    private static double min;
    private static double avg;
    private static double max;
    private static double sum;
    private static double genMin;

    /**
     * Width and Height of City Map, DO NOT CHANGE THESE VALUES!
     */
    private static int width = 600;
    private static int height = 600;


    private static Panel statsArea;
    private static TextArea statsText;


    /*
     * Writing to an output file with the costs.
     */
    private static void writeLog(String content) {
        String filename = "results.out";
        FileWriter out;

        try {
            out = new FileWriter(filename, true);
            out.write(content + "\n");
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     *  Deals with printing same content to System.out and GUI
     */
    private static void print(boolean guiEnabled, String content) {
        if(guiEnabled) {
            statsText.append(content + "\n");
        }

        System.out.println(content);
    }

    public static void evolve() {
    	
    	//Add parents to Tempchromosome list
//    	for (int i = 0; i < populationSize; i++){
//    		
//    		Tempchromosomes[i+100] = chromosomes[i];
//    		
//    	}
    	
    	
    	//Make a temp copy of the list and sort it
//    	Chromosome[] sorted = new Chromosome[chromosomes.length];
//    	System.arraycopy(chromosomes, 0, sorted, 0, chromosomes.length);
    	//Sort the list
//    	sorted = sortChromosomes(sorted, sorted.length);
    	//Select Parents
//    	int x = 0;
    	
    	
    	
//    	for (int i = 0; i < populationSize; i++){ 
//    		Random rand = new Random();
    		
    		//Non-Probabilistic (Elite Selection)
//        	x = rand.nextInt(1); // Top 3
    		
    		
    		
    		
    		
    		//Probabilistic (Fitness proportionate)
    		
//    		int n = rand.nextInt(100);
//    		
//    		if (n <= 49){
//    			x = rand.nextInt(25);
//    		}
//    		else if (n >= 50 && n <= 79){
//    			x = rand.nextInt(50-25) + 25;
//    		}
//    		else if (n >= 80 && n <= 94){
//    			x = rand.nextInt(75-50) + 50;
//    		}
//    		else if (n >= 95 && n <= 99){
//    			x = rand.nextInt(100-75) + 75;
//    		}
    		
    	// Uncomment either of the above to assign an x value
    	// Uncomment below 
    		
//    		Chromosome parent1 = sorted[x];
//    		    		
//    		Chromosome child1;
//    		child1 = new Chromosome(cities);
//    		child1.setCities(parent1.getCities());
//    		//mutate
//    		
//    		child1.inversionMutation(); 			//a
//    		//child1.transpositionMutation();		//b
//    		//child1.translocationMutation();		//c
//    		//child1.threepointexchangeMutation();	//d
//    		
//    		child1.calculateCost(cities);
//    		Tempchromosomes[i] = child1;
    		
    	//Uncomment below for parent replace child if fitter (Plus the code below for child replace parent), else child always replace parent or select top 100 from both children and parents
    		//child replace parent if fitter than parent
    		
//    		double parentScore = parent1.getCost();
//    		double childScore = child1.getCost();
//    		
//    		if (childScore > parentScore){
//    			Tempchromosomes[i] = parent1;
//    		}
    		
    		
//    	}
    	
    	
    	
    	//Elitist - Select top 100
    	
//    	Chromosome[] sortedTemp = new Chromosome[populationSize * 2];
//    	System.arraycopy(Tempchromosomes, 0, sortedTemp, 0, Tempchromosomes.length);
//    	sortedTemp = sortChromosomes(sortedTemp, sortedTemp.length);
//    	
//    	for (int i = 0; i < populationSize; i++){
//    		
//    		chromosomes[i] = sortedTemp[i];
//    	}
    	
    	
    	//Child replace Parent
    	
//    	for (int i = 0; i < populationSize; i++){
//    		
//    		chromosomes[i] = Tempchromosomes[i];
//    		//chromosomes[i].setCities(Tempchromosomes[i].getCities());
//    		//chromosomes[i].calculateCost(cities);
//    	}
    	
    	tournamentSelection(); // with selecting top 100 children
    	
    	
    }
    
    public static void tournamentSelection(){
    	
    	Chromosome[] sorted = new Chromosome[chromosomes.length];
    	System.arraycopy(chromosomes, 0, sorted, 0, chromosomes.length);
    	//Sort the list
    	sorted = sortChromosomes(sorted, sorted.length);
    	//Select Parents
    	int x = 0;
    	
    	//Tournament selection
    	int numParents = 100;
    	Chromosome[] tempParents = new Chromosome[numParents];
    	
    	
    	int[] list = new int[numParents];
    	int count = 0;
    	while (true){
    		
    		Random rand = new Random();
    		x = rand.nextInt(100);
    		
    		if (Arrays.asList(list).contains(x) == false){
    			
    			tempParents[count] = sorted[x];
    			list[count] = x;
    			count++;
    			
    		}
    		
    		if (count == numParents){
    			break;
    		}
    		
    		
    	}
    	
    	tempParents = sortChromosomes(tempParents, tempParents.length);
    	
    	int k = 0;
    	for (int i = 0; i < 1; i++){
    		Chromosome parent1 = tempParents[i];
    		
    		for (int j = 0; j < 100; j++){
    					
				Chromosome child1;
				child1 = new Chromosome(cities);
				child1.setCities(parent1.getCities());
				
				child1.inversionMutation(); 			//a
	    		//child1.transpositionMutation();		//b
	    		//child1.translocationMutation();		//c
	    		//child1.threepointexchangeMutation();	//d
	    		child1.calculateCost(cities);
	    		Tempchromosomes2[j+k] = child1;
    		}
    		
    		k += 100;
    	}
    	
    	//Add parents for tournament selection (Must increase Tempchromosomes2 size to 3100 first
//    	for (int i = 0; i < 100; i++){
//    		
//    		Tempchromosomes2[i+3000] = chromosomes[i];
//    		
//    	}
    	
    	
    	//Select top 100 children from tournament method (Elitist)
    	Tempchromosomes2 = sortChromosomes(Tempchromosomes2, Tempchromosomes2.length);
    	
    	for (int i = 0; i < populationSize; i++){
    		
    		chromosomes[i] = Tempchromosomes2[i];
    	}
    	
    	
    }
    
    
    /**
     * Create 2 children, Mutate the children, add them to the temp Population
     * @param The 2 chromosomes representing the 2 parents
     */
    public static void createChildren(Chromosome parent1, Chromosome parent2, int count){
    	// Create Children (Recombination of Parents)
    	
    	Chromosome child1;
    	Chromosome child2;
    	
    	int[] child1Cities = new int[cityCount];
    	int[] child2Cities = new int[cityCount];

    	int[] parent1Cities = parent1.getCities();
    	int[] parent2Cities = parent2.getCities();

    	Random rand = new Random();
    	int range = rand.nextInt(cityCount);
    	
    	for (int i = 0; i < range; i ++){
    		child1Cities[i] = parent1Cities[i];
    		child2Cities[i] = parent2Cities[i];
    	}
    	
    	for (int j = range; j < cityCount; j ++){
    		child1Cities[j] = parent1Cities[j];
    		child2Cities[j] = parent2Cities[j];
    	}
    	
    	//Mutate Children
    	
    	//Add Children to temp population
    	child1 = new Chromosome(cities);
    	child2 = new Chromosome(cities);
    	child1.setCities(child1Cities);
    	child2.setCities(child2Cities);
    	child1.calculateCost(cities);
    	child2.calculateCost(cities);
    	Tempchromosomes[count] = child1;
    	Tempchromosomes[count+1] = child2;
    	
    }
    /**
     * Sort the chromosomes by their cost.
     *
     * @param chromosomes An array of chromosomes to sort.
     * @param num         How much of the chromosome list to sort.
     */
    public static Chromosome[] sortChromosomes(Chromosome chromosomes[], int num) {
        Chromosome ctemp;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < num - 1; i++) {
                if (chromosomes[i].getCost() > chromosomes[i + 1].getCost()) {
                    ctemp = chromosomes[i];
                    chromosomes[i] = chromosomes[i + 1];
                    chromosomes[i + 1] = ctemp;
                    swapped = true;
                }
            }
        }
        return chromosomes;
    }

    /**
     * Update the display
     */
    public static void updateGUI() {
        Image img = frame.createImage(width, height);
        Graphics g = img.getGraphics();
        FontMetrics fm = g.getFontMetrics();

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        if (true && (cities != null)) {
            for (int i = 0; i < cityCount; i++) {
                int xpos = cities[i].getx();
                int ypos = cities[i].gety();
                g.setColor(Color.green);
                g.fillOval(xpos - 5, ypos - 5, 10, 10);
                
//                // SHOW Outline of movement boundary
//                 xpos = originalCities[i].getx();
//                 ypos = originalCities[i].gety();
//                 g.setColor(Color.darkGray);
//                 g.drawLine(xpos + cityShiftAmount, ypos, xpos, ypos + cityShiftAmount);
//                 g.drawLine(xpos, ypos + cityShiftAmount, xpos - cityShiftAmount, ypos);
//                 g.drawLine(xpos - cityShiftAmount, ypos, xpos, ypos - cityShiftAmount);
//                 g.drawLine(xpos, ypos - cityShiftAmount, xpos + cityShiftAmount, ypos);
            }

            g.setColor(Color.gray);
            for (int i = 0; i < cityCount; i++) {
                int icity = chromosomes[0].getCity(i);
                if (i != 0) {
                    int last = chromosomes[0].getCity(i - 1);
                    g.drawLine(
                        cities[icity].getx(),
                        cities[icity].gety(),
                        cities[last].getx(),
                        cities[last].gety());
                }
            }
                        
            int homeCity = chromosomes[0].getCity(0);
            int lastCity = chromosomes[0].getCity(cityCount - 1);
                        
            //Drawing line returning home
            g.drawLine(
                    cities[homeCity].getx(),
                    cities[homeCity].gety(),
                    cities[lastCity].getx(),
                    cities[lastCity].gety());
        }
        frame.getGraphics().drawImage(img, 0, 0, frame);
    }

    private static City[] LoadCitiesFromFile(String filename, City[] citiesArray) {
        ArrayList<City> cities = new ArrayList<City>();
        try 
        {
            FileReader inputFile = new FileReader(filename);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line;
            while ((line = bufferReader.readLine()) != null) { 
                String [] coordinates = line.split(", ");
                cities.add(new City(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
            }

            bufferReader.close();

        } catch (Exception e) {
            System.out.println("Error while reading file line by line:" + e.getMessage());                      
        }
        
        citiesArray = new City[cities.size()];
        return cities.toArray(citiesArray);
    }

    private static City[] MoveCities(City[]cities) {
    	City[] newPositions = new City[cities.length];
        Random randomGenerator = new Random();

        for(int i = 0; i < cities.length; i++) {
        	int x = cities[i].getx();
        	int y = cities[i].gety();
        	
            int position = randomGenerator.nextInt(5);
            
            if(position == 1) {
            	y += cityShiftAmount;
            } else if(position == 2) {
            	x += cityShiftAmount;
            } else if(position == 3) {
            	y -= cityShiftAmount;
            } else if(position == 4) {
            	x -= cityShiftAmount;
            }
            
            newPositions[i] = new City(x, y);
        }
        
        return newPositions;
    }

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String currentTime  = df.format(today);

        int runs;
        boolean display = false;
        String formatMessage = "Usage: java TSP 1 [gui] \n java TSP [Runs] [gui]";

        if (args.length < 1) {
            System.out.println("Please enter the arguments");
            System.out.println(formatMessage);
            display = false;
        } else {

            if (args.length > 1) {
                display = true; 
            }

            try {
                cityCount = 50;
                populationSize = 100;
                runs = Integer.parseInt(args[0]);

                if(display) {
                    frame = new JFrame("Traveling Salesman");
                    statsArea = new Panel();

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setSize(width + 300, height);
                    frame.setResizable(false);
                    frame.setLayout(new BorderLayout());
                    
                    statsText = new TextArea(35, 35);
                    statsText.setEditable(false);

                    statsArea.add(statsText);
                    frame.add(statsArea, BorderLayout.EAST);
                    
                    frame.setVisible(true);
                }


                min = 0;
                avg = 0;
                max = 0;
                sum = 0;

                originalCities = cities = LoadCitiesFromFile("CityList.txt", cities);

                writeLog("Run Stats for experiment at: " + currentTime);
                for (int y = 1; y <= runs; y++) {
                    genMin = 0;
                    print(display,  "Run " + y + "\n");

                // create the initial population of chromosomes
                    chromosomes = new Chromosome[populationSize];
                    for (int x = 0; x < populationSize; x++) {
                        chromosomes[x] = new Chromosome(cities);
                    }

                    generation = 0;
                    double thisCost = 0.0;

                    while (generation < 100) {
                        evolve();
                        if(generation % 5 == 0 ) 
                            cities = MoveCities(originalCities); //Move from original cities, so they only move by a maximum of one unit.
                        generation++;

                        Chromosome.sortChromosomes(chromosomes, populationSize);
                        double cost = chromosomes[0].getCost();
                        thisCost = cost;

                        if (thisCost < genMin || genMin == 0) {
                            genMin = thisCost;
                        }
                        
                        NumberFormat nf = NumberFormat.getInstance();
                        nf.setMinimumFractionDigits(2);
                        nf.setMinimumFractionDigits(2);

                        print(display, "Gen: " + generation + " Cost: " + (int) thisCost);

                        if(display) {
                            updateGUI();
                        }
                    }

                    writeLog(genMin + "");

                    if (genMin > max) {
                        max = genMin;
                    }

                    if (genMin < min || min == 0) {
                        min = genMin;
                    }

                    sum +=  genMin;

                    print(display, "");
                }

                avg = sum / runs;
                print(display, "Statistics after " + runs + " runs");
                print(display, "Solution found after " + generation + " generations." + "\n");
                print(display, "Statistics of minimum cost from each run \n");
                print(display, "Lowest: " + min + "\nAverage: " + avg + "\nHighest: " + max + "\n");

            } catch (NumberFormatException e) {
                System.out.println("Please ensure you enter integers for cities and population size");
                System.out.println(formatMessage);
            }
        }
    }
}