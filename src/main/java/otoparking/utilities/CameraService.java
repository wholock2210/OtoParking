package otoparking.utilities;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
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
                    org.opencv.core.Core.flip(frameForUI, frameForUI, 1);

                    int[] plate_bbox = apiPostImage.getPlate_bbox();
                    if(plate_bbox != null){
                        drawBBox(frameForUI, plate_bbox);
                    }

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

    private void drawBBox(Mat img, int[] bbox) {
        if (bbox.length != 4) return;

        Point p1 = new Point(bbox[0], bbox[1]);
        Point p2 = new Point(bbox[2], bbox[3]);

        Imgproc.rectangle(
            img,
            p1,
            p2,
            new Scalar(0, 255, 0), 
            2
        );

        Imgproc.putText(
            img,
            "PLATE",
            new Point(bbox[0], bbox[1] - 10),
            Imgproc.FONT_HERSHEY_SIMPLEX,
            0.6,
            new Scalar(255, 0, 0),
            2
        );
    }

    public void Stop(){
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
