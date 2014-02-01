public class RightShiftIdentityMatrix {

	int rl; // number of square matrix rank
	int n; //the number of right cyclic shift times(<<l)
	boolean[][] subMatrix;
	public RightShiftIdentityMatrix(int L, int N){
		this.rl=L;
		this.n=N;
		subMatrix = new boolean[rl][rl];
		
	}
	
	//configure I matrix;
	public void iMatrix(){
		for(int i=0;i<subMatrix.length; i++){
			for(int j=0; j<subMatrix.length; j++)
				if(i==j){
					subMatrix[i][j]=true;
				} else {
			subMatrix[i][j]=false;
		}
		}
		//printMatrix();
	}
	//print matrix
	public void printMatrix (){
		for(int i=0;i<subMatrix.length;i++){
		for(boolean x : subMatrix[i]){
			if(x==true)
				System.out.print(1+" ");
			else
				System.out.print(0+" ");
		}
		System.out.println();
		}
	}
    
	//right cyclic shift matrix
	public void rightCyclicShift(){
		int nShift=n;
		iMatrix();
		printMatrix();
		boolean mostRight;
		while(nShift!=0){
		for(int i=0; i<subMatrix.length; i++){
			mostRight=subMatrix[i][subMatrix.length-1];
			for(int j=subMatrix.length-1;j>0;j--){
				
				subMatrix[i][j]=subMatrix[i][j-1];
				}
				subMatrix[i][0]=mostRight;
			}
		nShift--;
		}	
		
		
		//printMatrix();
		}	
	}


