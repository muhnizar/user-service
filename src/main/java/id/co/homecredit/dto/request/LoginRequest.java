package id.co.homecredit.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

//import javax.validation.constraints.NotEmpty;

/**
 * Created by muhammad.nizar01 on 7/10/2019.
 */

@Data
public class LoginRequest {

//    @NotEmpty
    @Length(max=100)
    private String username;

    @Length(max=100)
    private String password;
}
