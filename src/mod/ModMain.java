package mod;

import arc.Core;
import arc.Events;
import arc.func.Func;
import arc.func.Prov;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.gen.LogicIO;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;
import mindustry.logic.LStatement;
import mindustry.mod.Mod;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.ui.fragments.MenuFragment;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawMulti;
import mod.logic.*;

import java.util.Arrays;
import java.util.HashMap;

public class ModMain extends Mod{
    public ModMain() throws Throwable
    {/*ToDo:
         Join(String,String)->String
         GetCharAt(String,int)->char
         GetAsciiChar(int)->char
         GetColor(int,int)->Color/double
    */Log.level=Log.LogLevel.debug;}

    @Override@SuppressWarnings({})
    public void init() {
        secretF(this);
        initDocumentationMenu();
        loadInstr();
        Events.on(EventType.WorldLoadBeginEvent.class,e->{
            //remove trash
            CallFull.CallInstruction.pointers=new HashMap<>();
        });
    }

    private static void loadInstr() {
        LAssembler.customParsers.putAll(
                "hash", (Func<String[],LStatement>)HashcodeFull.HashcodeStatement::read,
                "class", (Func<String[],LStatement>)GetClassNameFull.GetClassNameStatement::read,
                "gettext", (Func<String[],LStatement>)GetTextFull.GetTextStatement::read,
                "tostr", (Func<String[],LStatement>) NumberToStringFull.NumberToStringStatement::read,
                "label", (Func<String[],LStatement>) LabelFull.LabelStatement::read,
                "call", (Func<String[],LStatement>) CallFull.CallStatement::read,
                "ret", (Func<String[],LStatement>) ReturnFull.ReturnStatement::read
        );
        for (Prov<LStatement> prov : Arrays.<Prov<LStatement>>asList(
                HashcodeFull.HashcodeStatement::new,
                GetClassNameFull.GetClassNameStatement::new,
                GetTextFull.GetTextStatement::new,
                NumberToStringFull.NumberToStringStatement::new,
                LabelFull.LabelStatement::new,
                CallFull.CallStatement::new,
                ReturnFull.ReturnStatement::new
        )) LogicIO.allStatements.add(prov);
    }

    public static void secretF(Mod object) {
        int h = System.identityHashCode(object);
        {Vars.mods.getScripts().runConsole("function f"+h+"() {" +
                "d=new BaseDialog(\"hi\");"+
                "d.addCloseButton();"+
                "d.row();" +
                "d.cont.add(\"good job!\");"+
                "d.cont.row();"+
                "d.cont.add(\"number at function's name is hashcode of this mod instance\");"+
                "d.cont.row().add(\"may be try hash-1?\",.1)"+
                "d.show();"+
                "}"+
                "function f"+(h-1)+"() {"+
                "d=new BaseDialog(\"hi\");"+
                "d.addCloseButton();"+
                "d.row();" +
                "d.cont.row().add(\"hi again\")"+
                "d.cont.row().add(\"Mindustry can eat at maximum "+Runtime.getRuntime().maxMemory()+" bytes of RAM\")"+
                "}"
        );}
        Log.info("May be there is something in [stat]console[]...");
        System.out.println("f"+h+"()");
    }
    public static void initDocumentationMenu() {
        BaseDialog d=new BaseDialog("MLog "+ Core.bundle.get("logic.doc"));
        d.cont.add("Instruction categories");
        d.cont.row();
        final int[] i000={0};
        LCategory.all.each(category ->{
            d.cont.button(category.localized(), Styles.logict, () -> {
                BaseDialog dSquared=new BaseDialog(category.localized());
                LogicIO.allStatements.each(getter->getter.get().category()==category,getter->{
                    String bundleName = getter.get().name().toLowerCase().replace(" ","");
                    String syntax = Core.bundle.get("lsyntax"+bundleName,"@logic.notProvided");
                    String description = Core.bundle.get("lst"+bundleName,"@logic.notProvided");
                    String note;
                });
                dSquared.addCloseButton();
                dSquared.show();
            }).size(300, 75).color(category.color);
            if(++i000[0]%3==0)d.cont.row();
        });
        d.row().add("");
        d.addCloseButton();
        Vars.ui.menufrag.addButton(new MenuFragment.MenuButton("@logic.doc", Icon.bookOpen,d::show));
    }
    private void sandbox(){
        //CanvasBlock.CanvasBuild c =new CanvasBlock.CanvasBuild();
        //c.texture.getTextureData().getPixmap().get(x,y);
        //DrawBlock
    }
}
