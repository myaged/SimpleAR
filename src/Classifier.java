package com.my.android.activityrecognition;

/*
 * 2-class classification using online gradient descent and logistic regression function
 * For details, see "Machine Learning", Alpaydin, 2e, MIT Press
 * 
 * EXAMPLE USAGE:
 * Classifier cl = new Classifier(3, 0.05);
 * Double[] input = {1.0, 0.0, 0.0}; cl.train(1, input);
 * Double[] input2 = {0.0, 0.0, 1.0}; cl.train(0, input2);
 * Double[] input3 = {1.0, 0.2, 0.2}; int label = cl.test(input3));
 */
public class Classifier {
	private int numInputs;
	private Double[] weights;
	private Double learningRate;
	
	public Classifier(int numInputs, Double learningRate){
		this.numInputs = numInputs;
		this.learningRate = learningRate;
		weights = new Double[numInputs+1];
		initializeWeights();
	}
	
	private Double logisticFunction(Double x) 	{
		return 1.0/(1.0+Math.exp(-x));
	}
	
	private void initializeWeights() {		
		for (int i = 0; i < numInputs+1; i++) {
			weights[i] = Math.random() * 0.02 - 0.01;
		}
	}
	
	public void train(int trueLabel, Double[] input) {		
		Double output = weights[0];
		for(int i=0;i<numInputs;i++) {
			output += weights[i+1]*input[i];			
		}
		Double y = logisticFunction(output);
		weights[0]=weights[0]+learningRate*(trueLabel-y);
		for(int i=0;i<numInputs;i++) {
			weights[i+1]=weights[i+1]+learningRate*(trueLabel-y)*input[i]; 
		}
	}
	
	public int test(Double[] input)
	{
		Double output = weights[0];
		for(int i=0;i<numInputs;i++) {
			output += weights[i+1]*input[i];			
		}
		Double y = logisticFunction(output);
		if (y>0.5) return 1;
		return 0;
	}
}
