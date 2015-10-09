import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by hongbin on 15-1-29.
 */
public class LettersCommand {

    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 8);
    public static void main(String[] args) throws IOException, InterruptedException {
        String command = "stat";
        int sum = 0;
        for(int i=0;i<1000;i ++) {
            String contents = execute("192.168.144.110", 2181, command);
            System.out.println(contents);
            if(contents != null){
                sum++;
            }
        }
        System.out.print(sum);
    }

    public static String execute(final String host, final int port, final String command) throws IOException, InterruptedException {
        Socket socket = new Socket();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            byteBuffer.clear();
            socket.connect(new InetSocketAddress(host, port));
            outputStream = socket.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
            inputStream = socket.getInputStream();
            int index = -1;
            byte[] buffer = new byte[1024];
            while((index = inputStream.read(buffer))!=-1){
                System.out.println();
                byteBuffer.put(buffer,0,index);
            }
            ByteBuffer bb = (ByteBuffer)byteBuffer.flip();
            byte[] target = new byte[bb.limit()];
            bb.get(target);
            return new String(target);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            socket.close();
        }
    }
}