package id.ac.ui.cs.advprog.gametime.auth.model;

import id.ac.ui.cs.advprog.gametime.auth.model.authentication.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idBalance;
    private long jumlah;

    @OneToOne(fetch =    FetchType.LAZY, mappedBy = "balance", cascade = CascadeType.ALL)
    private AuthUser authUser;

}