package edu.com.javaesencial07salesapi.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User_DTO {

    private Long idUser;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El nombre de usuario no debe exceder los 50 caracteres")
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 60, message = "La contraseña no debe exceder los 60 caracteres")
    private String password;

    @NotNull(message = "El estado 'enabled' no puede ser nulo")
    private Boolean enabled;

    @NotNull(message = "El rol es obligatorio")
    private Long roleId;
}
