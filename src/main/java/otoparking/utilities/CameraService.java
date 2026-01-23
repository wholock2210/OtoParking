package otoparking.utilities;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import otoparking.ui.Panel.PnCamera;

import java.awt.image.BufferedImage;
import org.opencv.imgproc.Imgproc;


public class CameraService {

    private VideoCapture camera;
    private boolean running;
    private APIPostImage apiPostImage = new APIPostImage();

    private Thread cameraThread;

    public void Start(PnCamera panel){

        if(running)
            return;

        camera = new VideoCapture(0);

        if(!camera.isOpened()){
            System.out.print("can't open camera");
            return;
        }
        running = true;
        apiPostImage.Start();

        cameraThread = new Thread(() ->{
            Mat frame = new Mat();
            
            while (running) {
                if(camera.read(frame)){
                    Mat frameForUI = frame.clone();
                    Mat frameForAPI = frame.clone();
                    Imgproc.cvtColor(frameForUI, frameForUI, Imgproc.COLOR_BGR2RGB);
                    //org.opencv.core.Core.flip(frame, frame, 1);

                    BufferedImage imgForUI = MatToBufferImage(frameForUI);
                    panel.updateImage(imgForUI);

                    BufferedImage imgForApi = MatToBufferImage(frameForAPI);
                    apiPostImage.Submit(imgForApi);

                }
            }
            camera.release();
        });
        cameraThread.start();
    }

    public void Stop(){

        System.out.println("cameraservice bi dung");
        running = false;
        apiPostImage.Stop();

        if(cameraThread != null){
            cameraThread.interrupt();
        }
    }

    private BufferedImage MatToBufferImage(Mat mat){
        int type = BufferedImage.TYPE_3BYTE_BGR;
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0,0,buffer);

        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), buffer);

        return image;
    }
    
    public String getLicencePlate(){
        return apiPostImage.getLicencePlate();
    }
}
