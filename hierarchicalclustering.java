import java.util.*;

public class HericalClustering {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Input dimention of vectors:");
		int M = sc.nextInt();
		System.out.println("Input number of vectors:");
		int N =sc.nextInt();
		List[] clusterList=new ArrayList[N];
		  for (int i = 0; i < N; i++){
			  clusterList[i] = new ArrayList();
	      }
		  
		// read in N vectors of dimension M
		double[][] vector = new double[N][M];
		double[][] vectorM = new double[N][M+1];
		for (int i = 0; i < N; i++) {
			vectorM[i][M]=0;
			//System.out.println("Input information of NO." + i +" vectors:");
		  for (int j = 0; j < M; j++){
			  System.out.println("Input NO." + (j+1) + " dimention of NO." + (i+1) + " vectors:");
			vector[i][j] = sc.nextDouble();	
			vectorM[i][j]=vector[i][j];
		  }			
		}
		
	double INFINITY = Double.POSITIVE_INFINITY;
	double[][] d = new double[N][N];
	int[] dmin = new int[N];
	double D=0;	
	String T;
	String T1;
	String T2;
	double v1=0;
	double v2=0;
	double v=0;
	int n1=0;
	int n2=0;
	int n=0;
	
	//initial clusterList
	for (int i=0; i<N; i++){
	T="";
		for (int j = 0; j < M; j++){
		 T+=vector[i][j]+" ";
		 clusterList[i].add(vector[i][j]);
	 }
	 T="("+T+")";
	 clusterList[i].add(1);
	clusterList[i].add(T);
	}
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
	    
			if (i == j) d[i][j] = INFINITY;
	    else {
	    	D=0;
		    for(int z = 0; z < M; z++)
		    	
	     	   D+=(vector[i][z]-vector[j][z])*(vector[i][z]-vector[j][z]);
		    d[i][j] = Math.sqrt(D);
	        }
	   if (d[i][j] < d[i][dmin[i]]) dmin[i] = j;
	   }
	}


	
	for (int l = 0; l < N-1; l++) {
		// find closest pair of clusters (i1, i2)
		T="";
		T1="";
		T2="";
		int i1 = 0;
		for (int i = 0; i < N; i++)
			if (d[i][dmin[i]] < d[i1][dmin[i1]]) i1 = i;
		int i2 = dmin[i1];
		vectorM[i2][M]=1;
		
		//record and update cluster
		T1=(String) clusterList[i1].get(M+1);
		T2=(String) clusterList[i2].get(M+1);
		T="("+T1+T2+")";
		clusterList[i1].set(M+1,T);
		for (int m=0;m<M;m++){
			 v1=(double) clusterList[i1].get(m);
		     v2=(double) clusterList[i2].get(m);
	 	     v=(v1+v2)/2;
			clusterList[i1].set(m, v);			
		}
		 n1=(int) clusterList[i1].get(M);
		 n2=(int) clusterList[i2].get(M);
		 n=n1+n2;
		 clusterList[i1].set(M, n);
		
		//update distance Matrix
		for (int j = 0; j < M; j++) {
			vectorM[i1][j]=((double) clusterList[i1].get(j))/((int) clusterList[i1].get(M));
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
	
				if (i == j) d[i][j] = INFINITY;
		    else {
		    	D=0;
			    for(int z = 0; z < M; z++)
		     	   D+=(vectorM[i][z]-vectorM[j][z])*(vectorM[i][z]-vectorM[j][z]);
			    d[i][j] = Math.sqrt(D);
		        }
		   }
		}
		// infinity-out old row i2 and column i2
		for (int i = 0; i < N; i++)
		{
		if (vectorM[i][M]==1){
			for (int j = 0; j < N; j++)
			d[i][j] =d[j][i]=INFINITY;
		
		}
			
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				 if (d[i][j] < d[i][dmin[i]]) dmin[i] = j;
			}
		}
			
		// update dmin 
		for (int j = 0; j < N; j++) {
		if (d[i1][j] < d[i1][dmin[i1]]) dmin[i1] = j;
		} 
}
	
	//output result
	T=(String) clusterList[0].get(M+1);
	//n=(int) clusterList[0].get(M);
	System.out.println();
	System.out.println("The result of Hierarchical Clustering is:");
	System.out.println(T);	
	//System.out.println(n);
}
}
