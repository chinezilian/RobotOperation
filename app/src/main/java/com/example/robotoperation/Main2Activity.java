package com.example.robotoperation;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import org.ros.address.InetAddressFactory;
import org.ros.android.RosActivity;
import org.ros.android.view.RosTextView;
import org.ros.android.view.camera.RosCameraPreviewView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

public class Main2Activity extends RosActivity  {

   /* private int cameraId = 0;
    private RosCameraPreviewView rosCameraPreviewView;
    private Handler handy = new Handler();*/

    private NavSatFixPublisher fix_pub;
    private ImuPublisher imu_pub;

    private LocationManager mLocationManager;
    private SensorManager mSensorManager;


    public Main2Activity() {
        super("RobotOperation", "RobotOperation");
    }



    @Override
    protected void onPause()
    {
        super.onPause();
    }
/*
    Runnable sizeCheckRunnable = new Runnable() {
        @Override
        public void run() {
            if (rosCameraPreviewView.getHeight()== -1 || rosCameraPreviewView.getWidth()== -1) {
                handy.postDelayed(this, 100);
            } else {
                Camera camera = Camera.open(cameraId);
                rosCameraPreviewView.setCamera(camera);
            }
        }
    };

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        //rosCameraPreviewView = (RosCameraPreviewView) findViewById(R.id.ros_camera_preview_view);

        mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        mSensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

	  /*SubMenu subPreview = menu.addSubMenu("Color settings");
      subPreview.add(1,VIEW_MODE_RGBA,0,"RGB Color").setChecked(true);
      subPreview.add(1,VIEW_MODE_GRAY,0,"Grayscale");
      subPreview.add(1,VIEW_MODE_CANNY,0,"Canny edges");
      subPreview.setGroupCheckable(1, true, true);

      SubMenu subCompression = menu.addSubMenu("Compression");
//      subCompression.add(2,IMAGE_TRANSPORT_COMPRESSION_NONE,0,"None");
      subCompression.add(2,IMAGE_TRANSPORT_COMPRESSION_PNG,0,"Png");

      SubMenu subCompressionRate = subCompression.addSubMenu(2,IMAGE_TRANSPORT_COMPRESSION_JPEG,0,"Jpeg");
      subCompression.setGroupCheckable(2, true, true);
      subCompressionRate.setHeaderTitle("Compression quality");
      subCompressionRate.getItem().setChecked(true);
      subCompressionRate.add(3,50,0,"50");
      subCompressionRate.add(3,60,0,"60");
      subCompressionRate.add(3,70,0,"70");
      subCompressionRate.add(3,80,0,"80").setChecked(true);
      subCompressionRate.add(3,90,0,"90");
      subCompressionRate.add(3,100,0,"100");
      subCompressionRate.setGroupCheckable(3, true, true);*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
      /*if(item.getGroupId() == 1)
      {
    	  viewMode = item.getItemId();
    	  item.setChecked(true);
      }

      if(item.getGroupId() == 2)
      {
    	  imageCompression = item.getItemId();
    	  item.setChecked(true);
      }

      if(item.getGroupId() == 3)
      {
    	  imageCompressionQuality = item.getItemId();
    	  item.setChecked(true);
      }*/
        return true;
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
  /*      NodeConfiguration nodeConfiguration =
                NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(rosCameraPreviewView, nodeConfiguration);
        handy.post(sizeCheckRunnable);
*/
        NodeConfiguration nodeConfiguration2 = NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress());
        nodeConfiguration2.setMasterUri(getMasterUri());
        nodeConfiguration2.setNodeName("android_sensors_driver_nav_sat_fix");
        this.fix_pub = new NavSatFixPublisher(mLocationManager);
        nodeMainExecutor.execute(this.fix_pub, nodeConfiguration2);

        NodeConfiguration nodeConfiguration3 = NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress());
        nodeConfiguration3.setMasterUri(getMasterUri());
        String snode=Main3Activity.edNode.getText().toString();
        nodeConfiguration3.setNodeName(snode);
       // nodeConfiguration3.setNodeName("android_sensors_driver_imu1");
        this.imu_pub = new ImuPublisher(mSensorManager);
        nodeMainExecutor.execute(this.imu_pub, nodeConfiguration3);

    }
}
