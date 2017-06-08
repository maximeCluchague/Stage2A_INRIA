package Client_Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

class LogFileAnalyse extends Thread {
	Socket socket;
	BufferedReader entree;
	PrintStream sortie;

	LogFileAnalyse(Socket socket) {
		this.socket = socket;
		try {
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sortie = new PrintStream(socket.getOutputStream());
			this.start();
		}
		catch(IOException exc) {
			try {
				socket.close();
			}
			catch(IOException e){}
		}
	}

	/*
	 * 
	 */
	public void run() {
		String texte;
		String fileLog = "";
		int compteur = 0;

		try {
			System.out.println("================<OUTPUT>==============");
			while(!(texte = entree.readLine()).equals("<END_FILE>")) {
				sortie.println(texte);
				fileLog += texte+"\n"; // On récupère le contenue du fichier log dans le string fileLog
			}
			/*
			 * Ici on pourra parser fileLog pour enregistrer les datas dans une base SQL
			 */
			
			System.out.print(fileLog);
			System.out.println("================<OUTPUT>==============");
			sortie.close();
			entree.close();
			socket.close();
		}
		catch(IOException e) {}
	}
}

class Serveur {
	public static void main(String[] arg) {
		ServerSocket standardiste;
		Socket socket;

		try {
			standardiste = new ServerSocket(3018);
			System.out.println("le serveur est prêt !\n");
			while(true) {
				socket = standardiste.accept();
	 			new LogFileAnalyse(socket);
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}
	}
}

