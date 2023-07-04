package med.vol.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.adress.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String streetAddress;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;
    private String complement;
    private String number;

    public Address(AddressDTO data) {
        this.streetAddress = data.streetAddress();
        this.neighborhood = data.neighborhood();
        this.postalCode = data.postalCode();
        this.city = data.city();
        this.state = data.state();
        this.complement = data.complement();
        this.number = data.number();
    }

    public void updateAddress(AddressDTO data) {
        this.streetAddress = data.streetAddress() != null ? data.streetAddress() : this.streetAddress;
        this.neighborhood = data.neighborhood() != null ? data.neighborhood() : this.neighborhood;
        this.postalCode = data.postalCode() != null ? data.postalCode() : this.postalCode;
        this.city = data.city() != null ? data.city() : this.city;
        this.state = data.state()!= null ? data.state() : this.state;
        this.complement = data.complement() != null ? data.complement() : this.complement;
        this.number = data.number() != null ? data.number() : this.number;
    }
}
