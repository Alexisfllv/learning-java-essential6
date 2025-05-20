package edu.com.javaesencial07salesapi.dto.client;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client_DTO {

    private Long idClient;
    @NotBlank(message = "El nombre es obligatorio")
    @NotNull
    @Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
    private String clientFirstName;

    @NotBlank(message = "El apellido es obligatorio")
    @NotNull
    @Size(max = 50, message = "El apellido no debe superar los 50 caracteres")
    private String clientLastName;

    @NotBlank(message = "El DNI o ID es obligatorio")
    @NotNull
    @Pattern(regexp = "\\d{8}", message = "El ID debe tener 8 dígitos numéricos")
    private String clientCardId;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @NotNull
    @Pattern(regexp = "\\d{9}", message = "El número de teléfono debe tener 9 dígitos")
    private String clientPhoneNumber;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @NotNull
    @Email(message = "Formato de correo electrónico inválido")
    private String clientEmail;

    @NotBlank(message = "La dirección es obligatoria")
    @NotNull
    @Size(max = 100, message = "La dirección no debe superar los 100 caracteres")
    private String clientAddress;
}
