import java.util.Random;

public class JSONGenerator {
	public static void main(String[] args) {
		Random rand = new Random();
		int random;
		int id = Integer.parseInt(args[0]);					//unique identifier
		String type; 				//can be cds, exon, or gene
		int start;					//the starting location of the feature on the gene (0,5000000)
		int stop;					//the ending locaiton of the feature on the gene, must be >= start (0,5000000)
		String strand;				//+, -, or und
		int chromosome;				//(1,5)
		String featureName; 		//a random string of 8 alphanumerics

		//need 5 chromosomes, each with 5000, 50000, or 500000 features (depending on current setting)
		for(int i = 0; i < 5; i++) {	//one loop for each chromosome
			chromosome = i+1;
			for(int j = 0; j < 200; j++) {
				
				String result = "";

				//id
				id++;
				
				//type
				random = rand.nextInt(3);
				if(random == 0) {
					type = "cds";
				} else if(random == 1) {
					type = "exon";
				} else {
					type = "gene";
				}

				//start
				random = rand.nextInt(5000001);
				start = random;
				
				//stop
				random = rand.nextInt(5000001 - start);
				stop = start + random;

				//strand
				random = rand.nextInt(3);
				if(random == 0) {
					strand = "+";
				} else if(random == 1) {
					strand = "-";
				} else {
					strand = "und";
				}

				result += "{\"create\" : { \"_id\" : \"" + id + "\" } }\\n\n";	//command

				result += "{ \"id\" : " + id + ",";								//first line of object, "id"
				result += "\"type_name\" : \"" + type + "\",";					//second line of object, "type_name"
				result += "\"start\" : " + start + ",";							//thrid line of object, "type_name"
				result += "\"stop\" : " + stop + ",";							//fourth line of object, "type_name"
				result += "\"strand\" : \"" + strand + "\",";					//fifth line of object, "type_name"
				result += "\"chromosome\" : " + chromosome + ",";						//sixth line of object, "type_name"

				result += "\"feature_name\" : { ";								//Beginning of feature_name list

				//name
				random = rand.nextInt(5) + 1; 			//number between 1 and 5
				for(int k = 0; k < random; k++) {
					
					featureName = "";

					for(int l = 0; l < 8; l++) {
						int randomChar = rand.nextInt(36) + 48;		//number of Uppercase letters + number of single-digit numbers (alphanumerics - lowercase) (48 == 0)
						if(randomChar > 57) {
							randomChar += 7;
						}
						char letter = (char) randomChar;
						featureName += letter;
					}

					if(k != random-1) {
						result += "\"name_" + (k+1) + "\" : \"" + featureName + "\",";
					} else {
						result += "\"name_" + (k+1) + "\" : \"" + featureName + "\"";
					}
				}

				result += "}}\n";													//end of feature_name list followed by end of object
				System.out.print(result);
			}
		}

	}
}
