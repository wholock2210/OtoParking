package otoparking.utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.imageio.ImageIO;

public class APIPostImage {

    private final BlockingQueue<BufferedImage> queue = new ArrayBlockingQueue<>(2);

    private boolean running = true;

    private final String URL_API = "http://localhost:8000/detect-plate";
    private final HttpClient client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1) // CỰC KỲ QUAN TRỌNG
        .build();

    public APIPostImage(){
        new Thread(this::Start).start();
    }

    public void Submit(BufferedImage image){
        if(queue.remainingCapacity() == 0){
            queue.poll();
        }
        queue.offer(image);
    }

    private void Start(){
        while (running) {
            try {
                BufferedImage image = queue.take();

                SendToAPI(image);

                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void Stop(){
        running = false;
    }



    private void SendToAPI(BufferedImage image) {
        try {
            byte[] imageBytes = BufferedImageToBytes(image);
            System.out.println("JPEG size = " + imageBytes.length);

            String boundary = "----JavaBoundary" + System.currentTimeMillis();
            String CRLF = "\r\n";

            ByteArrayOutputStream body = new ByteArrayOutputStream();

            body.write(("--" + boundary + CRLF).getBytes());
            body.write(("Content-Disposition: form-data; name=\"file\"; filename=\"frame.jpg\"" + CRLF).getBytes());
            body.write(("Content-Type: image/jpeg" + CRLF + CRLF).getBytes());

            body.write(imageBytes);

            body.write(CRLF.getBytes());
            body.write(("--" + boundary + "--" + CRLF).getBytes());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API))
                    .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                    .POST(HttpRequest.BodyPublishers.ofByteArray(body.toByteArray()))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());
            System.out.println("API response: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private byte[] BufferedImageToBytes(BufferedImage image) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
    
    
}
