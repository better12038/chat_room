package MutiThreadVersion.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteDataToServerThread extends Thread{
    private final Socket client;

    public WriteDataToServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            //获取客户端的输出流
            OutputStream clientOutput = client.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);

            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("请输入消息：");
                String message = scanner.nextLine();

                //往服务器发送数据
                writer.write(message+"\n");
                writer.flush();

                //客户端要关闭
                if(message.equals("bye")){
                    client.close();
                    break;
                }
            }

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
