package id.ac.ui.cs.advprog.gametime.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private Long idBalance;
    private long jumlah;
    private UserEntity authUser;
}