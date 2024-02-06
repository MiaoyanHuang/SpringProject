package hmy.webapp.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private boolean flag;
    private Object data;
    private String error;

    public Response(boolean flag) {
        this.flag = flag;
    }

    public Response(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public Response(boolean flag, String errorMsg) {
        this.flag = flag;
        this.error = errorMsg;
    }

}
