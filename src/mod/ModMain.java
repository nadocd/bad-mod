package mod;

import arc.*;
import arc.util.*;
import mindustry.Vars;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.game.EventType.*;
import mindustry.game.Waves;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ModMain extends Mod{

    public ModMain(){
        Log.info("Loaded [stat]ModMain[] constructor.");

        Events.on(ClientLoadEvent.class,event -> {
            Time.run(10,()->{
                BaseDialog langMenu = new BaseDialog("Language");
                langMenu.row().add("idk how to make bundle");
                langMenu.cont.button("press if crashed",langMenu::hide).size(400,50);
                langMenu.center();
                langMenu.cont.row();
                langMenu.cont.button("English",
                        ()->{setNamesEN();langMenu.hide();}).size(600,200);
                langMenu.cont.button("\u0420\u0443\u0441\u0441\u043a\u0438\u0439",
                        ()->{setNamesRU();langMenu.hide();}).size(600,200);
                langMenu.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some sus content.");
        ItemsJCM.load();
        LiquidsJCM.load();
        StatusEffectsJCM.load();
        UnitTypesJCM.load();
        BlocksJCM.load();
        PlanetsJCM.load();
    }

    public void setNamesRU(){Time.run(10,()->{
        //items
        ItemsJCM.niotium.localizedName = "\u041d\u0438\u043e\u0442\u0438\u0439";
        //blocks
        BlocksJCM.niotiumSmelter.localizedName = "\u041d\u0438\u043e\u0442\u0438\u0432\u0430\u044f\u0020\u043f\u043b\u0430\u0432\u0438\u043b\u044c\u043d\u044f";
        //statuses
        StatusEffectsJCM.overpowered.localizedName = "\u0416\u0438\u0437\u043d\u0435\u043d\u043d\u0430\u044f\u0020\u0441\u0438\u043b\u0430";
    });}
    public void setNamesEN(){Time.run(10,()->{
        //items
        ItemsJCM.niotium.localizedName = "Niotium";
        //blocks
        BlocksJCM.niotiumSmelter.localizedName = "Niotium smelter";
        //statuses
        StatusEffectsJCM.overpowered.localizedName = "Life power";
    });}

}
