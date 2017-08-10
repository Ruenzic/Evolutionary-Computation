Josh di Bona (DBNJOS001)
Classmate: Jeremy Coupland (CPLJER001)

Note: There are some comments in the code for changing between different parent selection methods etc. 
If run like it is, it will run the same run used to generate my data that i used for the stats test.

			Josh		Jeremy
Mean			4415.2		4258.1
Median			4432		4263.5
Lowest			3576		3650
Highest			4971		4670
Standard Deviation	264.46		191.40
				
Mean - STD		4150.74		4066.70
Mean + STD		4679.66		4449.50
				
Mean - (STDx2)		3886.29		3875.30
Mean + (STDx2)		4944.11		4640.90
				
Mean - (STDx3)		3621.83		3683.90
Mean + (STDx3)		5208.57		4832.30
				
				
				
% Within 1 STD		67		69
% Within 2 STD		97		97
% Within 3 STD		99		99



Both datasets have roughly 68% within 1 standard deviation, 95% within 2 standard deviations and 99% within 3							
Both datasets have a bell curve when plotted on a histogram (See excel spreadsheet)							
Therefore we can conclude that both datasets are normally distributed, and parametric.							

F-Test	F-Test Two-Sample for Variances		
			
			Variable 1	Variable 2
Mean			4415.2		4258.1
Variance		69937.07071	36633.76768
Observations		100		100
df			99		99
F			1.909087575	
P(F<=f) one-tail	0.00073241	
F Critical one-tail	1.394061257	



T-Test	t-Test: Two-Sample Assuming Unequal Variances			
				
				Variable 1	Variable 2	
Mean				4415.2		4258.1	
Variance			69937.07071	36633.76768	
Observations			100		100	
Hypothesized Mean Difference	0		
df				180		
t Stat				4.812347651		
P(T<=t) one-tail		1.57276E-06		
t Critical one-tail		1.653363013		
P(T<=t) two-tail		3.14553E-06		
t Critical two-tail		1.973230823		
	

The t Stat is > t Critical two-tail therefore we reject the null hypothesis (which is that the 2 datasets are the same/similar) 

In my algorithm I select the top parent of the 100 in the current population (Elitist Selection) 
With this parent I create 100 children using Inversion Mutation. 
These 100 children then form the new population, replacing all previous parents. 

Jeremy's algorithm uses Elitst selection to select the top parent, from which he creates 100 children. 
The first 94 generations, his children are mutated using Inversion Mutation, and for the remaining 6 he uses Translocation Mutation. 
The 100 generated children replace all the parents, and form the new population. 


The means between the 2 algorithms differ by almost 200. The same selection and replacement methods are used. 
Although Jeremy's algorithm doesn't only use inversion mutation. He uses translocation towards the end of the generations. 
Inversion mutation changes 2 edges in a tour while translocation changes 3 edges. Therefore inversion is more exploitative while translocation is 
a little more explorative. It would have made more sense to switch from explorative to exploitative methods towards the end, but it all depends on the 
fitness landscape. In this case his algorithm exploits to a small cost then right at the end it explores by a little bit more (only 1 edge)
to see if it was perhaps stuck on a local maximum on its way up to the global (or a higher local maximum). 












