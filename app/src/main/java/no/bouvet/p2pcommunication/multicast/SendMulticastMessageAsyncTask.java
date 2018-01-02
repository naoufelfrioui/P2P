package no.bouvet.p2pcommunication.multicast;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import no.bouvet.p2pcommunication.listener.multicast.MulticastMessageSentListener;
import no.bouvet.p2pcommunication.util.NetworkUtil;
import no.bouvet.p2pcommunication.util.UserInputHandler;
import no.bouvet.p2pcommunication.MapData;

public class SendMulticastMessageAsyncTask extends AsyncTask<Void, String, Boolean> {
    Boolean Position=false;
  //  MapData map =new MapData();
    public static final String TAG = SendMulticastMessageAsyncTask.class.getSimpleName();
    private MulticastMessageSentListener multicastMessageSentListener;
    private UserInputHandler userInputHandler;


    public SendMulticastMessageAsyncTask(MulticastMessageSentListener multicastMessageSentListener, UserInputHandler userInputHandler) {
        this.multicastMessageSentListener = multicastMessageSentListener;
        this.userInputHandler = userInputHandler;
    }
    public SendMulticastMessageAsyncTask(MulticastMessageSentListener multicastMessageSentListener,Boolean position, UserInputHandler userInputHandler) {
        this.multicastMessageSentListener = multicastMessageSentListener;
        this.userInputHandler = userInputHandler;
        this.Position=position;
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        boolean success = false;
        String messageToBeSent;

        try {
            MulticastSocket multicastSocket = createMulticastSocket();
          if(this.Position==true)
               {


                   messageToBeSent =" longitude ="+userInputHandler.getlng().toString()+"and latitude :"+userInputHandler.getlat().toString();

               //      map.setLng(userInputHandler.getlng());
              //     map.setLat(userInputHandler.getlat());
               }
         else {
            messageToBeSent = userInputHandler.getMessageToBeSentFromUserInput();
                }

            DatagramPacket datagramPacket = new DatagramPacket(messageToBeSent.getBytes(), messageToBeSent.length(), getMulticastGroupAddress(), getPort());
            multicastSocket.send(datagramPacket);
            success = true;
            Position=false;
        } catch (IOException ioException) {
            Log.e(TAG, ioException.toString());
        }
        return success;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (!success) {
            multicastMessageSentListener.onCouldNotSendMessage();
        }
        userInputHandler.clearUserInput();
    }

    private MulticastSocket createMulticastSocket() throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(getPort());
        multicastSocket.setNetworkInterface(getNetworkInterface());
        multicastSocket.joinGroup(new InetSocketAddress(getMulticastGroupAddress(), getPort()), getNetworkInterface());
        return multicastSocket;
    }

    private NetworkInterface getNetworkInterface() throws SocketException {
        return NetworkUtil.getWifiP2pNetworkInterface();
    }

    private InetAddress getMulticastGroupAddress() throws UnknownHostException {
        return NetworkUtil.getMulticastGroupAddress();
    }

    private int getPort() {
        return NetworkUtil.getPort();
    }


}
