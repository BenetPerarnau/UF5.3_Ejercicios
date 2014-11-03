package filesgeneration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author mbella
 */
public class Operations {
    static Object[] opcionesMenu ={"1.Write data in text file", "2. Write primitive data in byte file", "3. Write object data in byte file", "4. Write data in random access file","5. Salir"};
    
//FILE
    private final static String textfile="stockTextFile.txt";
    private final static String bytefile="stockByteFile.dat";
    private final static String objectfile="stockObjectFile.dat";
    private final static String randomfile="stockRandomAccessFile.dat";
    
     public static void main(String[] args) {

       showMenu();
    }
     
     private static void showMenu(){
        Object seleccion = JOptionPane.showInputDialog(
                null,"Selecciona opcion","Selector de opciones",
                JOptionPane.QUESTION_MESSAGE,null,
                opcionesMenu, opcionesMenu[0]);
        if (seleccion!=null){
                defineSeleccion(seleccion.toString());
        }else{System.exit(0);}
    }
     
     private static void defineSeleccion(String seleccion) {
       
        if (seleccion.equals(opcionesMenu[0])){
            Path path = Paths.get(textfile);
            writeDataInFile(path, 0);
            showMenu();
        }else if (seleccion.equals(opcionesMenu[1])){
            Path path = Paths.get(bytefile);
            writeDataInFile(path, 1);
            showMenu();
        }else if (seleccion.equals(opcionesMenu[2])){
            Path path = Paths.get(objectfile);
            writeDataInFile(path, 2);
            showMenu();
        }else if (seleccion.equals(opcionesMenu[3])){
            Path path = Paths.get(randomfile);
            writeDataInFile(path, 3);
            showMenu();
        }else{
            System.exit(0);
        }
        
        
 }
     
     private static void writeDataInFile(Path path, int option){
       switch (option) {
            case 0:  writeTextFile(path);
                     break;
            case 1:  writeByteFile(path);
                     break;
            case 2:  writeObjectFile(path);
                     break;
            case 3:  writeRandomAccessFile(path);
                     break;
            case 4:  writeRandomAccessFile(path);
                     break;
       }
     }
     
     private static void writeTextFile(Path path){
         BufferedWriter bufferWriter=null;
        try{
           //apertura del stream. StandardOpenOption.CREATE->si el fichero no existe se crea
            bufferWriter=Files.newBufferedWriter(path, 
                    java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.CREATE);
            for (int i=0;i<Data.COD.length;i++){
                bufferWriter.write(Data.COD[i]+";"+Data.DESC[i]+";"+Data.STOCK[i]+";"+Data.PRICE[i]);
                bufferWriter.newLine();
            }
            System.out.println("Escritura fichero "+path.getFileName().toString()+ " finalizada");
            readTextFile(path);
            
        } catch (IOException ex) {
            System.err.println("I/O Error: "+ex);
        }finally{
            //Se cierra el stream y se liberan los recursos de sistema asociados a él.
             if (bufferWriter!=null) 
                 try {
                    bufferWriter.close();
                 } catch (IOException ex) {
                    System.err.println("I/O Error: "+ex);
                }
        }
     }
     
     private static void readTextFile (Path path){
     BufferedReader bufferedReader=null; 
        try {
            //apertura del stream. Los bytes del fichero se decodifican utilizando el CharSet: UTF_8:
            bufferedReader=Files.newBufferedReader(path, java.nio.charset.StandardCharsets.UTF_8);
            //lectura de lineas (thorws IOException):
            String linea;
            while ((linea=bufferedReader.readLine())!=null){
                    System.out.println(linea);
            }
        } catch (IOException ex) {
            System.err.println("I/O Error: "+ex);
        }finally{
            //Se cierra el stream y se liberan los recursos de sistema asociados a él.
            if (bufferedReader!=null) 
                 try {
                    bufferedReader.close();
                 } catch (IOException ex) {
                    System.err.println("I/O Error: "+ex);
                }
        }
     
     }
     
     private static void writeByteFile(Path path){
     DataOutputStream dataOut=null;
        try {
            //apertura del stream. StandardOpenOption.CREATE->si el fichero no existe se crea
            dataOut = new DataOutputStream(Files.newOutputStream(path, java.nio.file.StandardOpenOption.CREATE));
            for (int i=0; i<Data.COD.length;i++){
                //escritura de bytes (thorws IOException): 
                dataOut.writeUTF(Data.COD[i]);
                dataOut.writeUTF(Data.DESC[i]);
                dataOut.writeInt(Data.STOCK[i]);
                dataOut.writeDouble(Data.PRICE[i]);}
            System.out.println("Escritura fichero "+path.getFileName().toString()+ "finalizada");
            readByteFile(path);
        } catch (IOException ex) {
            System.err.println("Error I/O "+ex);
        }finally{
            //Se cierra el stream y se liberan los recursos de sistema asociados a él.
            if (dataOut!=null)
            try{
                dataOut.close();
            }catch (IOException ex) {
                System.err.println("Error I/O "+ex);
            } 
        }
     }
     
     private static void readByteFile (Path path){
         
         DataInputStream dataIn=null;
        try {
        //apertura del stream.
        dataIn= new DataInputStream(Files.newInputStream(path));
        try{
            while (true){
                System.out.println(dataIn.readUTF()+ " "
                        +dataIn.readUTF()+" "+dataIn.readInt()+" "+dataIn.readDouble());
            }
        }catch (EOFException ex){System.out.println("Fin de fichero detectado");}   
        
        } catch (IOException ex) {
            System.err.println("Error I/O "+ex);
        }finally{
            //Se cierra el stream y se liberan los recursos de sistema asociados a él.
            if (dataIn!=null)
            try{
                dataIn.close();
            }catch (IOException ex) {
                System.err.println("Error I/O "+ex);
            } 
        }
     
     }
     
     private static void writeObjectFile(Path path){
         
        ObjectOutputStream dataOut=null;
        try {
            //apertura del stream. StandardOpenOption.CREATE->si el fichero no existe se crea
            dataOut = new ObjectOutputStream(Files.newOutputStream(path, java.nio.file.StandardOpenOption.CREATE));
            for (int i=0; i<Data.COD.length;i++){
                Product product = new Product (Data.COD[i], Data.DESC[i], Data.STOCK[i], Data.PRICE[i]);
                //escritura de objetos (thorws IOException): 
                dataOut.writeObject(product);
            }
         System.out.println("Escritura fichero "+path.getFileName().toString()+ "finalizada");
         readObjectFile(path);
        }catch (IOException ex) {
            System.err.println("Error I/O "+ex);
        }finally{
            //Se cierra el stream y se liberan los recursos de sistema asociados a él.
            if (dataOut!=null)
            try{
                dataOut.close();
            }catch (IOException ex) {
                System.err.println("Error I/O "+ex);
            } 
        }
     }
     private static void readObjectFile(Path path){
     ObjectInputStream dataIn=null;
        try {
        //apertura del stream
        dataIn= new ObjectInputStream(Files.newInputStream(path));
        try{
            while (true){
                //lectura de objetos (thorws ClassNotFoundException):
                Product product = (Product) dataIn.readObject();
                System.out.println(product.toString());
            }
        }catch (EOFException ex){System.out.println("Fin de fichero detectado");}
        }catch (IOException ex) {
            System.err.println("Error I/O "+ex);
        }catch (ClassNotFoundException ex) {
            System.err.println("Error clase no encontrada "+ex);
        }finally{
             //Se cierra el stream y se liberan los recursos de sistema asociados a él.
            if (dataIn!=null)
            try{
                dataIn.close();
            }catch (IOException ex) {
                System.err.println("Error I/O "+ex);
            } 
        }
     }
    private static void writeRandomAccessFile(Path path){
        
        SeekableByteChannel sbc=null;
        try {
            sbc = Files.newByteChannel(path, 
                    java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.WRITE);
            ByteBuffer byteBuffer =ByteBuffer.allocate(92);

            for (int i=0; i<Data.COD.length;i++){
                byteBuffer.clear();
                //COD
                StringBuffer stringBuffer = new StringBuffer(Data.COD[i]);
                stringBuffer.setLength(20); //max 20 caracteres 
                String string = stringBuffer.toString();
                char[] arrayChars = string.toCharArray();
                for (char c : arrayChars){
                    byteBuffer.putChar(c);
                } //40 bytes
                //DESC
                stringBuffer = new StringBuffer(Data.DESC[i]);
                stringBuffer.setLength(20); //max 20 caracteres 
                string = stringBuffer.toString();
                arrayChars = string.toCharArray();
                for (char c : arrayChars){
                    byteBuffer.putChar(c);
                } //40 bytes
                
                byteBuffer.putInt(Data.STOCK[i]); //4 bytes
                byteBuffer.putDouble(Data.PRICE[i]); //8 bytes
                
                byteBuffer.flip(); //The limit is set to the current position and then the position is set to zero
                
                sbc.write(byteBuffer);
            }
            System.out.println("Escritura fichero "+path.getFileName().toString()+ "finalizada");
            readRandomAccessFile(path);
            
        } catch (IOException ex) {
            System.err.println("Error I/O "+ex);
        } finally{
            if (sbc!=null)
                try {
                    sbc.close();
            } catch (IOException ex) {
                System.err.println("Error I/O "+ex);
            }
        }
    }
    
    private static void readRandomAccessFile(Path path){
        SeekableByteChannel sbc=null;
        try {
            sbc = Files.newByteChannel(path, java.nio.file.StandardOpenOption.READ);
            ByteBuffer byteBuffer= ByteBuffer.allocate(92);    
            
            int posicion =0; 
            int stock;
            double price;
            char[] charSequence = new char[20];
            String cod, desc;

            while (sbc.position()<sbc.size()){
                byteBuffer.clear();
                sbc.read(byteBuffer);
                byteBuffer.rewind(); //The position is set to zero and the mark is discarded
                
                for (int i=0; i<charSequence.length;i++){
                    charSequence[i]=byteBuffer.getChar();
                }
                cod = new String(charSequence);
                for (int i=0; i<charSequence.length;i++){
                    charSequence[i]=byteBuffer.getChar();
                }
                desc=new String(charSequence);

                stock = byteBuffer.getInt();
                price = byteBuffer.getDouble();
                
                System.out.println(cod+" "+desc+" "+stock+" "+price);
                
                posicion = posicion +byteBuffer.capacity();
                sbc.position(posicion);
            }
            
        } catch (IOException ex) {
            System.err.println("Error I/O "+ex);
        } finally{
            if (sbc!=null)
                try {
                    sbc.close();
            } catch (IOException ex) {
                System.err.println("Error I/O "+ex);
            }
        }
    }
}
