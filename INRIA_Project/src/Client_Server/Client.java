package Client_Server;




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

class Client {	
	public static void main(String[] arg) {

		BufferedReader lecteurFichier;
		BufferedReader entree;
		PrintStream sortie;
		String ligne;
		Socket socket;
		try {
			socket = new Socket("localhost", 3013);
			lecteurFichier = new BufferedReader(new FileReader("/run/netsop/u/sop-nas2a/vol/home_hephaistos/mcluchag/Desktop/Stage2A_INRIA/Documentation/Diagramme_de_sequence"));
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sortie = new PrintStream(socket.getOutputStream());
			while ((ligne = lecteurFichier.readLine()) != null) {
				sortie.println(ligne);
			}
			sortie.println("<END_FILE>");
			
			//System.out.println(entree.readLine());
		        sortie.close();
		        entree.close();
			socket.close();
		}
		catch(FileNotFoundException exc) {
			System.out.println("Fichier introuvable");
		}
		catch(UnknownHostException exc) {
			System.out.println("Destinataire inconnu");
		}
		catch(IOException exc) {
			System.out.println("Probleme d'entree-sortie");
		}
	}
}