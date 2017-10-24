import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Chromosome {

    /**
     * The list of cities, which are the genes of this chromosome.
     */
    protected int[] cityList;
    protected int[] tempList;

    /**
     * The cost of following the cityList order of this chromosome.
     */
    protected double cost;

    /**
     * @param cities The order that this chromosome would visit the cities.
     */
    Chromosome(City[] cities) {
        Random generator = new Random();
        cityList = new int[cities.length];
        tempList = new int[cities.length];
        //cities are visited based on the order of an integer representation [o,n] of each of the n cities.
        for (int x = 0; x < cities.length; x++) {
            cityList[x] = x;
        }

        //shuffle the order so we have a random initial order
        for (int y = 0; y < cityList.length; y++) {
            int temp = cityList[y];
            int randomNum = generator.nextInt(cityList.length);
            cityList[y] = cityList[randomNum];
            cityList[randomNum] = temp;
        }

        calculateCost(cities);
        
    }
    
    /**
     * Perform Inversion Mutation, Cuts out a segment of a tour and re-inserts it in opposite direction.
     */
    void inversionMutation(){
    	Random rand = new Random();
    	int start = rand.nextInt(cityList.length);
    	int end = rand.nextInt(cityList.length - start) + start;
    	//System.out.println(start + " " + end);
    	int[] vals = new int[end-start];
    	
    	System.arraycopy(cityList, start, vals, 0, end-start);
    	
    	for(int i = 0; i < vals.length / 2; i++)
    	{
    	    int temp = vals[i];
    	    vals[i] = vals[vals.length - i - 1];
    	    vals[vals.length - i - 1] = temp;
    	}

	for (int i = 0; i < vals.length; i++){
	    int value2 = vals[i];
	    cityList[start + i] = value2;
    	}
    }
    
    /**
     * Perform Translocation Mutation (Insertion), Selects a random position in the genotype, then inserts this gene into a new (random) position.
     */
    void translocationMutation(){
    	Random rand = new Random();
    	int x = rand.nextInt(cityList.length);
    	int y = rand.nextInt(cityList.length);
    	
    	int element = cityList[x];
    	
    	int[] temp = new int[cityList.length];
    	
    	temp[y] = element;
    	
    	for (int i = 0; i < cityList.length; i ++){
    		
    		if(i >= x && i < y){
    			
    			temp[i] = cityList[i+1];
    			
    		}
    		else if (i > y || i < x){
    			
    			temp[i] = cityList[i];
    		}
    		
    		
    	}
    	
    	
    	for (int i = 0; i < cityList.length; i ++){
    		
    		setCity(i, temp[i]);
    		
    	}
    	
    	
    	
    	
    	
    }
    
    /**
     * Perform 3-point exchange (shifting) Mutation, Shifts randomly chosen segment (between 2 points) to a 3rd selected point. 
     */
    void threepointexchangeMutation(){
    	
    	Random rand = new Random();
    	
    	int start = rand.nextInt(cityList.length);
    	int end = rand.nextInt(cityList.length - start) + start;
    	
    	int newEnd = rand.nextInt(cityList.length - end) + end;
    	
    	List<Integer> temp = new ArrayList<Integer>(cityList.length);
    	
    	int count = 0;
    	for (int i = 0; i < cityList.length; i++){
    		
    		if (i < start || i > end){
    			
    			temp.add(count,cityList[i]);
    			count++;
    		}
    	}
    	
    	for (int i = start; i < end+1; i++){
    		
    		int pos = newEnd - end - start + (i);
    		
    		int value = cityList[i];
    		
    		temp.add(pos,value);
    	}
    	
    	for (int i = 0; i < cityList.length; i++){
    		int value = temp.get(i);
    		setCity(i, value);
    	}
    }
    
    /**
     * Perform Transposition (2-exchange) Mutation, Exchanges 2 randomly chosen points in a tour.
     */
    void transpositionMutation(){
    	Random rand = new Random();
    	int x = rand.nextInt(cityList.length);
    	int y = rand.nextInt(cityList.length);
    	
    	int value1 = cityList[y];
    	int value2 = cityList[x];
    	
    	setCity(x, value1);
    	setCity(y, value2);

    	
    }
    

    /**
     * Calculate the cost of the specified list of cities.
     *
     * @param cities A list of cities.
     */
    void calculateCost(City[] cities) {
        cost = 0;
        for (int i = 0; i < cityList.length - 1; i++) {
            double dist = cities[cityList[i]].proximity(cities[cityList[i + 1]]);
            cost += dist;
        }

        cost += cities[cityList[0]].proximity(cities[cityList[cityList.length - 1]]); //Adding return home
    }

    /**
     * Get the cost for this chromosome. This is the amount of distance that
     * must be traveled.
     */
    double getCost() {
        return cost;
    }

    /**
     * @param i The city you want.
     * @return The ith city.
     */
    int getCity(int i) {
        return cityList[i];
    }
    
    /**
     * @return The list of cities
     */
    int[] getCities() {
        return cityList;
    }

    /**
     * Set the order of cities that this chromosome would visit.
     *
     * @param list A list of cities.
     */
    void setCities(int[] list) {
        for (int i = 0; i < cityList.length; i++) {
            cityList[i] = list[i];
        }
    }

    /**
     * Set the index'th city in the city list.
     *
     * @param index The city index to change
     * @param value The city number to place into the index.
     */
    void setCity(int index, int value) {
        cityList[index] = value;
    }

    /**
     * Sort the chromosomes by their cost.
     *
     * @param chromosomes An array of chromosomes to sort.
     * @param num         How much of the chromosome list to sort.
     */
    public static void sortChromosomes(Chromosome chromosomes[], int num) {
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
    }
}
