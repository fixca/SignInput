package kr.teamcocoa.signinput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public class SignInputModel {
    private Player player;
    private String[] lines;
    private InputCallBack callBack;
}
