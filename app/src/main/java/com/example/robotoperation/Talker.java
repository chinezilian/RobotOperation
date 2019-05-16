/*
 * Copyright (C) 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.robotoperation;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

/**
 * A simple {@link Publisher} {@link NodeMain}.
 * 
 * @author damonkohler@google.com (Damon Kohler)
 */
public class Talker extends AbstractNodeMain {
  private String topic_name;

  public Talker() {
    topic_name = "chatter";
  }

  public Talker(String topic)
  {
    topic_name = topic;
  }

  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("rosjava_tutorial_pubsub/talker");
  }

  @Override
  public void onStart(final ConnectedNode connectedNode) {
    final Publisher<std_msgs.Float32MultiArray> publisher =
        connectedNode.newPublisher(topic_name, std_msgs.Float32MultiArray._TYPE);
    //final Publisher<std_msgs.String> publisher1 =
    //        connectedNode.newPublisher("chatter", std_msgs.String._TYPE);
    // This CancellableLoop will be canceled automatically when the node shuts
    // down.
    connectedNode.executeCancellableLoop(new CancellableLoop() {
      private float[] num = new float[5];
      private int sequenceNumber;
      @Override
      protected void setup() {
        num[0] = 0;
        num[1] = 0;
        num[2] = 0;
        num[3] = 0;
        num[4] = 0;
        sequenceNumber = 0;
      }

      @Override
      protected void loop() throws InterruptedException {
        std_msgs.Float32MultiArray str = publisher.newMessage();
        num=MainActivity.an;
       // std_msgs.String str = publisher1.newMessage();
        //if(sequenceNumber==0){
      //    str.setData(sequenceNumber + "\n");
     //     sequenceNumber++;
        //}
       // str.setData("restart");
        str.setData(num);
        publisher.publish(str);
        Thread.sleep(10);
      }
    });
  }
}
