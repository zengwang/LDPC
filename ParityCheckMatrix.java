public class ParityCheckMatrixH  implements FormatingSubMatrix {
int n; //Number of variable node; also  number of codewords
int k; //number of check node or number of parity
int g;
int l;
boolean[][] A;
boolean[][] B;
boolean[][] C;
boolean[][] D;
boolean[][] E;
boolean[][] T;
boolean[] As;
boolean[] TAs;
boolean[] ETAs;
boolean[] Cs;
boolean[] ETAs_Cs;
boolean[][] PH;
boolean[] s; // number of information bits
boolean[] p1;
boolean[] p2;
boolean[] Bp1;
	public ParityCheckMatrixH(int numberOfVariableNode, int numberOfCheckNode, int g, int l){
		this.n=numberOfVariableNode;
		this.k=numberOfCheckNode;
		this.g=g;
		this.l=l;
		As= new boolean[k-g];
		TAs = new boolean[k-g];
		ETAs=new boolean[g];
		Cs = new boolean[g];
		ETAs_Cs= new boolean[g];
		PH = new boolean[g][g];
		s = new boolean[n-k];
		p1= new boolean[g];
		p2= new boolean[k-g];
		Bp1= new boolean[k-g];
	}
	
	
	void initializationOfMatrix(){
		submatrixA(n-k,k-g);
		submatrixB(g,k-g);
		submatrixC(n-k,g);
		submatrixD(g,g);
		submatrixE(k-g,g);
		submatrixT(k-g,k-g);
		
	}

	boolean[] matrixMulti(boolean[][] x, boolean[] y){
		boolean sum;
		boolean[] xy= new boolean[y.length];
		for(int i=0; i<x.length; i++){
			sum=x[i][0]&y[0];
			for(int j=1;j<x[i].length; j++){
				sum=sum^(y[j] & x[i][j]);
			}
			xy[i]=sum;
		}
		return xy;
	}
	
	boolean[][] matrixMulti(boolean[][] x, boolean[][] y){
		boolean[][] xy= new boolean [x.length][x[0].length];
		boolean[] tmpRow=new boolean[x[0].length];
		for(int i=0; i<x.length; i++){
			tmpRow=matrixMulti(x,y[i]);
			int j=0;
			for(boolean element : tmpRow){
				xy[i][j]=element;
				j++;
			}	
		}
		return xy;
		
	}
	
	boolean[] matrixAdd(boolean[]x, boolean[]y){
		boolean[] xy=  new boolean[x.length];
		for(int i=1; i<x.length; i++){
			xy[i]=x[i]^y[i];
		}
		return xy;
	}
	
	boolean[][] matrixAdd(boolean[][] x, boolean[][] y){
		boolean[][] xy = new boolean[x.length][x[0].length];
		for(int i=0; i<x.length; i++)
			for(int j=0; j<x[0].length; j++){
				xy[i][j]=x[i][j]^y[i][j];
			}
		return xy;
	}
 
	void phMatrix(){
		PH=matrixMulti(inverse(T),B);
		PH=matrixMulti(E,PH);
		PH=matrixAdd(PH,D);
	}
	
	void parity1Matrix(boolean[] inforbits){
		As= matrixMulti(A,inforbits);
		TAs= matrixMulti(inverse(T), As);
		ETAs=matrixMulti(E, TAs );
		Cs=matrixMulti(C,inforbits);
		p1=matrixAdd(Cs,ETAs);
		p1=matrixMulti(inverse(PH),p1);
		
	}
	
	void parity2Matrix(){
		Bp1=matrixMulti(B,p1);
		p2=matrixAdd(As,Bp1);
		p2=matrixMulti(inverse(T),p2);
	}
	
	boolean[][] inverse(boolean[][] w){
		for(int i=0; i<w.length; i++)
			for(int j=0;j<w[i].length; j++){
				boolean wij=w[i][j];
				boolean wji=w[j][i];
				w[i][j]=wji;
				w[j][i]=wij;
		}
		return w;
	}
	
	@Override
	public void submatrixA(int columnN_k, int rowK_g) {
		// TODO Auto-generated method stub
		A = new boolean[columnN_k][rowK_g];
		
		
	}

	@Override
	public void submatrixB(int columnG, int rowK_g) {
		// TODO Auto-generated method stub
		B= new boolean[columnG][rowK_g];
	}

	@Override
	public void submatrixC(int columnN_k, int rowG) {
		// TODO Auto-generated method stub
		C= new boolean[columnN_k][rowG];
	}

	@Override
	public void submatrixD(int columnG, int rowG) {
		// TODO Auto-generated method stub
		D = new boolean[columnG][rowG];
		
	}

	@Override
	public void submatrixE(int columnK_g, int rowG) {
		// TODO Auto-generated method stub
		E= new boolean[columnK_g][rowG];
		
	}

	@Override
	public void submatrixT(int columnK_g, int rowK_g) {
		// TODO Auto-generated method stub
		T=new boolean[columnK_g][rowK_g];
		
	}
	
}
