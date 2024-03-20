package so;


public class Execute {
	public static void main(String[] args) {
		System.out.println("Seja Bem-vindo");
		Process p1 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 20);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p1, 0);
		
		Process p2 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 38);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p2, 0);
		
		Process p3 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 38);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p3, 0);
		
		Process p4 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 20);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p4, 0);
		
		SystemOperation.systemCall(SystemCallType.DELETE_PROCESS, p3, 0);
		
		//Process p5 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 40); // Estouro de memoria
		//SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p5, 0);
			
		//Só usar quando for o best fit ou o Worst
		Process p5 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 8); // O gerenciador adiciona no final da memoria para o best fit e adiciona no lugar de p2 se for o Worst fit
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p5, 0);
		
		SystemOperation.systemCall(SystemCallType.DELETE_PROCESS, p5, 0);
		
		Process p6 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 12); 
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p6, 0);
	}
}