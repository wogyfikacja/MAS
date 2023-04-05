import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Mas1
 */
public class Mas1 {

  /**
   * Counter for the Id of workers
   */
  static AtomicLong ID_COUNTER = new AtomicLong();

  public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
    //Worker.serializeDataIn();
    BufferedReader bfn = new BufferedReader(new InputStreamReader(System.in));
      int mode = 10;

      while (true) {
        System.out.println("Write 0 to end, 1 to add, 2 to delete worker: ");
        mode = bfn.read();
        if(mode == 48){
          break;
        }else if(mode == 49){
          String name = "";
          String surname = "";

          System.out.println("Name: ");
          bfn.readLine();
          name = bfn.readLine();
          System.out.println("Surname: ");
          surname = bfn.readLine();
          Worker addedWorker = new Worker(name, surname);
        }else if(mode == 50){
          Worker.printExtent();
          System.out.println("Delete worker with id: ");
          bfn.readLine();
          String id = bfn.readLine();
          Worker.removeById(id);
        }else{
          System.out.println("try again");
        }
        Worker.printExtent();
      }
    Worker.serializeDataOut();
  }
}
