package com.example.robotoperation;

import android.content.Intent;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.ros.android.MessageCallable;
import org.ros.android.RosActivity;
import org.ros.android.view.RosTextView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;
// import org.ros.rosjava_tutorial_pubsub.Talker;

/**
 * @author damonkohler@google.com (Damon Kohler)
 */
public class MainActivity extends RosActivity {


    private RosTextView<std_msgs.String> rosTextView;

    private Talker talker;
    TextView tex1, tex2, tex3, tex4, tex5;
    static float[] an =new float[5];


    public MainActivity() {
        // The RosActivity constructor configures the notification title and ticker
        // messages.
        super("RobotOperation", "RobotOperation");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rosTextView = (RosTextView<std_msgs.String>) findViewById(R.id.text);
        rosTextView.setTopicName(String.valueOf(Splash.text.getText()));
        rosTextView.setMessageType(std_msgs.String._TYPE);
        rosTextView.setMessageToStringCallable(new MessageCallable<String, std_msgs.String>() {
            @Override
            public String call(std_msgs.String message) {

                String str=message.getData();
                if(str=="restart"){
                    Main3Activity.restartCounter();
                    return str+"10";
                }
                return message.getData();
            }
        });

        tex1=(TextView)findViewById(R.id.num1);
        tex2=(TextView)findViewById(R.id.num2);
        tex3=(TextView)findViewById(R.id.num3);
        tex4=(TextView)findViewById(R.id.num4);
        tex5=(TextView)findViewById(R.id.num5);
        SeekBar seek1 =(SeekBar)findViewById(R.id.seekBar1);
        SeekBar seek2 =(SeekBar)findViewById(R.id.seekBar2);
        SeekBar seek3 =(SeekBar)findViewById(R.id.seekBar3);
        SeekBar seek4 =(SeekBar)findViewById(R.id.seekBar4);
        SeekBar seek5 =(SeekBar)findViewById(R.id.seekBar5);
        seek1.setProgress(283);seek2.setProgress(179);seek3.setProgress(94);seek4.setProgress(179);seek5.setProgress(100);

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = (float) (i-283)/100;
                tex1.setText("1º Ang:"+Float.toString((float)(i-283)/100));
                an[0]=value;            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }        });
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = (float) (i-179)/100;
                tex2.setText("2º Ang:"+Float.toString((float)(i-179)/100));
                an[1]=value;            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }        });
        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = (float) (i-94)/100;
                tex3.setText("3º Ang:"+Float.toString((float)(i-94)/100));
                an[2]=value;            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }        });
        seek4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = (float) (i-179)/100;
                tex4.setText("4º Ang:"+Float.toString((float)(i-179)/100));
                an[3]=value;            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }        });
        seek5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float value = (float) (i-100)/10000;
                tex5.setText("5º Ang:"+Float.toString((float)(i-100)/10000));
                an[4]=value;            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }        });
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {

        talker = new Talker();
        // At this point, the user has already been prompted to either enter the URI
        // of a master to use or to start a master locally.
        // The user can easily use the selected ROS Hostname in the master chooser
        // activity.
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        // The RosTextView is also a NodeMain that must be executed in order to
        // start displaying incoming messages.
        nodeMainExecutor.execute(talker, nodeConfiguration);
        nodeMainExecutor.execute(rosTextView, nodeConfiguration);
    }
    public void startSecondActivity(View view) {

        Intent secondActivity = new Intent(this, Main3Activity.class);
        startActivity(secondActivity);
    }
}

