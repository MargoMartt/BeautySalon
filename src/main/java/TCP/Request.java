package TCP;

import com.google.gson.Gson;

public class Request <T> {
    private RequestType requestType;
    private String requestMessage;

    public Request(RequestType requestType, T requestMessage) {
        this.requestType = requestType;
        this.requestMessage = new Gson().toJson(requestMessage);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType=" + requestType +
                ", requestMessage=" + requestMessage +
                '}';
    }

    public Request() {
    }


    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }
}
