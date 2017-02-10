
import java.util.Stack;

public class StableMatching implements StableMatchingInterface {
	
	 public int[] constructStableMatching (
			    int n,
			    int[][] menPrefs,
			    int[][] womenPrefs		    
			   ) {
		 
		 if(n==0)
			 return  new int[0];
		 	 
		 int [] bride = new int [n];
		 int [] ordreH = new int [n];
		 
		 for(int i = 0; i<n ; i++)
			 ordreH[i]=0;
				 
		 int[] houseband = new int [n];		 
		 int[][]prefModif = new int [n][n];
		 
		 for(int i = 0; i<n ; i++){
			 for(int j = 0; j<n ; j++){
				 int ind = womenPrefs[i][j]; //prefModif[l][k] donne le rang de lHomme k pour la femme l
				 prefModif[i][ind]=j;
			 }
		 }	 
		 for(int i = 0; i<n ; i++){
			 houseband[i]=-1; //le mari de la femme i est -1 qd elle n est pas mariee et il est j sinon
		 }	 
		 boolean [] engagedMen = new boolean [n]; //EngagedMen[i]=true if and only if i is engaged
		 Stack <Integer>NonengagedMen = new Stack<Integer>();
		 for(int i = 0; i<n ; i++){
			 NonengagedMen.push(i);
		 }		
		 
		 while(!NonengagedMen.isEmpty()){			 
			 int j = NonengagedMen.pop(); //le mec
			 
			 int w = menPrefs[j][ordreH[j]]; //la fille prefereee 
	
			 ordreH[j]++;
			 
					 if(houseband[w]==-1){ // elle est celib
						houseband[w]=j;
						 bride[j]=w;
						 engagedMen[j]=true;					
					 }			
					 
					 if(houseband[w]!=-1){ // elle est pas celib
						 int h=houseband[w];
						 
						 boolean split = prefModif[w][h]>prefModif[w][j];//j est prefere a h pour w								
						 
						 if(split) {			 
							 engagedMen[h]=false;	
							 NonengagedMen.push(h);
							 bride[j]=w;
							 houseband[w]=j;			
							 engagedMen[j]=true;
							 
						 }
					 }		 
					 if(!engagedMen[j]){ //il reste celib
						 NonengagedMen.push(j);	 						 
	 				 }
				 }   
		 return bride;
		 

	 }
	 
	 
}
