import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Worker implements Serializable {
 
  /**
   * The extent
   */
  private static TreeMap<String, Worker> extent = new TreeMap<>();
  private static final long serialVersionUID = 6529685098267757690L;


  private String name = "";
  private String surname = "";
  @Nullable
  private String secondName = "NaN";
  private Date dateOfCreation;


  /**
   * this is a constructor of a Worker class
   * 
   * @param name    name of a worker
   * @param surname surname of a worker
   */
  public Worker(String name, String surname) {
    // Adding to extent
    this.surname = surname;
    this.name = name;
    this.dateOfCreation = new Date();
    if(!extent.isEmpty()){
      Worker.incrementToLength();
    }
    String id = String.valueOf(Mas1.ID_COUNTER.getAndIncrement());
    addWorker(id, this);
  }

  /**
   * getter
   * 
   * @return String name
   */
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    String toReturn = " NAME: " + name + " SURNAME: " + surname + " DATE OF ADDING: " + dateOfCreation.toString();
    return toReturn;
  }

  /**
   * Prints all workers in extent
   */
  public static void printExtent() {
    String toPrint = "";
    for (Entry<String, Worker> idAndWorker : extent.entrySet()) {
      var id = idAndWorker.getKey();
      var worker = idAndWorker.getValue();

      toPrint = toPrint + "ID: " + id + " " + worker.toString() + "\n";
    }
    System.out.println(toPrint);
  }

  /**
   * Adds worker to extent
   *
   * @param worker new worker
   */
  public static void addWorker(String id, Worker worker) {
    extent.put(id, worker);
  }

  /**
   * Removes worker from extent
   *
   * @param worker one of the workers
   */
  public static void removeById(String id) {
    extent.remove(id);
  }

  /**
   * increments global counter to the heighest of the id's
   */
  public static void incrementToLength(){
    Mas1.ID_COUNTER.set(Long.parseLong(extent.lastKey()));
  }

  /**
   * serializes data to a file notdb.dat
   * 
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void serializeDataOut() throws FileNotFoundException, IOException {
    FileOutputStream out = new FileOutputStream("notdb.dat");
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(extent);
    oos.close();
    out.close();
  }

  /**
   * deserializes data from a file notdb.dat
   * 
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public static void serializeDataIn() throws FileNotFoundException, IOException, ClassNotFoundException {
    FileInputStream in = new FileInputStream("notdb.dat");
    ObjectInputStream ois = new ObjectInputStream(in);
    TreeMap<String, Worker> fromDB = (TreeMap<String, Worker>) ois.readObject();
    extent = fromDB;
    ois.close();
    in.close();
  }
}
