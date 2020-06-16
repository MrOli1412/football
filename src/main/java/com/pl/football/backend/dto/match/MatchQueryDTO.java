package com.pl.football.backend.dto.match;

import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.model.Dress;
import com.pl.football.backend.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchQueryDTO {

    List<DressQueryDTO> dress;

    List<PlayerMatchDTO> players;

    List<StaffPersonQueryMatchDTO> staffPersons;

}
