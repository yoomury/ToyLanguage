package samplee.Repository;

import samplee.Domain.ProgramState.PrgState;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;


public class MyRepository implements Repository {
    private List<PrgState> prgStates;


    public MyRepository(List<PrgState> prgStates) {
        this.prgStates = prgStates;
        try {
            Files.deleteIfExists(FileSystems.getDefault().getPath("prgState.txt"));
        } catch (IOException e) {
            System.out.println("No old prgState.txt file");
        }
    }

    public MyRepository() {
        this.prgStates = null;
    }

    public List<PrgState> getPrgStates() {
        return prgStates;
    }

    public void setPrgStates(List<PrgState> states) {
       prgStates=states;

    }


    @Override
    public void logPrgState() {
        try {
            FileChannel fc = new RandomAccessFile("prgState.txt", "rw").getChannel();
            fc.position(fc.size());
            for(PrgState prg:prgStates){fc.write(ByteBuffer.wrap(prg.toString().getBytes()));}
        } catch (IOException  e) {
           System.out.println("no such file");
            }
    }


    @Override
    public void serializePrgState() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("prgState.ser"));
            out.writeObject(prgStates);
        } catch (IOException e) {
            System.err.println("Error serialization: " + e.getMessage());
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch(IOException e)  {
                    System.err.println("Error " + e.getMessage());
                }
        }
    }


    @Override
    public void deserializePrgState() throws IOException {
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream("prgState.ser"));
           this.prgStates = (List<PrgState>) in.readObject();
            System.out.println("all okkk");
        } catch (ClassNotFoundException e) {
            System.err.println("Error deserialization, class not found " + e.getMessage());
        } finally{
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Error " + e.getMessage());
                }
        }
    }
}


