package TCP;

import com.google.gson.Gson;

public class Response <T> {
private String responseMessage;
private ResponseType responseType;

    public Response(ResponseType responseType, T responseMessage) {
        this.responseType = responseType;
        this.responseMessage = new Gson().toJson(responseMessage);
    }

    public Response() {
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}
