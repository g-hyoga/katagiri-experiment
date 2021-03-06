import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q9 {
	public static List<Double> step(List<List<Double>> irises, List<Double> weight, Double r){
		List<Double> newWeight = weight;
		for(int i = 0; i < irises.size(); i++){
			double g = Util.dot(newWeight, irises.get(i));
    		if (g > 0 && i > 49) {
    			List<Double> tmp = irises.get(i).stream().map(e -> e*r).collect(Collectors.toList());
    			newWeight = Util.sub(newWeight, tmp);
    		} else if (g < 0 && i < 50){
    			List<Double> tmp = irises.get(i).stream().map(e -> e*r).collect(Collectors.toList());
    			newWeight = Util.add(newWeight, tmp);
    		}
		}
		return newWeight;
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = Util.makeBR("./data/iris.txt");

        // make iris array
        double[][][] sample;
        sample = Util.makeSample(br);
        br.close();

        // array to list
        List<List<Double>> setosas = Util.convertSampleToExtendedList(sample, 0);
        List<List<Double>> versicolors = Util.convertSampleToExtendedList(sample,1);
        List<List<Double>> versinicas = Util.convertSampleToExtendedList(sample, 2);

        double r = 1.0;
        List<List<Double>> irises = versicolors;
        irises.addAll(versinicas);
        
        List<Integer> foo = Arrays.asList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        List<String> hoge = Arrays.asList("", "", "", "", "", "", "", "", "", "", "");
        for (int j = 0; j < hoge.size(); j++){
        	List<Double> weight = Arrays.asList(0.33, 0.23, -0.38, -0.05, 0.47);
        	for (int i = 0; i < foo.get(j); i++){
            	weight = step(irises, weight, r);
            }
            List<Double> newWeight = weight;

            List<Double> out = irises.stream().map(x -> Util.dot(newWeight, x)).collect(Collectors.toList());
            List<Double> result = out.stream().map(x -> {
                double tmp = 0.0;
                if (x > 0){
                    tmp = 1.0;
                } else if (x <= 0){
                    tmp = 2.0;
                }
                return tmp;
            }).collect(Collectors.toList());

            double count = 0.0;
            for (int i = 0; i < result.size(); i++){
                if(result.get(i) == 1.0 && i < 50){
                    count++;
                } else if (result.get(i) == 2.0 && i >= 50) {
                	count++;
                }
            }
        	hoge.set(j, count/result.size()*100 + "%");
        }
        
        System.out.println(hoge);
    }
}
