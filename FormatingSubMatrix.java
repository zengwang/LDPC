public interface FormatingSubMatrix {
//for QC-LDPC Encoder, formating the following A,B,C,D,E,T matrix
	void submatrixA(int columnN_k, int rowK_g);
	void submatrixB(int columnG, int rowK_g);
	void submatrixC(int columnN_k, int rowG);
	void submatrixD(int columnG, int rowG);
	void submatrixE(int columnK_g, int rowG );
	void submatrixT(int columnK_g, int rowK_g); //square matrix
}
