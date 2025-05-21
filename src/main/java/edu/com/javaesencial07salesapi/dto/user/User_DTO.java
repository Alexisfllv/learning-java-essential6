package edu.com.javaesencial07salesapi.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.com.javaesencial07salesapi.dto.role.Role_DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User_DTO {

    private Long idUser;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El nombre de usuario no debe exceder los 50 caracteres")
    @JsonProperty(value = "user_name")
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 60, message = "La contraseña no debe exceder los 60 caracteres")
    // ACCESO SOLO A LECTURA
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "El estado 'enabled' no puede ser nulo")
    private Boolean enabled;


    // mostrar solo campos de role dto o insertar datos
    @JsonIncludeProperties(value = {"idRole", "roleName"} )
    @NotNull(message = "El rol es obligatorio")
    private Role_DTO role;
}
