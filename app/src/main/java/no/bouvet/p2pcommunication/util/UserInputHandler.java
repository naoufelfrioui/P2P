package no.bouvet.p2pcommunication.util;

import java.util.List;

public interface UserInputHandler {
Double getlat();
Double getlng();
    String getMessageToBeSentFromUserInput();
  //  List<Double> getMessage();
    void clearUserInput();

}
